package ru.omsu.imit.novikova.dao;

import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.User;

public interface UserDao {
    public User getById(int id) throws ShelterException;
    public User getByEmail(String email) throws ShelterException;
    public void deleteAll();
}
