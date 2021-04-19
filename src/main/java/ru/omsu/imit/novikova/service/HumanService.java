package ru.omsu.imit.novikova.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.novikova.dao.HumanDao;
import ru.omsu.imit.novikova.daoimpl.HumanDaoImpl;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.Human;
import ru.omsu.imit.novikova.rest.request.HumanRequest;
import ru.omsu.imit.novikova.rest.response.EmptySuccessResponse;
import ru.omsu.imit.novikova.rest.response.HumanResponse;
import ru.omsu.imit.novikova.utils.ShelterUtils;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class HumanService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HumanService.class);
    private static final Gson GSON = new GsonBuilder().create();
    private HumanDao humanDao = new HumanDaoImpl();

    public Response insert(String json) {
        LOGGER.debug("Insert human " + json);
        try {
            HumanRequest request = ShelterUtils.getClassInstanceFromJson(GSON, json, HumanRequest.class);
            Human human = new Human(0, request.getPhone(), request.getEmail(), request.getPassword(),
                    request.getFirstName(),request.getLastName(),request.getPatronymic(),
                    request.getBirthdate(),request.getCountry(),request.getCity(),request.getRegistrationDate());
            Human addedHuman = humanDao.insert(human);
            String response = GSON.toJson(new HumanResponse(addedHuman.getId(), addedHuman.getUser().getPhone(),addedHuman.getUser().getEmail(),
                    addedHuman.getUser().getPassword(), addedHuman.getFirstName(), addedHuman.getLastName(), addedHuman.getPatronymic(),
                    addedHuman.getBirthdate(), addedHuman.getCountry(), addedHuman.getCity(), addedHuman.getRegistrationDate()));
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ShelterException ex) {
            return ShelterUtils.failureResponse(ex);
        }
    }

    public Response getById(int id) {
        LOGGER.debug("Get Human By id " + id);
        try {
            Human human = humanDao.getById(id);
            String response = GSON.toJson(new HumanResponse(human.getId(), human.getUser().getPhone(),human.getUser().getEmail(),
                    human.getUser().getPassword(), human.getFirstName(), human.getLastName(), human.getPatronymic(),
                    human.getBirthdate(), human.getCountry(), human.getCity(), human.getRegistrationDate()));
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ShelterException ex) {
            return ShelterUtils.failureResponse(ex);
        }
    }

    public Response getByEmail(String email) {
        LOGGER.debug("Get Human By email " + email);
        try {
            Human human = humanDao.getByEmail(email);
            String response = GSON.toJson(new HumanResponse(human.getId(), human.getUser().getPhone(),human.getUser().getEmail(),
                    human.getUser().getPassword(), human.getFirstName(), human.getLastName(), human.getPatronymic(),
                    human.getBirthdate(), human.getCountry(), human.getCity(), human.getRegistrationDate()));
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ShelterException ex) {
            return ShelterUtils.failureResponse(ex);
        }
    }

    public Response changeHuman(int id, String json) {
        LOGGER.debug("Change Human By id " + id);
        try {
            HumanRequest request = ShelterUtils.getClassInstanceFromJson(GSON, json, HumanRequest.class);
            Human human = humanDao.getById(id);
            human.updateUser(request.getPhone(), request.getEmail(), request.getPassword());
            humanDao.changeHuman(human.getId(), human);
            String response = GSON.toJson(new HumanResponse(human.getId(), human.getUser().getPhone(),human.getUser().getEmail(),
                    human.getUser().getPassword(), human.getFirstName(), human.getLastName(), human.getPatronymic(),
                    human.getBirthdate(), human.getCountry(), human.getCity(), human.getRegistrationDate()));
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ShelterException ex) {
            return ShelterUtils.failureResponse(ex);
        }
    }

    public Response deleteById(int id, String json) {
        LOGGER.debug("Delete Human By id " + id);
        humanDao.delete(id);
        String response = GSON.toJson(new EmptySuccessResponse());
        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }
}
