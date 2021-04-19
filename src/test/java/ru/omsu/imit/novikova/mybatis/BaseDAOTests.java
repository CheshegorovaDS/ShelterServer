package ru.omsu.imit.novikova.mybatis;

import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import ru.omsu.imit.novikova.dao.HumanDao;
import ru.omsu.imit.novikova.dao.UserDao;
import ru.omsu.imit.novikova.daoimpl.HumanDaoImpl;
import ru.omsu.imit.novikova.daoimpl.UserDaoImpl;
import ru.omsu.imit.novikova.model.Human;
import ru.omsu.imit.novikova.model.User;
import ru.omsu.imit.novikova.utils.MyBatisUtils;

import static org.junit.Assert.assertEquals;

public class BaseDAOTests {
    protected UserDao userDAO = new UserDaoImpl();
    protected HumanDao humanDao = new HumanDaoImpl();

    @BeforeClass()
    public static void init() {
        Assume.assumeTrue(MyBatisUtils.initSqlSessionFactory());
    }

    @Before()
    public void clearDatabase() {
        humanDao.deleteAll();
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

}