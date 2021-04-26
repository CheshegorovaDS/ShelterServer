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
import ru.omsu.imit.novikova.utils.ShelterUtils;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CardService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HumanService.class);
    private static final Gson GSON = new GsonBuilder().create();
    private CardDao cardDao = new CardDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private AnimalDao animalDao = new AnimalDaoImpl();
    private CategoryDao categoryDao = new CategoryDaoImpl();

    public Response insert(String json) {
        LOGGER.debug("Insert card " + json);
        try {
            CardRequest request = ShelterUtils.getClassInstanceFromJson(GSON, json, CardRequest.class);
            User user = userDao.getById(request.getIdUser());
            Animal animal = animalDao.getById(request.getIdAnimal());
            Category category = categoryDao.getById(request.getIdCategory());
            Card card = new Card(user, category, animal);
            Card addedCard = cardDao.insert(card);
            String response = GSON.toJson(new CardResponse(
                    addedCard.getAnimal().getId(), addedCard.getAnimal().getName(),
                    addedCard.getAnimal().getPhoto(), addedCard.getAnimal().getAge(),
                    addedCard.getAnimal().getBreed(), addedCard.getAnimal().getAnimalType().getTitle(),
                    addedCard.getAnimal().getSex().name(), addedCard.getAnimal().getPassport(),
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
}
