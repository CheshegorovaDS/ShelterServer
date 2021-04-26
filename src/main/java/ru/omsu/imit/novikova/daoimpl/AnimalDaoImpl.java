package ru.omsu.imit.novikova.daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.novikova.dao.AnimalDao;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.Animal;
import ru.omsu.imit.novikova.utils.ErrorCode;

public class AnimalDaoImpl extends BaseDAOImpl implements AnimalDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnimalDaoImpl.class);

    @Override
    public Animal getById(int id) throws ShelterException {
        try (SqlSession sqlSession = getSession()) {
            Animal animal = getAnimalMapper(sqlSession).getById(id);
            if(animal == null){
                throw new ShelterException(ErrorCode.ITEM_NOT_FOUND, Integer.toString(id));
            }
            return animal;
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Animal By Id {}", ex);
            throw ex;
        }
    }

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
