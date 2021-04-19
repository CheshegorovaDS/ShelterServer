package ru.omsu.imit.novikova.mybatis;

import org.junit.Test;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.Human;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AnnotationsHumanDaoTests extends BaseDAOTests {

    //Insert Human

    @Test
    public void testInsertHuman() throws ShelterException {
        Human human = new Human(0, "80001342422", "dg@mail.ru", "test",
                "Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"));
        humanDao.insert(human);
        Human humanFromDB = humanDao.getById(human.getId());
        checkHumanFields(human, humanFromDB);
    }

    @Test(expected = RuntimeException.class)
    public void testInsertHumanWithNullName() {
        Human human = new Human(0, "80001342422", "dg@mail.ru", "test",
                null, "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"));
        humanDao.insert(human);
    }

    //Update Human

    @Test
    public void testChangeHumanFullName() throws ShelterException {
        Human human = new Human(0, "80001342422", "dg@mail.ru", "test",
                "Дм", "Пупкин", null, Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"));
        humanDao.insert(human);
        Human humanFromDB = humanDao.getById(human.getId());
        checkHumanFields(human, humanFromDB);
        Human human2 = new Human(0, "80001342422", "dg@mail.ru", "test",
                "Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"));
        humanDao.changeHuman(human.getId(), human2);
        humanFromDB = humanDao.getById(human.getId());
        assertEquals("Дмитрий",     humanFromDB.getFirstName());
        assertEquals("Пупкин",    humanFromDB.getLastName());
        assertEquals("Святославович",  humanFromDB.getPatronymic());
    }

    @Test(expected = RuntimeException.class)
    public void testChangeHumanPasswordToNull() throws ShelterException {
        Human human = new Human(0, "80001342422", "dg@mail.ru", "test",
                "Дмитрий", "Пупкин", null, Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"));
        humanDao.insert(human);
        Human humanFromDB = humanDao.getById(human.getId());
        checkHumanFields(human, humanFromDB);
        Human human2 = new Human(0, "80001342422", "dg@mail.ru", null,
                "Дмитрий", "Пупкин", null, Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"));
        humanDao.changeHuman(human.getId(), human2);
    }

    //Select Human

    @Test
    public void testHumanGetByEmail() throws ShelterException {
        Human human = new Human(0, "80001342422", "dg@mail.ru", "test",
                "Дм", "Пупкин", null, Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"));
        humanDao.insert(human);
        Human human2 = new Human(0, "80000000000", "aaaa@mail.ru", "test",
                "Дм", "Пупкин", null, Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"));
        humanDao.insert(human2);
        Human humanFromDB = humanDao.getByEmail(human.getUser().getEmail());
        checkHumanFields(human, humanFromDB);
    }

    @Test(expected = ShelterException.class)
    public void testHumanGetByEmailWithoutThisEmail() throws ShelterException {
        Human human = humanDao.getByEmail("not_email@mail.ru");
    }

    //Delete Voter

    @Test(expected = ShelterException.class)
    public void testDeleteHuman() throws ShelterException {
        Human human = new Human(0, "80001342422", "dg@mail.ru", "test",
                "Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"));
        humanDao.insert(human);
        Human humanFromDB = humanDao.getById(human.getId());
        checkHumanFields(human, humanFromDB);
        humanDao.delete(human.getId());
        humanFromDB = humanDao.getById(human.getId());
        assertNull(humanFromDB);
    }

}
