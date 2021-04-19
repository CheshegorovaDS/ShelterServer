package ru.omsu.imit.novikova.mybatis;

import org.junit.Test;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.Human;

import java.sql.Date;

public class AnnotationsHumanDaoTests extends BaseDAOTests {

    //Insert Human

    @Test
    public void testInsertHuman() throws ShelterException {
        Human human = new Human(0, "80001342422", "dg@mail.ru", "test",
                "Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"));
        humanDao.insert(human);
//        Voter voterFromDB = voterDAO.getById(voter.getId());
//        checkVoterFields(voter, voterFromDB);
    }

    @Test(expected = RuntimeException.class)
    public void testInsertHumanWithNullName() {
        Human human = new Human(0, "80001342422", "dg@mail.ru", "test",
                null, "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"));
        humanDao.insert(human);
    }
}
