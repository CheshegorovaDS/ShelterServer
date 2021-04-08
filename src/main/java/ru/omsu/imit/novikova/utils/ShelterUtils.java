package ru.omsu.imit.novikova.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.lang.StringUtils;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.rest.response.FailureResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ShelterUtils {
    private static final Gson GSON = new Gson();

    public static <T> T getClassInstanceFromJson(Gson gson, String json, Class<T> clazz) throws ShelterException {
        if (StringUtils.isEmpty(json)) {
            throw new ShelterException(ErrorCode.NULL_REQUEST);
        }
        try {
            return gson.fromJson(json, clazz);
        } catch (JsonSyntaxException ex) {
            throw new ShelterException(ErrorCode.JSON_PARSE_EXCEPTION, json);
        }
    }

    public static Response failureResponse(Response.Status status, ShelterException ex) {
        return Response.status(status).entity(GSON.toJson(new FailureResponse(ex.getErrorCode(), ex.getMessage())))
                .type(MediaType.APPLICATION_JSON).build();
    }

    public static Response failureResponse(ShelterException ex) {
        return failureResponse(Response.Status.BAD_REQUEST, ex);
    }
}
