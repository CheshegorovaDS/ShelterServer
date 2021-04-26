package ru.omsu.imit.novikova.mybatis;

import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import ru.omsu.imit.novikova.dao.CardDao;
import ru.omsu.imit.novikova.dao.HumanDao;
import ru.omsu.imit.novikova.dao.OrganisationDao;
import ru.omsu.imit.novikova.dao.UserDao;
import ru.omsu.imit.novikova.daoimpl.CardDaoImpl;
import ru.omsu.imit.novikova.daoimpl.HumanDaoImpl;
import ru.omsu.imit.novikova.daoimpl.OrganisationDaoImpl;
import ru.omsu.imit.novikova.daoimpl.UserDaoImpl;
import ru.omsu.imit.novikova.model.*;
import ru.omsu.imit.novikova.utils.MyBatisUtils;

import static org.junit.Assert.assertEquals;

public class BaseDAOTests {
    protected UserDao userDAO = new UserDaoImpl();
    protected HumanDao humanDao = new HumanDaoImpl();
    protected OrganisationDao organisationDao = new OrganisationDaoImpl();
    protected CardDao cardDao = new CardDaoImpl();

    @BeforeClass()
    public static void init() {
        Assume.assumeTrue(MyBatisUtils.initSqlSessionFactory());
    }

    @Before()
    public void clearDatabase() {
//        humanDao.deleteAll();
//        organisationDao.deleteAll();
//        cardDao.deleteAll();
    }


    protected void checkUserFields(User user1, User user2) {
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getPhone(), user2.getPhone());
        assertEquals(user1.getEmail(), user2.getEmail());
        assertEquals(user1.getPassword(), user2.getPassword());
    }

    protected void checkHumanFields(Human human1, Human human2) {
        assertEquals(human1.getId(), human2.getId());
        assertEquals(human1.getUser().getPhone(), human2.getUser().getPhone());
        assertEquals(human1.getUser().getEmail(), human2.getUser().getEmail());
        assertEquals(human1.getUser().getPassword(), human2.getUser().getPassword());
        assertEquals(human1.getFirstName(), human2.getFirstName());
        assertEquals(human1.getLastName(), human2.getLastName());
        assertEquals(human1.getPatronymic(), human2.getPatronymic());
        assertEquals(human1.getBirthdate(), human2.getBirthdate());
        assertEquals(human1.getCountry(), human2.getCountry());
        assertEquals(human1.getCity(), human2.getCity());
        assertEquals(human1.getRegistrationDate(), human2.getRegistrationDate());
    }

    protected void checkOrganisationFields(Organisation organisation1, Organisation organisation2) {
        assertEquals(organisation1.getId(), organisation2.getId());
        assertEquals(organisation1.getUser().getPhone(), organisation2.getUser().getPhone());
        assertEquals(organisation1.getUser().getEmail(), organisation2.getUser().getEmail());
        assertEquals(organisation1.getUser().getPassword(), organisation2.getUser().getPassword());
        assertEquals(organisation1.getTIN(), organisation2.getTIN());
        assertEquals(organisation1.getTitle(), organisation2.getTitle());
        assertEquals(organisation1.getAdditionalInfo(), organisation2.getAdditionalInfo());
        assertEquals(organisation1.getRegistrationDate(), organisation2.getRegistrationDate());
    }

    protected void checkAnimalFields(Animal animal1, Animal animal2) {
        assertEquals(animal1.getId(), animal2.getId());
        assertEquals(animal1.getName(), animal2.getName());
        assertEquals(animal1.getPhoto(), animal2.getPhoto());
        assertEquals(animal1.getAge(), animal2.getAge());
        assertEquals(animal1.getBreed(), animal2.getBreed());
        assertEquals(animal1.getPassport(), animal2.getPassport());
        assertEquals(animal1.getDescription(), animal2.getDescription());
        assertEquals(animal1.getAnimalType(), animal2.getAnimalType());
        assertEquals(animal1.getSex(), animal2.getSex());
    }

    protected void checkCardFields(Card card1, Card card2) {
        checkUserFields(card1.getUser(), card2.getUser());
        checkAnimalFields(card1.getAnimal(), card2.getAnimal());
        assertEquals(card1.getCategory().getId(), card2.getCategory().getId());
        assertEquals(card1.getCategory().getTitle(), card2.getCategory().getTitle());
    }

}