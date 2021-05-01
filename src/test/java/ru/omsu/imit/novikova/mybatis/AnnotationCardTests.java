package ru.omsu.imit.novikova.mybatis;

import org.junit.Test;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.*;

public class AnnotationCardTests extends BaseDAOTests {

//    Insert Card

    @Test
    public void testInsertCard() throws ShelterException {
        User user = new User();
        user.setId(1);
        Category category = new Category();
        category.setId(1);
        Animal animal = new Animal(0, "Мурка", "", 1,
                null, new AnimalType(1, ""), Sex.F, null, null);
        Card card = new Card(user, category, animal);
        cardDao.insert(card);
//        Card cardFromDB = cardDao.getById(card.i)
//        Organisation organisationFromDB = organisationDao.getById(card.getId());
//        checkOrganisationFields(card, organisationFromDB);
    }
}
