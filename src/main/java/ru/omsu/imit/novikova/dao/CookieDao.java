package ru.omsu.imit.novikova.dao;

import ru.omsu.imit.novikova.model.Cookie;

public interface CookieDao {
    Cookie insert(Cookie cookie);
    Cookie getById(int id);
    Cookie getByUUID(String uuid);
    void deleteByUUID(String uuid);
    void deleteById(int id);
    void deleteAll();
}
