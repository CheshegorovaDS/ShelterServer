package ru.omsu.imit.novikova.shelter.clienttests;

import org.junit.Test;
import ru.omsu.imit.novikova.rest.request.HumanRequest;
import ru.omsu.imit.novikova.rest.response.HumanResponse;
import ru.omsu.imit.novikova.utils.ErrorCode;

import java.sql.Date;

public class HumanClientTest extends BaseClientTest {

//    Insert

    @Test
    public void testInsertHuman() {
        HumanRequest request = new HumanRequest("Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        addHuman(request, ErrorCode.SUCCESS);
    }

//    Get

    @Test
    public void testGetById() {
        HumanRequest request = new HumanRequest("Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        HumanResponse response = getHumanById(addHuman(request, ErrorCode.SUCCESS).getId() , ErrorCode.SUCCESS);
        checkHumanFields(request,response);
    }

    @Test
    public void testGetByIdWithoutHuman() {
        getHumanById(100 , ErrorCode.ITEM_NOT_FOUND);
    }

    @Test
    public void testGetByEmail() {
        HumanRequest request = new HumanRequest("Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        HumanResponse response = getHumanByEmail(addHuman(request, ErrorCode.SUCCESS).getEmail() , ErrorCode.SUCCESS);
        checkHumanFields(request,response);
    }

    @Test
    public void testGetByEmailWithoutHuman() {
        getHumanByEmail("not_email@mail.ru" , ErrorCode.ITEM_NOT_FOUND);
    }

//    Update

    @Test
    public void testChangeHuman() {
        HumanRequest request = new HumanRequest("Д", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        HumanResponse response = addHuman(request, ErrorCode.SUCCESS);
        HumanRequest request2 = new HumanRequest("Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        changeHuman(response.getId(), request2, ErrorCode.SUCCESS);
        checkHumanFields(request2, getHumanById(response.getId(), ErrorCode.SUCCESS));
    }

    @Test
    public void testChangeHumanWithoutHuman(){
        HumanRequest request = new HumanRequest("Д", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        changeHuman(100, request, ErrorCode.ITEM_NOT_FOUND);
    }

//    Delete

    @Test
    public void testDeleteById() {
        HumanRequest request = new HumanRequest("Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        HumanResponse response = addHuman(request, ErrorCode.SUCCESS);
        deleteHuman(response.getId(), ErrorCode.SUCCESS);
    }

    @Test
    public void testDeleteWithoutHuman() {
        deleteHuman(100, ErrorCode.SUCCESS);
    }

}
