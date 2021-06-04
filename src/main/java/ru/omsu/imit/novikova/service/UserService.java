package ru.omsu.imit.novikova.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.novikova.dao.CookieDao;
import ru.omsu.imit.novikova.dao.UserDao;
import ru.omsu.imit.novikova.daoimpl.CookieDaoImpl;
import ru.omsu.imit.novikova.daoimpl.UserDaoImpl;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.Cookie;
import ru.omsu.imit.novikova.model.User;
import ru.omsu.imit.novikova.rest.response.TokenResponse;
import ru.omsu.imit.novikova.utils.ErrorCode;
import ru.omsu.imit.novikova.utils.ShelterUtils;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private static final Gson GSON = new GsonBuilder().create();
    private UserDao userDao = new UserDaoImpl();
    private CookieDao cookieDao = new CookieDaoImpl();

    public Response login(String login, String password) {
        LOGGER.debug("Login User with email = "+ login);
        try {
            User user = userDao.getByEmail(login);
            if (!BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified) {
                throw new ShelterException(ErrorCode.ITEM_NOT_FOUND, login);
            }

            Cookie cookie = new Cookie(user, UUID.randomUUID().toString());
            if (cookieDao.getById(user.getId()) != null) {
                cookieDao.deleteById(user.getId());
            }
            cookieDao.insert(cookie);

            String response = GSON.toJson(new TokenResponse(cookie.getUser().getId(), cookie.getUuid()));
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ShelterException ex) {
            return ShelterUtils.failureResponse(ex);
        }
    }

    public void logout(String uuid) {
        LOGGER.debug("Service logout by uuid {}", uuid);
        try {
            cookieDao.deleteByUUID(uuid);
        } catch (Exception ex) {
            LOGGER.info("Service can't logout user ", ex);
            throw ex;
        }
    }
}
