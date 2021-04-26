package ru.omsu.imit.novikova.daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.novikova.dao.AnimalTypeDao;

public class AnimalTypeDaoImpl extends BaseDAOImpl implements AnimalTypeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnimalTypeDaoImpl.class);

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO Delete All AnimalTypes {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getAnimalTypeMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all AnimalTypes {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
