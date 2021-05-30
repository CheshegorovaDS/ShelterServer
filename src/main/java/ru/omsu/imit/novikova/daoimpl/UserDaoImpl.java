package ru.omsu.imit.novikova.daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.novikova.dao.UserDao;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.User;
import ru.omsu.imit.novikova.utils.ErrorCode;

public class UserDaoImpl extends BaseDAOImpl implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User getById(int id) throws ShelterException {
        try (SqlSession sqlSession = getSession()) {
            User user = getUserMapper(sqlSession).getById(id);
            if(user == null){
                throw new ShelterException(ErrorCode.ITEM_NOT_FOUND, Integer.toString(id));
            }
            return user;
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get User By Id {}", ex);
            throw ex;
        }
    }

    @Override
    public User getByEmail(String email) throws ShelterException {
        try (SqlSession sqlSession = getSession()) {
            User user = getUserMapper(sqlSession).getByEmail(email);
            if(user == null){
                throw new ShelterException(ErrorCode.ITEM_NOT_FOUND, email);
            }
            return user;
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get User By Email {}", ex);
            throw ex;
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO Delete All Users {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Users {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
