package ru.omsu.imit.novikova.dao;

import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.Category;

public interface CategoryDao {
    public Category getById(int id) throws ShelterException;
    public void deleteAll();
}
