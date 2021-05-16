package ru.omsu.imit.novikova.dao;

import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.AnimalType;

public interface AnimalTypeDao {
    public AnimalType getById(int id) throws ShelterException;
    public void deleteAll();
}
