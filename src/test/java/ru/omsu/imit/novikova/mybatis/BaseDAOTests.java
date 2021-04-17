package ru.omsu.imit.novikova.mybatis;

import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import ru.omsu.imit.novikova.dao.UserDao;
import ru.omsu.imit.novikova.daoimpl.UserDaoImpl;
import ru.omsu.imit.novikova.model.User;
import ru.omsu.imit.novikova.utils.MyBatisUtils;

import static org.junit.Assert.assertEquals;

public class BaseDAOTests {
    protected UserDao userDAO = new UserDaoImpl();
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


    protected void checkUserFields(User user1, User user2) {
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getPhone(), user2.getPhone());
        assertEquals(user1.getEmail(), user2.getEmail());
        assertEquals(user1.getPassword(), user2.getPassword());
    }

}