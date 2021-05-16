package ru.omsu.imit.novikova.mybatis;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AnnotationCardTests extends BaseDAOTests {

//    Insert Card

    @Test
    public void testInsertCard() throws ShelterException {
        Category category = new Category(1, "Search Home");
        Animal animal = new Animal(0, "Мурка", "", 1,
                null, new AnimalType(1, "Cat"), Sex.F, null, null);
        Card card = new Card(insertUser(), category, animal);
        cardDao.insert(card);
        Card cardFromDB = cardDao.getById(card.getUser().getId(), card.getAnimal().getId());
        checkCardFields(card, cardFromDB);
    }

    @Test(expected = RuntimeException.class)
    public void testInsertCardWithNullAnimal() throws ShelterException {
        Category category = new Category(1, "Search Home");
        Animal animal = null;
        Card card = new Card(insertUser(), category, animal);
        cardDao.insert(card);
    }

//    Update Card

    @Test
    public void testChangeCard() throws ShelterException {
        Category category = new Category(1, "Search Home");
        User user = insertUser();
        Animal animal = new Animal(0, "Мурка", "", 1,
                null, new AnimalType(1, "Cat"), Sex.F, null, null);
        Card card = new Card(user, category, animal);
        cardDao.insert(card);
        Card cardFromDB = cardDao.getById(card.getUser().getId(), card.getAnimal().getId());
        checkCardFields(card, cardFromDB);
        animal.setBreed("персидская");
        Category category2 = new Category(2, "Lost");
        Card card2 = new Card(user, category2, animal);
        cardDao.changeCard(card.getAnimal().getId(), card2);
        cardFromDB = cardDao.getById(card.getUser().getId(), card.getAnimal().getId());
        TestCase.assertEquals(2,     cardFromDB.getCategory().getId());
        assertEquals("Lost",    cardFromDB.getCategory().getTitle());
        assertEquals("персидская", cardFromDB.getAnimal().getBreed());
    }

    @Test(expected = RuntimeException.class)
    public void testChangeAnimalNameToNull() throws ShelterException {
        Category category = new Category(1, "Search Home");
        User user = insertUser();
        Animal animal = new Animal(0, "Мурка", "", 1,
                null, new AnimalType(1, "Cat"), Sex.F, null, null);
        Card card = new Card(user, category, animal);
        cardDao.insert(card);
        Card cardFromDB = cardDao.getById(card.getUser().getId(), card.getAnimal().getId());
        checkCardFields(card, cardFromDB);
        animal.setName(null);
        Category category2 = new Category(2, "Lost");
        Card card2 = new Card(user, category2, animal);
        cardDao.changeCard(card.getAnimal().getId(), card2);
    }

    //Select

    @Test(expected = ShelterException.class)
    public void testGetCardByIdWithoutId() throws ShelterException {
        cardDao.getById(4, 4);
    }

    @Test
    public void testGetAllCards() throws ShelterException {
        Category category = new Category(1, "Search Home");
        User user = insertUser();
        Animal animal1 = new Animal(0, "Мурка", "", 1,
                null, new AnimalType(1, "Cat"), Sex.F, null, null);
        Card card1 = new Card(user, category, animal1);
        cardDao.insert(card1);

        Animal animal2 = new Animal(0, animal1.getName(), animal1.getPhoto(), animal1.getAge(),
                animal1.getBreed(), animal1.getAnimalType(), animal1.getSex(), animal1.getPassport(), animal1.getDescription());
        User user2 = insertUser("80001342222", "dgww@mail.ru");
        Card card2 = new Card(user2, category, animal2);
        cardDao.insert(card2);
        List<Card> list = new ArrayList<>();
        list.add(card1);
        list.add(card2);
        List<Card> list2 = cardDao.getAll();
        assertEquals(list, list2);
    }

    @Test
    public void testGetAllCardsWithoutCards() throws ShelterException {
        List<Card> list = new ArrayList<>();
        List<Card> list2 = cardDao.getAll();
        assertEquals(list, list2);
    }

    @Test
    public void testGetCardsByCategory() throws ShelterException {
        Category category = new Category(1, "Search Home");
        User user = insertUser();
        Animal animal1 = new Animal(0, "Мурка", "", 1,
                null, new AnimalType(1, "Cat"), Sex.F, null, null);
        Card card1 = new Card(user, category, animal1);
        cardDao.insert(card1);

        Animal animal2 = new Animal(0, animal1.getName(), animal1.getPhoto(), animal1.getAge(),
                animal1.getBreed(), animal1.getAnimalType(), animal1.getSex(), animal1.getPassport(), animal1.getDescription());
        User user2 = insertUser("80001342222", "dgww@mail.ru");
        Card card2 = new Card(user2, category, animal2);
        cardDao.insert(card2);

        Category category3 = new Category(2, "Lost");
        Animal animal3 = new Animal(0, animal1.getName(), animal1.getPhoto(), animal1.getAge(),
                animal1.getBreed(), animal1.getAnimalType(), animal1.getSex(), animal1.getPassport(), animal1.getDescription());
        User user3 = insertUser("80001442222", "dgqw@mail.ru");
        Card card3 = new Card(user3, category3, animal3);
        cardDao.insert(card3);

        List<Card> list = new ArrayList<>();
        list.add(card1);
        list.add(card2);
        List<Card> list2 = cardDao.getByCategory(1);
        assertEquals(list, list2);
    }

    @Test
    public void testGetCardsByCategoryWithoutCards() {
        List<Card> list = new ArrayList<>();
        List<Card> list2 = cardDao.getByCategory(0);
        assertEquals(list, list2);
    }

    @Test
    public void testGetCardsByAnimalType() throws ShelterException {
        Category category = new Category(1, "Search Home");
        User user = insertUser();
        Animal animal1 = new Animal(0, "Мурка", "", 1,
                null, new AnimalType(1, "Cat"), Sex.F, null, null);
        Card card1 = new Card(user, category, animal1);
        cardDao.insert(card1);

        Animal animal2 = new Animal(0, animal1.getName(), animal1.getPhoto(), animal1.getAge(),
                animal1.getBreed(), animal1.getAnimalType(), animal1.getSex(), animal1.getPassport(), animal1.getDescription());
        User user2 = insertUser("80001342222", "dgww@mail.ru");
        Card card2 = new Card(user2, category, animal2);
        cardDao.insert(card2);

        Category category3 = new Category(2, "Lost");
        Animal animal3 = new Animal(0, animal1.getName(), animal1.getPhoto(), animal1.getAge(),
                animal1.getBreed(), new AnimalType(2, "Dog"), animal1.getSex(), animal1.getPassport(), animal1.getDescription());
        User user3 = insertUser("80001442222", "dgqw@mail.ru");
        Card card3 = new Card(user3, category3, animal3);
        cardDao.insert(card3);

        List<Card> list = new ArrayList<>();
        list.add(card3);
        List<Card> list2 = cardDao.getByAnimalType(2);
        assertEquals(list, list2);
    }

    @Test
    public void testGetCardsByAnimalTypeWithoutCards() {
        List<Card> list = new ArrayList<>();
        List<Card> list2 = cardDao.getByAnimalType(1);
        assertEquals(list, list2);
    }

    @Test
    public void testGetCardsByUser() throws ShelterException {
        Category category = new Category(1, "Search Home");
        User user = insertUser();
        Animal animal1 = new Animal(0, "Мурка", "", 1,
                null, new AnimalType(1, "Cat"), Sex.F, null, null);
        Card card1 = new Card(user, category, animal1);
        cardDao.insert(card1);

        Animal animal2 = new Animal(0, animal1.getName(), animal1.getPhoto(), animal1.getAge(),
                animal1.getBreed(), animal1.getAnimalType(), animal1.getSex(), animal1.getPassport(), animal1.getDescription());
        Card card2 = new Card(user, category, animal2);
        cardDao.insert(card2);

        Category category3 = new Category(2, "Lost");
        Animal animal3 = new Animal(0, animal1.getName(), animal1.getPhoto(), animal1.getAge(),
                animal1.getBreed(), new AnimalType(2, "Dog"), animal1.getSex(), animal1.getPassport(), animal1.getDescription());
        User user3 = insertUser("80001442222", "dgqw@mail.ru");
        Card card3 = new Card(user3, category3, animal3);
        cardDao.insert(card3);

        List<Card> list = new ArrayList<>();
        list.add(card1);
        list.add(card2);
        List<Card> list2 = cardDao.getByUserId(user.getId());
        assertEquals(list, list2);
    }

    @Test
    public void testGetCardsByUserWithoutCards() {
        List<Card> list = new ArrayList<>();
        List<Card> list2 = cardDao.getByUserId(1);
        assertEquals(list, list2);
    }

    //Delete Human

    @Test(expected = ShelterException.class)
    public void testDeleteCard() throws ShelterException {
        Category category = new Category(1, "Search Home");
        Animal animal = new Animal(0, "Мурка", "", 1,
                null, new AnimalType(1, "Cat"), Sex.F, null, null);
        Card card = new Card(insertUser(), category, animal);
        cardDao.insert(card);
        Card cardFromDB = cardDao.getById(card.getUser().getId(), card.getAnimal().getId());
        checkCardFields(card, cardFromDB);
        cardDao.delete(card.getAnimal().getId());
        cardFromDB = cardDao.getById(card.getUser().getId(), card.getAnimal().getId());
        assertNull(cardFromDB);
    }

    private User insertUser() throws ShelterException {
        Human human = new Human(0, "80001342422", "dg@mail.ru", "test",
                "Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"));
        humanDao.insert(human);
        Human humanFromDB = humanDao.getById(human.getId());
        return humanFromDB.getUser();
    }

    private User insertUser(String phone, String email) throws ShelterException {
        Human human = new Human(0, phone, email, "test",
                "Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"));
        humanDao.insert(human);
        Human humanFromDB = humanDao.getById(human.getId());
        return humanFromDB.getUser();
    }

}
