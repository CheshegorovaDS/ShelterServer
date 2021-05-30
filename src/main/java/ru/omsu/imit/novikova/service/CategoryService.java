package ru.omsu.imit.novikova.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.novikova.dao.CategoryDao;
import ru.omsu.imit.novikova.daoimpl.CategoryDaoImpl;
import ru.omsu.imit.novikova.model.Category;
import ru.omsu.imit.novikova.rest.response.CategoryResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HumanService.class);
    private static final Gson GSON = new GsonBuilder().create();
    private CategoryDao categoryDao = new CategoryDaoImpl();

    public Response getAll() {
        LOGGER.debug("Get All Categories");
        List<Category> itemList = categoryDao.getAll();
        List<CategoryResponse> responseList = new ArrayList<>();
        for (Category category : itemList)
            responseList.add(new CategoryResponse(
                    category.getId(),
                    category.getTitle()
            ));
        String response = GSON.toJson(responseList);
        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }
}
