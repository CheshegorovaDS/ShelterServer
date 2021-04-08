package ru.omsu.imit.novikova.rest.response;


import ru.omsu.imit.novikova.utils.ErrorCode;

public class FailureResponse extends BaseResponseObject {

	private ErrorCode errorCode;
	private String message;

	public FailureResponse(ErrorCode errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	public FailureResponse(ErrorCode errorCode) {
		this(errorCode, "");
	}
	public ErrorCode getErrorCode() {
		return errorCode;
	}


	public String getMessage() {
		return message;
	}


}
