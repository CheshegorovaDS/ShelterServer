package ru.omsu.imit.novikova.rest.mappers;

import ru.omsu.imit.novikova.utils.ErrorCode;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.utils.ShelterUtils;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class WrongURLExceptionMapper implements	ExceptionMapper<NotFoundException> {

    @Override
	public Response toResponse(NotFoundException exception) {
		return ShelterUtils.failureResponse(Status.NOT_FOUND, new ShelterException(ErrorCode.WRONG_URL));
	}
}