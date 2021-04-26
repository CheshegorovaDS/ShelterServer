package ru.omsu.imit.novikova.dao;

import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.Animal;

public interface AnimalDao {
    public Animal getById(int id) throws ShelterException;
    public void deleteAll();
}
