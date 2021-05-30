package ru.omsu.imit.novikova.dao;

import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.Category;

import java.util.List;

public interface CategoryDao {
    public Category getById(int id) throws ShelterException;
    public List<Category> getAll();
    public void deleteAll();
}
