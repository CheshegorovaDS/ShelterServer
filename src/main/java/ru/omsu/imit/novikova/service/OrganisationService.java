package ru.omsu.imit.novikova.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.novikova.dao.OrganisationDao;
import ru.omsu.imit.novikova.daoimpl.OrganisationDaoImpl;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.Organisation;
import ru.omsu.imit.novikova.rest.request.OrganisationRequest;
import ru.omsu.imit.novikova.rest.response.EmptySuccessResponse;
import ru.omsu.imit.novikova.rest.response.OrganisationResponse;
import ru.omsu.imit.novikova.utils.ShelterUtils;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class OrganisationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrganisationService.class);
    private static final Gson GSON = new GsonBuilder().create();
    private OrganisationDao organisationDao = new OrganisationDaoImpl();

    public Response insert(String json) {
        LOGGER.debug("Insert organisation " + json);
        try {
            OrganisationRequest request = ShelterUtils.getClassInstanceFromJson(GSON, json, OrganisationRequest.class);
            String hashPass = BCrypt.withDefaults().hashToString(12, request.getPassword().toCharArray());
            Organisation organisation = new Organisation(0, request.getPhone(), request.getEmail(), hashPass,
                    request.getTin(), request.getTitle(), request.getAdditionalInfo(), request.getRegistrationDate());
            Organisation addedOrganisation = organisationDao.insert(organisation);
            String response = GSON.toJson(new OrganisationResponse(
                    addedOrganisation.getId(), addedOrganisation.getUser().getPhone(),
                    addedOrganisation.getUser().getEmail(), addedOrganisation.getUser().getPassword(),
                    addedOrganisation.getTIN(), addedOrganisation.getTitle(), addedOrganisation.getAdditionalInfo(),
                    addedOrganisation.getRegistrationDate()));
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ShelterException ex) {
            return ShelterUtils.failureResponse(ex);
        }
    }

    public Response getById(int id) {
        LOGGER.debug("Get Organisation By id " + id);
        try {
            Organisation organisation = organisationDao.getById(id);
            String response = GSON.toJson(new OrganisationResponse(
                    organisation.getId(), organisation.getUser().getPhone(),
                    organisation.getUser().getEmail(), organisation.getUser().getPassword(),
                    organisation.getTIN(), organisation.getTitle(),
                    organisation.getAdditionalInfo(), organisation.getRegistrationDate()));
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ShelterException ex) {
            return ShelterUtils.failureResponse(ex);
        }
    }

    public Response getByEmail(String email) {
        LOGGER.debug("Get Organisation By email " + email);
        try {
            Organisation organisation = organisationDao.getByEmail(email);
            String response = GSON.toJson(new OrganisationResponse(
                    organisation.getId(), organisation.getUser().getPhone(),
                    organisation.getUser().getEmail(), organisation.getUser().getPassword(),
                    organisation.getTIN(), organisation.getTitle(),
                    organisation.getAdditionalInfo(), organisation.getRegistrationDate()));
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ShelterException ex) {
            return ShelterUtils.failureResponse(ex);
        }
    }

    public Response changeOrganisation(int id, String json) {
        LOGGER.debug("Change Organisation By id " + id);
        try {
            OrganisationRequest request = ShelterUtils.getClassInstanceFromJson(GSON, json, OrganisationRequest.class);
            Organisation organisation = organisationDao.getById(id);
            String hashPass = BCrypt.withDefaults().hashToString(12, request.getPassword().toCharArray());
            organisation.updateUser(request.getPhone(), request.getEmail(), hashPass);
            organisation.setTIN(request.getTin());
            organisation.setTitle(request.getTitle());
            organisation.setAdditionalInfo(request.getAdditionalInfo());
            organisation.setRegistrationDate(request.getRegistrationDate());
            organisationDao.changeOrganisation(organisation.getId(), organisation);
            String response = GSON.toJson(new OrganisationResponse(
                    organisation.getId(), organisation.getUser().getPhone(),
                    organisation.getUser().getEmail(), organisation.getUser().getPassword(),
                    organisation.getTIN(), organisation.getTitle(),
                    organisation.getAdditionalInfo(), organisation.getRegistrationDate()));
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ShelterException ex) {
            return ShelterUtils.failureResponse(ex);
        }
    }

    public Response deleteById(int id, String json) {
        LOGGER.debug("Delete Organisation By id " + id);
        organisationDao.delete(id);
        String response = GSON.toJson(new EmptySuccessResponse());
        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }
}
