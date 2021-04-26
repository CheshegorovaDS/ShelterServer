package ru.omsu.imit.novikova.mybatis;

import org.junit.Test;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.Animal;
import ru.omsu.imit.novikova.model.Card;
import ru.omsu.imit.novikova.model.Category;
import ru.omsu.imit.novikova.model.User;

public class AnnotationCardTests extends BaseDAOTests {

//    Insert Card

    @Test
    public void testInsertCard() throws ShelterException {
        User user = new User();
        user.setId(23);
        Category category = new Category();
        category.setId(1);
        Animal animal = new Animal();
        animal.setId(2);
        Card card = new Card(user, category, animal);
        cardDao.insert(card);
//        Organisation organisationFromDB = organisationDao.getById(card.getId());
//        checkOrganisationFields(card, organisationFromDB);
    }
}
