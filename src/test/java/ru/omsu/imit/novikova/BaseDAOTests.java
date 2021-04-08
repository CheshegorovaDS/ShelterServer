package ru.omsu.imit.novikova;

import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import ru.omsu.imit.novikova.utils.MyBatisUtils;

public class BaseDAOTests {
//    protected UserDao userDAO = new UserDaoImpl();
//    protected VoterDao voterDAO = new VoterDaoImpl();
//    protected CandidateDao candidateDAO = new CandidateDaoImpl();
//    protected BulletinDao bulletinDAO = new BulletinDaoImpl();

    @BeforeClass()
    public static void init() {
        Assume.assumeTrue(MyBatisUtils.initSqlSessionFactory());
    }

//    @Before()
//    public void clearDatabase() {
//        voterDAO.deleteAll();
//    }


//    protected void checkUserFields(User user1, User user2) {
//        assertEquals(user1.getId(), user2.getId());
//        assertEquals(user1.getFirstName(), user2.getFirstName());
//        assertEquals(user1.getLastName(), user2.getLastName());
//        assertEquals(user1.getPatronymic(), user2.getPatronymic());
//        assertEquals(user1.getBirthdate(), user2.getBirthdate());
//        assertEquals(user1.getPassport(), user2.getPassport());
//        assertEquals(user1.getCity(), user2.getCity());
//        assertEquals(user1.getStreet(), user2.getStreet());
//        assertEquals(user1.getHouse(), user2.getHouse());
//        assertEquals(user1.getFlat(), user2.getFlat());
//    }

}