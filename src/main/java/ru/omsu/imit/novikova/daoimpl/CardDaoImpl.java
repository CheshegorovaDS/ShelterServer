package ru.omsu.imit.novikova.daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.novikova.dao.CardDao;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.Card;
import ru.omsu.imit.novikova.utils.ErrorCode;

import java.util.List;

public class CardDaoImpl extends BaseDAOImpl implements CardDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(CardDaoImpl.class);

    @Override
    public Card insert(Card card) {
        LOGGER.debug("DAO Insert Cards {}", card);
        try (SqlSession sqlSession = getSession()) {
            try {
                getAnimalMapper(sqlSession).insert(card.getAnimal());
                getCardMapper(sqlSession).insert(card);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Cards {}, {}", card, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return card;
    }

    @Override
    public Card getByAnimalId(int idAnimal) throws ShelterException {
        try (SqlSession sqlSession = getSession()) {
            Card card = getCardMapper(sqlSession).getByAnimalId(idAnimal);
            if(card == null){
                throw new ShelterException(ErrorCode.ITEM_NOT_FOUND,Integer.toString(idAnimal));
            }
            return card;
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Card By Id {}", ex);
            throw ex;
        }
    }

    @Override
    public Card getById(int idUser, int idAnimal) throws ShelterException {
        try (SqlSession sqlSession = getSession()) {
            Card card = getCardMapper(sqlSession).getById(idUser, idAnimal);
            if(card == null){
                throw new ShelterException(ErrorCode.ITEM_NOT_FOUND,Integer.toString(idUser, idAnimal));
            }
            return card;
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Card By Id {}", ex);
            throw ex;
        }
    }

    @Override
    public List<Card> getAll() {
        try (SqlSession sqlSession = getSession()) {
            return getCardMapper(sqlSession).getAll();
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get all Cards {}", ex);
            throw ex;
        }
    }

    @Override
    public List<Card> getByCategory(int idCategory) {
        try (SqlSession sqlSession = getSession()) {
            return getCardMapper(sqlSession).getAllByCategory(idCategory);
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Cards by Category {}", ex);
            throw ex;
        }
    }

    @Override
    public List<Card> getByAnimalType(int idAnimalType) {
        try (SqlSession sqlSession = getSession()) {
            return getCardMapper(sqlSession).getAllByAnimalType(idAnimalType);
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Cards by AnimalType {}", ex);
            throw ex;
        }
    }

    @Override
    public List<Card> getByUserId(int idUser) {
        try (SqlSession sqlSession = getSession()) {
            return getCardMapper(sqlSession).getAllByUser(idUser);
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Cards by User {}", ex);
            throw ex;
        }
    }

    @Override
    public List<Card> getByString(String str) {
        try (SqlSession sqlSession = getSession()) {
            return getCardMapper(sqlSession).getAllByString(str + "%");
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Cards by String {}", ex);
            throw ex;
        }
    }

    @Override
    public void changeCard(int idAnimal, Card newCard) throws ShelterException {
        try (SqlSession sqlSession = getSession()) {
            try {
                getAnimalMapper(sqlSession).changeAnimal(idAnimal, newCard.getAnimal());
                getCardMapper(sqlSession).changeCard(idAnimal, newCard);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't change Card {} {} ", idAnimal, ex);
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
                getCardMapper(sqlSession).delete(id);
                getAnimalMapper(sqlSession).delete(id);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete Card by id {} {}", id, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO Delete All Cards {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getCardMapper(sqlSession).deleteAll();
                getAnimalMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Cards {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
