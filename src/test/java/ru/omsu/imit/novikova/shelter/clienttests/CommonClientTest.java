package ru.omsu.imit.novikova.shelter.clienttests;

import org.junit.Test;
import ru.omsu.imit.novikova.rest.response.FailureResponse;
import ru.omsu.imit.novikova.utils.ErrorCode;

public class CommonClientTest extends BaseClientTest {

	@Test
	public void testWrongUrl() {
		Object response = client.post(getBaseURL() + "/wrong", null, FailureResponse.class);
		checkFailureResponse((FailureResponse) response, ErrorCode.WRONG_URL);
	}

	@Test
	public void testWrongJson() {
		Object response = client.postWrongJson(getBaseURL() + "/user", "{ text: ", FailureResponse.class);
		checkFailureResponse((FailureResponse) response, ErrorCode.JSON_PARSE_EXCEPTION);
	}

	@Test
	public void testEmptyJson() {
		Object response = client.postWrongJson(getBaseURL() + "/user", "", FailureResponse.class);
		checkFailureResponse((FailureResponse) response, ErrorCode.NULL_REQUEST);
	}

}

