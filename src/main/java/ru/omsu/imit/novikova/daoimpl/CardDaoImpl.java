package ru.omsu.imit.novikova.daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.novikova.dao.CardDao;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.Card;

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
    public Card getById(int idUser, int idAnimal) throws ShelterException {
        return null;
    }

    @Override
    public List<Card> getAll() {
        return null;
    }

    @Override
    public List<Card> getByCategory() {
        return null;
    }

    @Override
    public List<Card> getByAnimalType() {
        return null;
    }

    @Override
    public List<Card> getByUserId() {
        return null;
    }

    @Override
    public void changeCard(int id, Card newCard) throws ShelterException {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
