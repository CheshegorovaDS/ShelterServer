package ru.omsu.imit.novikova.shelter.clienttests;

import org.junit.Test;
import ru.omsu.imit.novikova.model.Sex;
import ru.omsu.imit.novikova.rest.request.CardRequest;
import ru.omsu.imit.novikova.rest.request.HumanRequest;
import ru.omsu.imit.novikova.rest.response.CardResponse;
import ru.omsu.imit.novikova.rest.response.HumanResponse;
import ru.omsu.imit.novikova.utils.ErrorCode;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CardClientTest extends BaseClientTest{

    //    Insert

    @Test
    public void testInsertHuman() {
        HumanRequest humanRequest = new HumanRequest("Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        HumanResponse response = addHuman(humanRequest, ErrorCode.SUCCESS);

        CardRequest request = new CardRequest(response.getId(), 1, "Мурка", "", 1,
                null, 1, Sex.F, null, null);
        addCard(request, ErrorCode.SUCCESS);
    }

    //    Get

    @Test
    public void testGetByAnimalId() {
        HumanRequest humanRequest = new HumanRequest("Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        HumanResponse humanResponse = getHumanById(addHuman(humanRequest, ErrorCode.SUCCESS).getId() , ErrorCode.SUCCESS);
        checkHumanFields(humanRequest,humanResponse);

        CardRequest request = new CardRequest(humanResponse.getId(),1, "Мурка", "", 1,
                null, 1, Sex.F, null, null);
        CardResponse cardResponse = getCardById(addCard(request, ErrorCode.SUCCESS).getIdAnimal(), ErrorCode.SUCCESS);
        checkCardFields(request, cardResponse);
    }

    @Test
    public void testGetByIdWithoutCard() {
        getCardById(100 , ErrorCode.ITEM_NOT_FOUND);
    }

    @Test
    public void testGetAllCards() {
        addListCards();
        getAllCard(5, ErrorCode.SUCCESS);
    }

    @Test
    public void testGetAllWhenEmpty() {
        getAllCard(0, ErrorCode.SUCCESS);
    }

    @Test
    public void testGetAllByUserCards() {
        int id = addListCards().get(0).getIdUser();
        getAllCardByUser(id, 3, ErrorCode.SUCCESS);
    }

    @Test
    public void testGetAllByUserWhenEmpty() {
        getAllCardByUser(2, 0, ErrorCode.SUCCESS);
    }

    @Test
    public void testGetAllByAnimalTypeCards() {
        int id = addListCards().get(0).getAnimalType();
        getAllCardByAnimalType(id, 2, ErrorCode.SUCCESS);
    }

    @Test
    public void testGetAllByAnimalTypeWhenEmpty() {
        getAllCardByAnimalType(0, 0, ErrorCode.SUCCESS);
    }

    @Test
    public void testGetAllByCategoryCards() {
        addListCards();
        getAllCardByCategory(1, 3, ErrorCode.SUCCESS);
    }

    @Test
    public void testGetAllByCategoryWhenEmpty() {
        getAllCardByCategory(0, 0, ErrorCode.SUCCESS);
    }

    @Test
    public void testGetAllByString() {
        addListCards();
        getAllCardByString("Му", 3, ErrorCode.SUCCESS);
    }

    @Test
    public void testGetAllByStringWhenEmpty() {
        getAllCardByString("Ъ", 0, ErrorCode.SUCCESS);
    }


    // Update

    @Test
    public void testChangeCard() {
        HumanRequest humanRequest = new HumanRequest("Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        HumanResponse humanResponse = addHuman(humanRequest, ErrorCode.SUCCESS);

        CardRequest request = new CardRequest(humanResponse.getId(), 1, "Мурка", "", 1,
                null, 1, Sex.F, null, null);
        CardResponse response = addCard(request, ErrorCode.SUCCESS);

        CardRequest request2 = new CardRequest(humanResponse.getId(), 3, "Мурка", "", 2,
                null, 1, Sex.F, null, "Игривая.");
        changeCard(response.getIdAnimal(), request2, ErrorCode.SUCCESS);
        checkCardFields(request2, getCardById(response.getIdAnimal(), ErrorCode.SUCCESS));
    }

    @Test
    public void testChangeCardWithoutCard(){
        HumanRequest humanRequest = new HumanRequest("Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        HumanResponse humanResponse = addHuman(humanRequest, ErrorCode.SUCCESS);

        CardRequest request = new CardRequest(humanResponse.getId(), 1, "Мурка", "", 1,
                null, 1, Sex.F, null, null);
        changeCard(1, request, ErrorCode.ITEM_NOT_FOUND);
    }

    // Delete

    @Test
    public void testDeleteById() {
        HumanRequest humanRequest = new HumanRequest("Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        HumanResponse humanResponse = addHuman(humanRequest, ErrorCode.SUCCESS);

        CardRequest request = new CardRequest(humanResponse.getId(),1, "Мурка", "", 1,
                null, 1, Sex.F, null, null);
        CardResponse response = addCard(request, ErrorCode.SUCCESS);
        deleteCard(response.getIdAnimal(), ErrorCode.SUCCESS);
    }

    @Test
    public void testDeleteWithoutCard() {
        deleteCard(100, ErrorCode.SUCCESS);
    }

    private List<CardResponse> addListCards() {
        List<CardResponse> list = new ArrayList<>();
        HumanRequest humanRequest = new HumanRequest("Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        HumanResponse humanResponse = getHumanById(addHuman(humanRequest, ErrorCode.SUCCESS).getId() , ErrorCode.SUCCESS);
        CardRequest request = new CardRequest(humanResponse.getId(),1, "Мурка", "", 1,
                null, 1, Sex.F, null, null);
        CardResponse response1 = getCardById(addCard(request, ErrorCode.SUCCESS).getIdAnimal(), ErrorCode.SUCCESS);
//        list.add(response1);

        HumanRequest humanRequest2 = new HumanRequest("Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"),
                "80001342433", "aaa@mail.ru", "test");
        HumanResponse humanResponse2 = getHumanById(addHuman(humanRequest2, ErrorCode.SUCCESS).getId() , ErrorCode.SUCCESS);
        CardRequest request2 = new CardRequest(humanResponse2.getId(),1, "Мирка", "", 1,
                null, 1, Sex.F, null, null);
        CardResponse response2 = getCardById(addCard(request2, ErrorCode.SUCCESS).getIdAnimal(), ErrorCode.SUCCESS);
//        list.add(response2);

        HumanRequest humanRequest3 = new HumanRequest("Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"),
                "80001333422", "bbb@mail.ru", "test");
        HumanResponse humanResponse3 = getHumanById(addHuman(humanRequest3, ErrorCode.SUCCESS).getId() , ErrorCode.SUCCESS);
        CardRequest request3 = new CardRequest(humanResponse3.getId(),1, "Мерка", "", 1,
                "Мулвлв", 2, Sex.F, null, null);
        CardResponse response3 = getCardById(addCard(request3, ErrorCode.SUCCESS).getIdAnimal(), ErrorCode.SUCCESS);
        list.add(response3);

        CardRequest request4 = new CardRequest(humanResponse3.getId(),2, "Мурка", "", 1,
                null, 1, Sex.F, null, null);
        CardResponse response4 = getCardById(addCard(request4, ErrorCode.SUCCESS).getIdAnimal(), ErrorCode.SUCCESS);
//        list.add(response4);

        CardRequest request5 = new CardRequest(humanResponse3.getId(),2, "Морка", "", 1,
                null, 2, Sex.F, null, null);
        CardResponse response5 = getCardById(addCard(request5, ErrorCode.SUCCESS).getIdAnimal(), ErrorCode.SUCCESS);
//        list.add(response5);

        return list;
    }

}
