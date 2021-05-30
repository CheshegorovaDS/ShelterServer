package ru.omsu.imit.novikova.daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.novikova.dao.AnimalTypeDao;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.AnimalType;
import ru.omsu.imit.novikova.utils.ErrorCode;

import java.util.List;

public class AnimalTypeDaoImpl extends BaseDAOImpl implements AnimalTypeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnimalTypeDaoImpl.class);

    @Override
    public AnimalType getById(int id) throws ShelterException {
        try (SqlSession sqlSession = getSession()) {
            AnimalType animalType = getAnimalTypeMapper(sqlSession).getById(id);
            if(animalType == null){
                throw new ShelterException(ErrorCode.ITEM_NOT_FOUND, Integer.toString(id));
            }
            return animalType;
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get AnimalType By Id {}", ex);
            throw ex;
        }
    }

    @Override
    public List<AnimalType> getAll() {
        try (SqlSession sqlSession = getSession()) {
            return getAnimalTypeMapper(sqlSession).getAll();
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get all Animal Types {}", ex);
            throw ex;
        }
    }

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
