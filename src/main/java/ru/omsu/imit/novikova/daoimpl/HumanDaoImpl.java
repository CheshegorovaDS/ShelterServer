package ru.omsu.imit.novikova.daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.novikova.dao.HumanDao;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.Human;
import ru.omsu.imit.novikova.utils.ErrorCode;

public class HumanDaoImpl extends BaseDAOImpl implements HumanDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(HumanDaoImpl.class);

    @Override
    public Human insert(Human human) {
        LOGGER.debug("DAO Insert Human {}", human);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).insert(human.getUser());
                getHumanMapper(sqlSession).insert(human);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Human {}, {}", human, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return human;
    }

    @Override
    public Human getById(int id) throws ShelterException {
        try (SqlSession sqlSession = getSession()) {
            Human human = getHumanMapper(sqlSession).getById(id);
            if(human == null){
                throw new ShelterException(ErrorCode.ITEM_NOT_FOUND, Integer.toString(id));
            }
            return human;
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Human By Id {}", ex);
            throw ex;
        }
    }

    @Override
    public Human getByEmail(String email) throws ShelterException {
        try (SqlSession sqlSession = getSession()) {
            Human human = getHumanMapper(sqlSession).getByEmail(email);
            if(human == null){
                throw new ShelterException(ErrorCode.ITEM_NOT_FOUND, email);
            }
            return human;
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Human By Email {}", ex);
            throw ex;
        }
    }

    @Override
    public void changeHuman(int id, Human newHuman) throws ShelterException {
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).changeUser(id, newHuman.getUser());
                getHumanMapper(sqlSession).changeHuman(id, newHuman);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't change Human {} {} ", id, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO Delete All People {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getHumanMapper(sqlSession).deleteAll();
                getUserMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all People {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession sqlSession = getSession()) {
            try {
                getHumanMapper(sqlSession).delete(id);
                getUserMapper(sqlSession).delete(id);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete Human by id {} {}", id, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
