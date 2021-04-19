package ru.omsu.imit.novikova.dao;

import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.Human;

public interface HumanDao {

    public Human insert(Human human);

    public Human getById(int id) throws ShelterException;

    public Human getByEmail(String email) throws ShelterException;

    public void changeHuman(int id, Human newHuman) throws ShelterException;

    public void deleteAll();

    public void delete(int id);
}
