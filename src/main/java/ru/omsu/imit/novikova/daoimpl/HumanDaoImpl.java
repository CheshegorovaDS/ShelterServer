package ru.omsu.imit.novikova.daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.novikova.dao.HumanDao;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.Human;

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
        return null;
    }

    @Override
    public void changeHuman(int id, Human newHumnan) throws ShelterException {

    }

    @Override
    public void deleteAll() {
//        LOGGER.debug("DAO Delete All People {}");
//        try (SqlSession sqlSession = getSession()) {
//            try {
//                getHumanMapper(sqlSession).deleteAll();
//                getUserMapper(sqlSession).deleteAll();
//            } catch (RuntimeException ex) {
//                LOGGER.debug("Can't delete all People {}", ex);
//                sqlSession.rollback();
//                throw ex;
//            }
//            sqlSession.commit();
//        }
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
