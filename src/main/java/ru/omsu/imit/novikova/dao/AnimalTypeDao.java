package ru.omsu.imit.novikova.dao;

import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.AnimalType;

import java.util.List;

public interface AnimalTypeDao {
    public AnimalType getById(int id) throws ShelterException;
    public List<AnimalType> getAll();
    public void deleteAll();
}
