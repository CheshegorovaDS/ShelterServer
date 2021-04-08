package ru.omsu.imit.novikova.utils;


public enum ErrorCode {

	SUCCESS("", ""),
	ITEM_NOT_FOUND("item", "Item not found %s"),
	NULL_REQUEST("json", "Null request"),
	JSON_PARSE_EXCEPTION("json", "Json parse exception :  %s"),
	WRONG_URL("url", "Wrong URL"),
	METHOD_NOT_ALLOWED("url", "Method not allowed")
	;	

	private String field;
	private String message;

	private ErrorCode(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}


}
