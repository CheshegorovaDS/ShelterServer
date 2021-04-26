package ru.omsu.imit.novikova.daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.novikova.dao.CategoryDao;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.Category;
import ru.omsu.imit.novikova.utils.ErrorCode;

public class CategoryDaoImpl extends BaseDAOImpl implements CategoryDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryDaoImpl.class);

    @Override
    public Category getById(int id) throws ShelterException {
        try (SqlSession sqlSession = getSession()) {
            Category category = getCategoryMapper(sqlSession).getById(id);
            if(category == null){
                throw new ShelterException(ErrorCode.ITEM_NOT_FOUND, Integer.toString(id));
            }
            return category;
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Category By Id {}", ex);
            throw ex;
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO Delete All Categories {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getCategoryMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Categories {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
