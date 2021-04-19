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
    public void testDeleteWithoutHuman() {
        deleteHuman(100, ErrorCode.SUCCESS);
    }

}
