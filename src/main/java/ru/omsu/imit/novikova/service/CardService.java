package ru.omsu.imit.novikova.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.novikova.dao.*;
import ru.omsu.imit.novikova.daoimpl.*;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.*;
import ru.omsu.imit.novikova.rest.request.CardRequest;
import ru.omsu.imit.novikova.rest.response.CardResponse;
import ru.omsu.imit.novikova.rest.response.EmptySuccessResponse;
import ru.omsu.imit.novikova.utils.ShelterUtils;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class CardService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HumanService.class);
    private static final Gson GSON = new GsonBuilder().create();
    private CardDao cardDao = new CardDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private CategoryDao categoryDao = new CategoryDaoImpl();
    private AnimalTypeDao animalTypeDao = new AnimalTypeDaoImpl();

    public Response insert(String json) {
        LOGGER.debug("Insert card " + json);
        try {
            CardRequest request = ShelterUtils.getClassInstanceFromJson(GSON, json, CardRequest.class);
            User user = userDao.getById(request.getIdUser());
            Category category = categoryDao.getById(request.getIdCategory());
            AnimalType animalType = animalTypeDao.getById(request.getIdAnimalType());
            Card card = new Card(user, category,
                    new Animal(0, request.getNameAnimal(), request.getPhotoAnimal(),
                            request.getAgeAnimal(), request.getBreedAnimal(), animalType,
                            request.getSexAnimal(), request.getPassportAnimal(), request.getDescriptionAnimal()));
            Card addedCard = cardDao.insert(card);
            String response = GSON.toJson(new CardResponse(
                    addedCard.getAnimal().getId(), addedCard.getAnimal().getName(),
                    addedCard.getAnimal().getPhoto(), addedCard.getAnimal().getAge(),
                    addedCard.getAnimal().getBreed(), addedCard.getAnimal().getAnimalType().getId(),
                    addedCard.getAnimal().getSex(), addedCard.getAnimal().getPassport(),
                    addedCard.getAnimal().getDescription(),
                    addedCard.getUser().getId(), addedCard.getUser().getPhone(),
                    addedCard.getUser().getEmail(), addedCard.getUser().getPassword(),
                    addedCard.getCategory().getId(), addedCard.getCategory().getTitle()
                    )
            );
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ShelterException ex) {
            return ShelterUtils.failureResponse(ex);
        }
    }

    public Response getByAnimalId(int id) {
        LOGGER.debug("Get Card By id " + id);
        try {
            Card card = cardDao.getByAnimalId(id);
            String response = GSON.toJson(new CardResponse(
                    card.getAnimal().getId(), card.getAnimal().getName(), card.getAnimal().getPhoto(),
                    card.getAnimal().getAge(), card.getAnimal().getBreed(), card.getAnimal().getAnimalType().getId(),
                    card.getAnimal().getSex(), card.getAnimal().getPassport(), card.getAnimal().getDescription(),
                    card.getUser().getId(), card.getUser().getPhone(),
                    card.getUser().getEmail(), card.getUser().getPassword(),
                    card.getCategory().getId(), card.getCategory().getTitle()
            ));
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ShelterException ex) {
            return ShelterUtils.failureResponse(ex);
        }
    }

    public Response getAll() {
        LOGGER.debug("Get All Cards");
        List<Card> itemList = cardDao.getAll();
        List<CardResponse> responseList = new ArrayList<>();
        for (Card card : itemList)
            responseList.add(new CardResponse(
                    card.getAnimal().getId(), card.getAnimal().getName(), card.getAnimal().getPhoto(),
                    card.getAnimal().getAge(), card.getAnimal().getBreed(), card.getAnimal().getAnimalType().getId(),
                    card.getAnimal().getSex(), card.getAnimal().getPassport(), card.getAnimal().getDescription(),
                    card.getUser().getId(), card.getUser().getPhone(),
                    card.getUser().getEmail(), card.getUser().getPassword(),
                    card.getCategory().getId(), card.getCategory().getTitle()
            ));
        String response = GSON.toJson(responseList);
        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }

    public Response getAllByUser(int id) {
        LOGGER.debug("Get All Cards By User");
        List<Card> itemList = cardDao.getByUserId(id);
        List<CardResponse> responseList = new ArrayList<>();
        for (Card card : itemList)
            responseList.add(new CardResponse(
                    card.getAnimal().getId(), card.getAnimal().getName(), card.getAnimal().getPhoto(),
                    card.getAnimal().getAge(), card.getAnimal().getBreed(), card.getAnimal().getAnimalType().getId(),
                    card.getAnimal().getSex(), card.getAnimal().getPassport(), card.getAnimal().getDescription(),
                    card.getUser().getId(), card.getUser().getPhone(),
                    card.getUser().getEmail(), card.getUser().getPassword(),
                    card.getCategory().getId(), card.getCategory().getTitle()
            ));
        String response = GSON.toJson(responseList);
        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }

    public Response getAllByAnimalType(int id) {
        LOGGER.debug("Get All Cards By AnimalType");
        List<Card> itemList = cardDao.getByAnimalType(id);
        List<CardResponse> responseList = new ArrayList<>();
        for (Card card : itemList)
            responseList.add(new CardResponse(
                    card.getAnimal().getId(), card.getAnimal().getName(), card.getAnimal().getPhoto(),
                    card.getAnimal().getAge(), card.getAnimal().getBreed(), card.getAnimal().getAnimalType().getId(),
                    card.getAnimal().getSex(), card.getAnimal().getPassport(), card.getAnimal().getDescription(),
                    card.getUser().getId(), card.getUser().getPhone(),
                    card.getUser().getEmail(), card.getUser().getPassword(),
                    card.getCategory().getId(), card.getCategory().getTitle()
            ));
        String response = GSON.toJson(responseList);
        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }

    public Response getAllByCategory(int id) {
        LOGGER.debug("Get All Cards By Category");
        List<Card> itemList = cardDao.getByCategory(id);
        List<CardResponse> responseList = new ArrayList<>();
        for (Card card : itemList)
            responseList.add(new CardResponse(
                    card.getAnimal().getId(), card.getAnimal().getName(), card.getAnimal().getPhoto(),
                    card.getAnimal().getAge(), card.getAnimal().getBreed(), card.getAnimal().getAnimalType().getId(),
                    card.getAnimal().getSex(), card.getAnimal().getPassport(), card.getAnimal().getDescription(),
                    card.getUser().getId(), card.getUser().getPhone(),
                    card.getUser().getEmail(), card.getUser().getPassword(),
                    card.getCategory().getId(), card.getCategory().getTitle()
            ));
        String response = GSON.toJson(responseList);
        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }

    public Response getAllByFilters(List<Integer> idCategoryList, List<Integer> idAnimalTypeList) {
        LOGGER.debug("Get All Cards By Category");
        List<CardResponse> responseList = new ArrayList<>();
        List<Card> itemList = new ArrayList<>();

        for (int i = 0; i < idCategoryList.size(); i++){
            itemList.addAll(cardDao.getByCategory(idCategoryList.get(i)));
        }
        for (int i = 0; i < idAnimalTypeList.size(); i++){
            itemList.addAll(cardDao.getByAnimalType(idAnimalTypeList.get(i)));
        }

        for (Card card : itemList)
            responseList.add(new CardResponse(
                    card.getAnimal().getId(), card.getAnimal().getName(), card.getAnimal().getPhoto(),
                    card.getAnimal().getAge(), card.getAnimal().getBreed(), card.getAnimal().getAnimalType().getId(),
                    card.getAnimal().getSex(), card.getAnimal().getPassport(), card.getAnimal().getDescription(),
                    card.getUser().getId(), card.getUser().getPhone(),
                    card.getUser().getEmail(), card.getUser().getPassword(),
                    card.getCategory().getId(), card.getCategory().getTitle()
            ));

        String response = GSON.toJson(responseList);
        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }

    public Response changeCard(int id, String json) {
        LOGGER.debug("Change Card By id " + id);
        try {
            CardRequest request = ShelterUtils.getClassInstanceFromJson(GSON, json, CardRequest.class);
            Card card = cardDao.getByAnimalId(id);
            Category newCategory = categoryDao.getById(request.getIdCategory());
            AnimalType animalType = animalTypeDao.getById(request.getIdAnimalType());
            card.updateCategory(newCategory);
            card.updateAnimal(request.getNameAnimal(), request.getPhotoAnimal(),
                    request.getAgeAnimal(), request.getBreedAnimal(),
                    animalType, request.getSexAnimal(),
                    request.getPassportAnimal(), request.getDescriptionAnimal());
            cardDao.changeCard(card.getAnimal().getId(), card);
            String response = GSON.toJson(new CardResponse(
                            card.getAnimal().getId(),    card.getAnimal().getName(),
                            card.getAnimal().getPhoto(), card.getAnimal().getAge(),
                            card.getAnimal().getBreed(), card.getAnimal().getAnimalType().getId(),
                            card.getAnimal().getSex(),   card.getAnimal().getPassport(),
                            card.getAnimal().getDescription(),
                            card.getUser().getId(),      card.getUser().getPhone(),
                            card.getUser().getEmail(),   card.getUser().getPassword(),
                            card.getCategory().getId(),  card.getCategory().getTitle()
                    )
            );
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ShelterException ex) {
            return ShelterUtils.failureResponse(ex);
        }
    }

    public Response deleteById(int id, String json) {
        LOGGER.debug("Delete Card By id " + id);
        cardDao.delete(id);
        String response = GSON.toJson(new EmptySuccessResponse());
        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }

}
