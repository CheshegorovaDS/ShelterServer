package ru.omsu.imit.novikova.rest.mappers;

import ru.omsu.imit.novikova.utils.ErrorCode;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.utils.ShelterUtils;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class MethodNotAllowedExceptionMapper implements	ExceptionMapper<NotAllowedException> {

    @Override
	public Response toResponse(NotAllowedException exception) {
		return ShelterUtils.failureResponse(Status.METHOD_NOT_ALLOWED, new ShelterException(ErrorCode.METHOD_NOT_ALLOWED));
	}
}