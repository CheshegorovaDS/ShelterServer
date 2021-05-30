package ru.omsu.imit.novikova.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.novikova.dao.AnimalTypeDao;
import ru.omsu.imit.novikova.daoimpl.AnimalTypeDaoImpl;
import ru.omsu.imit.novikova.model.AnimalType;
import ru.omsu.imit.novikova.rest.response.AnimalTypeResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class AnimalTypeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HumanService.class);
    private static final Gson GSON = new GsonBuilder().create();
    private AnimalTypeDao animalTypeDao = new AnimalTypeDaoImpl();

    public Response getAll() {
        LOGGER.debug("Get All Animal Types");
        List<AnimalType> itemList = animalTypeDao.getAll();
        List<AnimalTypeResponse> responseList = new ArrayList<>();
        for (AnimalType animalType : itemList)
            responseList.add(new AnimalTypeResponse(
                    animalType.getId(),
                    animalType.getTitle()
            ));
        String response = GSON.toJson(responseList);
        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }
}
