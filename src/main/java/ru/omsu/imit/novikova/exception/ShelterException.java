package ru.omsu.imit.novikova.exception;

import ru.omsu.imit.novikova.utils.ErrorCode;

public class ShelterException extends Exception {

    private static final long serialVersionUID = 6049904777923589329L;
    private ErrorCode errorCode;
    private String param;

    public ShelterException(ErrorCode errorCode, String param) {
        this.errorCode = errorCode;
        this.param = param;
    }

    public ShelterException(ErrorCode errorCode) {
        this(errorCode, null);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        if (param != null)
            return String.format(errorCode.getMessage(), param);
        else
            return errorCode.getMessage();
    }
}
