package ru.omsu.imit.novikova.shelter.clienttests;

import org.junit.Test;
import ru.omsu.imit.novikova.rest.request.HumanRequest;
import ru.omsu.imit.novikova.rest.response.HumanResponse;
import ru.omsu.imit.novikova.utils.ErrorCode;

import java.sql.Date;

public class HumanClientTest extends BaseClientTest {

    @Test
    public void testInsertHuman() {
        HumanRequest request = new HumanRequest("Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        addHuman(request, ErrorCode.SUCCESS);
    }

    @Test
    public void testDeleteById() {
        HumanRequest request = new HumanRequest("Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        HumanResponse response = addHuman(request, ErrorCode.SUCCESS);
        deleteHuman(response.getId(), ErrorCode.SUCCESS);
    }

    @Test
    public void testGetById() {
        HumanRequest request = new HumanRequest("Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
//        HumanResponse response = getHu(addVoter(request, ErrorCode.SUCCESS).getId() , ErrorCode.SUCCESS);
//        checkVoterFields(request,response);
    }

//    @Test
//    public void testGetByIdWithoutVoter() {
//        getVoterById(1 , ErrorCode.ITEM_NOT_FOUND);
//    }
//
//    @Test
//    public void testGetByPassport() {
//        VoterRequest request = new VoterRequest("Пётр","Гривин", "Васильевич", Date.valueOf("1945-8-24"),"6666666666","Москва","Ленина","210","14",null);
//        VoterResponse response = getVoterByPassport(addVoter(request, ErrorCode.SUCCESS).getPassport() , ErrorCode.SUCCESS);
//        checkVoterFields(request,response);
//    }
//
//    @Test
//    public void testGetByPassportWithoutVoter() {
//        getVoterById(1 , ErrorCode.ITEM_NOT_FOUND);
//    }


    @Test
    public void testDeleteWithoutHuman() {
        deleteHuman(100, ErrorCode.SUCCESS);
    }

}
