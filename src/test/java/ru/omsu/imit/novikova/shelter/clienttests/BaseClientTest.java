package ru.omsu.imit.novikova.shelter.clienttests;

import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.novikova.client.ShelterClient;
import ru.omsu.imit.novikova.rest.response.EmptySuccessResponse;
import ru.omsu.imit.novikova.rest.response.FailureResponse;
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
//	private VoterDao voterDAO = new VoterDaoImpl();

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

//	@Before
//	public void clearDataBase() {
//		voterDAO.deleteAll();
//	}
	
	public static String getBaseURL() {
		return baseURL;
	}

	protected void checkFailureResponse(Object response, ErrorCode expectedStatus) {
		assertTrue(response instanceof FailureResponse);
		FailureResponse failureResponseObject = (FailureResponse) response;
		assertEquals(expectedStatus, failureResponseObject.getErrorCode());
	}

	//Voter

//	protected VoterResponse addVoter(VoterRequest request, ErrorCode expectedStatus) {
//		Object response = client.post(baseURL + "/voter", request, VoterResponse.class);
//		if (response instanceof VoterResponse) {
//			assertEquals(ErrorCode.SUCCESS, expectedStatus);
//			VoterResponse voterResponse = (VoterResponse) response;
//			checkVoterFields(request,voterResponse);
//			return voterResponse;
//		} else {
//			checkFailureResponse(response, expectedStatus);
//			return null;
//		}
//	}
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
//	protected VoterResponse getVoterByPassport(String passport, ErrorCode expectedStatus) {
//		Object response = client.get(baseURL + "/voter/passport=" + passport, VoterResponse.class);
//		if (response instanceof VoterResponse) {
//			assertEquals(ErrorCode.SUCCESS, expectedStatus);
//			VoterResponse getVoterResponse = (VoterResponse) response;
//			assertEquals(passport, getVoterResponse.getPassport());
//			return getVoterResponse;
//		} else {
//			checkFailureResponse(response, expectedStatus);
//			return null;
//		}
//	}

//	protected List<VoterResponse> getAllVoters(int expeectedCount, ErrorCode expectedStatus) {
//		Object response = client.get(baseURL + "/voter/", List.class);
//		if (response instanceof List<?>) {
//			assertEquals(ErrorCode.SUCCESS, expectedStatus);
//			@SuppressWarnings("unchecked")
//			List<VoterResponse> responseList = (List<VoterResponse>) response;
//			assertEquals(expeectedCount, responseList.size());
//			return responseList;
//		} else {
//			checkFailureResponse(response, expectedStatus);
//			return null;
//		}
//	}
//
//	protected VoterResponse changeVoter(int id, VoterRequest request, ErrorCode expectedStatus) {
//		Object response = client.put(baseURL + "/voter/" + id , request, VoterResponse.class);
//		if (response instanceof VoterResponse) {
//			assertEquals(ErrorCode.SUCCESS, expectedStatus);
//			VoterResponse addVoterResponse = (VoterResponse) response;
//			return addVoterResponse;
//		} else {
//			checkFailureResponse(response, expectedStatus);
//			return null;
//		}
//	}
//
//	protected EmptySuccessResponse deleteVoter(int id, ErrorCode expectedStatus) {
//		Object response = client.delete(baseURL + "/voter/" + id , EmptySuccessResponse.class);
//		if (response instanceof EmptySuccessResponse) {
//			assertEquals(ErrorCode.SUCCESS, expectedStatus);
//			return (EmptySuccessResponse)response;
//		} else {
//			checkFailureResponse(response, expectedStatus);
//			return null;
//		}
//	}

	//check Equals
//	protected void checkVoterFields(VoterRequest voter1, VoterResponse voter2) {
//		assertEquals(voter1.getFirstName(), voter2.getFirstName());
//		assertEquals(voter1.getLastName(),  voter2.getLastName());
//		assertEquals(voter1.getPatronymic(),voter2.getPatronymic());
//		assertEquals(voter1.getBirthdate(), voter2.getBirthdate());
//		assertEquals(voter1.getPassport(),  voter2.getPassport());
//		assertEquals(voter1.getCity(),      voter2.getCity());
//		assertEquals(voter1.getStreet(),    voter2.getStreet());
//		assertEquals(voter1.getHouse(),     voter2.getHouse());
//		assertEquals(voter1.getFlat(),      voter2.getFlat());
//	}

}
