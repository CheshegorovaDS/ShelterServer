package ru.omsu.imit.novikova.daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.novikova.dao.AnimalDao;

public class AnimalDaoImpl extends BaseDAOImpl implements AnimalDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnimalDaoImpl.class);

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO Delete All Animals {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getAnimalMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Animals {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
