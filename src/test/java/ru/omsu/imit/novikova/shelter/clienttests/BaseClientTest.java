package ru.omsu.imit.novikova.shelter.clienttests;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.novikova.client.ShelterClient;
import ru.omsu.imit.novikova.dao.CardDao;
import ru.omsu.imit.novikova.dao.HumanDao;
import ru.omsu.imit.novikova.dao.OrganisationDao;
import ru.omsu.imit.novikova.daoimpl.CardDaoImpl;
import ru.omsu.imit.novikova.daoimpl.HumanDaoImpl;
import ru.omsu.imit.novikova.daoimpl.OrganisationDaoImpl;
import ru.omsu.imit.novikova.model.AnimalType;
import ru.omsu.imit.novikova.model.Card;
import ru.omsu.imit.novikova.model.Category;
import ru.omsu.imit.novikova.rest.request.CardRequest;
import ru.omsu.imit.novikova.rest.request.HumanRequest;
import ru.omsu.imit.novikova.rest.request.OrganisationRequest;
import ru.omsu.imit.novikova.rest.request.UserRequest;
import ru.omsu.imit.novikova.rest.response.*;
import ru.omsu.imit.novikova.server.ShelterServer;
import ru.omsu.imit.novikova.server.config.Settings;
import ru.omsu.imit.novikova.utils.ErrorCode;
import ru.omsu.imit.novikova.utils.MyBatisUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BaseClientTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseClientTest.class);
	private HumanDao humanDao = new HumanDaoImpl();
	private OrganisationDao organisationDao = new OrganisationDaoImpl();
	private CardDao cardDao = new CardDaoImpl();

	protected static ShelterClient client = new ShelterClient();
	private static String baseURL;

	private static void initialize() {
		Assume.assumeTrue(MyBatisUtils.initSqlSessionFactory());
		String hostName = null;
		try {
			hostName = InetAddress.getLocalHost().getCanonicalHostName();
		} catch (UnknownHostException e) {
			LOGGER.debug("Can't determine my own host name", e);
		}
		baseURL = "http://" + hostName + ":" + Settings.getRestHTTPPort() + "/api";
	}

	@BeforeClass
	public static void startServer() {
		initialize();
		ShelterServer.createServer();
	}

	@AfterClass
	public static void stopServer() {
			ShelterServer.stopServer();
	}

	@Before
	public void clearDataBase() {
	    cardDao.deleteAll();
		humanDao.deleteAll();
		organisationDao.deleteAll();
	}
	
	public static String getBaseURL() {
		return baseURL;
	}

	protected void checkFailureResponse(Object response, ErrorCode expectedStatus) {
		assertTrue(response instanceof FailureResponse);
		FailureResponse failureResponseObject = (FailureResponse) response;
		assertEquals(expectedStatus, failureResponseObject.getErrorCode());
	}

	//Login

	protected TokenResponse login(UserRequest request, ErrorCode expectedStatus) {
		Object response = client.get(
				baseURL + "/login/login=" + request.getLogin() + "&pass=" + request.getPassword(),
				TokenResponse.class
		);
		if (response instanceof TokenResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			TokenResponse getTokenResponse = (TokenResponse) response;
			return getTokenResponse;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	//Logout

	protected EmptySuccessResponse logout(String uuid, ErrorCode expectedStatus) {
		Object response = client.delete(baseURL + "/logout/uuid=" + uuid , EmptySuccessResponse.class);
		if (response instanceof EmptySuccessResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			return (EmptySuccessResponse)response;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	//Human

//	Add

	protected HumanResponse addHuman(HumanRequest request, ErrorCode expectedStatus) {
		Object response = client.post(baseURL + "/human", request, HumanResponse.class);
		if (response instanceof HumanResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			HumanResponse humanResponse = (HumanResponse) response;
			checkHumanFields(request, humanResponse);
			return humanResponse;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

//	Get

	protected HumanResponse getHumanById(int id, ErrorCode expectedStatus) {
		Object response = client.get(baseURL + "/human/" + id,  HumanResponse.class);
		if (response instanceof HumanResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			HumanResponse getHumanResponse = (HumanResponse) response;
			assertEquals(id, getHumanResponse.getId());
			return getHumanResponse;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	protected HumanResponse getHumanByEmail(String email, ErrorCode expectedStatus) {
		Object response = client.get(baseURL + "/human/email=" + email,  HumanResponse.class);
		if (response instanceof HumanResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			HumanResponse getHumanResponse = (HumanResponse) response;
			assertEquals(email, getHumanResponse.getEmail());
			return getHumanResponse;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

//	Update

	protected HumanResponse changeHuman(int id, HumanRequest request, ErrorCode expectedStatus) {
		Object response = client.put(baseURL + "/human/" + id , request, HumanResponse.class);
		if (response instanceof HumanResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			HumanResponse addHumanResponse = (HumanResponse) response;
			return addHumanResponse;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

//	Delete

	protected EmptySuccessResponse deleteHuman(int id, ErrorCode expectedStatus) {
		Object response = client.delete(baseURL + "/human/" + id , EmptySuccessResponse.class);
		if (response instanceof EmptySuccessResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			return (EmptySuccessResponse)response;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	//check Equals

	protected void checkHumanFields(HumanRequest human1, HumanResponse human2) {
		assertEquals(human1.getPhone(),				 human2.getPhone());
		assertEquals(human1.getEmail(), 			 human2.getEmail());
		assertEquals(human1.getFirstName(),	 		 human2.getFirstName());
		assertEquals(human1.getLastName(), 	 		 human2.getLastName());
		assertEquals(human1.getPatronymic(),		 human2.getPatronymic());
		assertEquals(human1.getBirthdate(),			 human2.getBirthdate());
		assertEquals(human1.getCountry(), 			 human2.getCountry());
		assertEquals(human1.getCity(),				 human2.getCity());
		assertEquals(human1.getRegistrationDate(), 	 human2.getRegistrationDate());
		assertTrue(BCrypt.verifyer().verify(human1.getPassword().toCharArray(), human2.getPassword()).verified);
	}

	//Organisation

//	Add

	protected OrganisationResponse addOrganisation(OrganisationRequest request, ErrorCode expectedStatus) {
		Object response = client.post(baseURL + "/organisation", request, OrganisationResponse.class);
		if (response instanceof OrganisationResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			OrganisationResponse organisationResponse = (OrganisationResponse) response;
			checkOrganisationFields(request, organisationResponse);
			return organisationResponse;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

//	Get

	protected OrganisationResponse getOrganisationById(int id, ErrorCode expectedStatus) {
		Object response = client.get(baseURL + "/organisation/" + id,  OrganisationResponse.class);
		if (response instanceof OrganisationResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			OrganisationResponse getOrganisationResponse = (OrganisationResponse) response;
			assertEquals(id, getOrganisationResponse.getId());
			return getOrganisationResponse;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	protected OrganisationResponse getOrganisationByEmail(String email, ErrorCode expectedStatus) {
		Object response = client.get(baseURL + "/organisation/email=" + email,  OrganisationResponse.class);
		if (response instanceof OrganisationResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			OrganisationResponse organisationResponse = (OrganisationResponse) response;
			assertEquals(email, organisationResponse.getEmail());
			return organisationResponse;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

//	Update

	protected OrganisationResponse changeOrganisation(int id, OrganisationRequest request, ErrorCode expectedStatus) {
		Object response = client.put(baseURL + "/organisation/" + id , request, OrganisationResponse.class);
		if (response instanceof OrganisationResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			OrganisationResponse addOrganisationResponse = (OrganisationResponse) response;
			return addOrganisationResponse;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

//	Delete

	protected EmptySuccessResponse deleteOrganisation(int id, ErrorCode expectedStatus) {
		Object response = client.delete(baseURL + "/organisation/" + id , EmptySuccessResponse.class);
		if (response instanceof EmptySuccessResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			return (EmptySuccessResponse)response;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	//check Equals

	protected void checkOrganisationFields(OrganisationRequest request, OrganisationResponse response) {
		assertEquals(request.getPhone(),			 response.getPhone());
		assertEquals(request.getEmail(), 			 response.getEmail());
		assertEquals(request.getTin(),	 		 	 response.getTIN());
		assertEquals(request.getTitle(), 	 		 response.getTitle());
		assertEquals(request.getAdditionalInfo(),	 response.getAdditionalInfo());
		assertEquals(request.getRegistrationDate(),  response.getRegistrationDate());
		assertTrue(BCrypt.verifyer().verify(request.getPassword().toCharArray(), response.getPassword()).verified);
	}

//	Card
	//Insert
	protected CardResponse addCard(CardRequest request, ErrorCode expectedStatus) {
		Object response = client.post(baseURL + "/card", request, CardResponse.class);
		if (response instanceof CardResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			CardResponse cardResponse = (CardResponse) response;
			checkCardFields(request, cardResponse);
			return cardResponse;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

    //	Get

    protected CardResponse getCardById(int id, ErrorCode expectedStatus) {
        Object response = client.get(baseURL + "/card/" + id,  CardResponse.class);
        if (response instanceof CardResponse) {
            assertEquals(ErrorCode.SUCCESS, expectedStatus);
            CardResponse getCardResponse = (CardResponse) response;
            assertEquals(id, getCardResponse.getIdAnimal());
            return getCardResponse;
        } else {
            checkFailureResponse(response, expectedStatus);
            return null;
        }
    }

	protected List<CardResponse> getAllCard(int expectedCount, ErrorCode expectedStatus) {
		Object response = client.get(baseURL + "/cards/", List.class);
		if (response instanceof List<?>) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			@SuppressWarnings("unchecked")
			List<CardResponse> responseList = (List<CardResponse>) response;
			assertEquals(expectedCount, responseList.size());
			return responseList;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	protected List<CardResponse> getAllCardByUser(int id, int expectedCount, ErrorCode expectedStatus) {
		Object response = client.get(baseURL + "/cards/user="+ id, List.class);
		if (response instanceof List<?>) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			@SuppressWarnings("unchecked")
			List<CardResponse> responseList = (List<CardResponse>) response;
			assertEquals(expectedCount, responseList.size());
			return responseList;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	protected List<CardResponse> getAllCardByAnimalType(int id, int expectedCount, ErrorCode expectedStatus) {
		Object response = client.get(baseURL + "/cards/animalType="+ id, List.class);
		if (response instanceof List<?>) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			@SuppressWarnings("unchecked")
			List<CardResponse> responseList = (List<CardResponse>) response;
			assertEquals(expectedCount, responseList.size());
			return responseList;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	protected List<CardResponse> getAllCardByCategory(int id, int expectedCount, ErrorCode expectedStatus) {
		Object response = client.get(baseURL + "/cards/category="+ id, List.class);
		if (response instanceof List<?>) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			@SuppressWarnings("unchecked")
			List<CardResponse> responseList = (List<CardResponse>) response;
			assertEquals(expectedCount, responseList.size());
			return responseList;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	protected List<CardResponse> getAllCardByString(String request, int expectedCount, ErrorCode expectedStatus) {
		Object response = client.get(baseURL + "/cards/request="+ request, List.class);
		if (response instanceof List<?>) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			@SuppressWarnings("unchecked")
			List<CardResponse> responseList = (List<CardResponse>) response;
			assertEquals(expectedCount, responseList.size());
			return responseList;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	// Update

	protected CardResponse changeCard(int id, CardRequest request, ErrorCode expectedStatus) {
		Object response = client.put(baseURL + "/card/" + id , request, CardResponse.class);
		if (response instanceof CardResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			CardResponse addCardResponse = (CardResponse) response;
			return addCardResponse;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}


	//	Delete

	protected EmptySuccessResponse deleteCard(int id, ErrorCode expectedStatus) {
		Object response = client.delete(baseURL + "/card/" + id , EmptySuccessResponse.class);
		if (response instanceof EmptySuccessResponse) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			return (EmptySuccessResponse)response;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	//check Equals

	protected void checkCardFields(CardRequest request, CardResponse response) {
		assertEquals(request.getIdUser(),			 response.getIdUser());
		assertEquals(request.getIdCategory(),		 response.getIdCategory());
		assertEquals(request.getNameAnimal(),        response.getNameAnimal());
        assertEquals(request.getPhotoAnimal(),       response.getPhotoAnimal());
        assertEquals(request.getAgeAnimal(),         response.getAgeAnimal());
        assertEquals(request.getBreedAnimal(),       response.getBreedAnimal());
        assertEquals(request.getIdAnimalType(),      response.getAnimalType());
        assertEquals(request.getSexAnimal(),         response.getSexAnimal());
        assertEquals(request.getPassportAnimal(),    response.getPassportAnimal());
        assertEquals(request.getDescriptionAnimal(), response.getDescriptionAnimal());
	}

	//Animal Type

	protected List<AnimalTypeResponse> getAllAnimalTypes(int expectedCount, ErrorCode expectedStatus) {
		Object response = client.get(baseURL + "/animal_types/", List.class);
		if (response instanceof List<?>) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			@SuppressWarnings("unchecked")
			List<AnimalTypeResponse> responseList = (List<AnimalTypeResponse>) response;
			assertEquals(expectedCount, responseList.size());
			return responseList;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}

	//Category

	protected List<CategoryResponse> getAllCategories(int expectedCount, ErrorCode expectedStatus) {
		Object response = client.get(baseURL + "/categories/", List.class);
		if (response instanceof List<?>) {
			assertEquals(ErrorCode.SUCCESS, expectedStatus);
			@SuppressWarnings("unchecked")
			List<CategoryResponse> responseList = (List<CategoryResponse>) response;
			assertEquals(expectedCount, responseList.size());
			return responseList;
		} else {
			checkFailureResponse(response, expectedStatus);
			return null;
		}
	}
}
