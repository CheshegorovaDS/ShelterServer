package ru.omsu.imit.novikova.shelter.clienttests;

import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.novikova.client.ShelterClient;
import ru.omsu.imit.novikova.dao.HumanDao;
import ru.omsu.imit.novikova.daoimpl.HumanDaoImpl;
import ru.omsu.imit.novikova.model.Human;
import ru.omsu.imit.novikova.rest.request.HumanRequest;
import ru.omsu.imit.novikova.rest.response.EmptySuccessResponse;
import ru.omsu.imit.novikova.rest.response.FailureResponse;
import ru.omsu.imit.novikova.rest.response.HumanResponse;
import ru.omsu.imit.novikova.server.ShelterServer;
import ru.omsu.imit.novikova.server.config.Settings;
import ru.omsu.imit.novikova.utils.ErrorCode;
import ru.omsu.imit.novikova.utils.MyBatisUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BaseClientTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseClientTest.class);
	private HumanDao humanDao = new HumanDaoImpl();

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
		humanDao.deleteAll();
	}
	
	public static String getBaseURL() {
		return baseURL;
	}

	protected void checkFailureResponse(Object response, ErrorCode expectedStatus) {
		assertTrue(response instanceof FailureResponse);
		FailureResponse failureResponseObject = (FailureResponse) response;
		assertEquals(expectedStatus, failureResponseObject.getErrorCode());
	}

	//Human

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
//
//	protected VoterResponse getVoterById(int id, ErrorCode expectedStatus) {
//		Object response = client.get(baseURL + "/voter/" + id, VoterResponse.class);
//		if (response instanceof VoterResponse) {
//			assertEquals(ErrorCode.SUCCESS, expectedStatus);
//			VoterResponse getVoterResponse = (VoterResponse) response;
//			assertEquals(id, getVoterResponse.getId());
//			return getVoterResponse;
//		} else {
//			checkFailureResponse(response, expectedStatus);
//			return null;
//		}
//	}
//
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
		assertEquals(human1.getPassword(),			 human2.getPassword());
		assertEquals(human1.getFirstName(),	 		 human2.getFirstName());
		assertEquals(human1.getLastName(), 	 		 human2.getLastName());
		assertEquals(human1.getPatronymic(),		 human2.getPatronymic());
		assertEquals(human1.getBirthdate(),			 human2.getBirthdate());
		assertEquals(human1.getCountry(), 			 human2.getCountry());
		assertEquals(human1.getCity(),				 human2.getCity());
		assertEquals(human1.getRegistrationDate(), 	 human2.getRegistrationDate());
	}

}
