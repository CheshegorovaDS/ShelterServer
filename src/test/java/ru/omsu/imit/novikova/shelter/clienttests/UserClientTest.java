package ru.omsu.imit.novikova.shelter.clienttests;

import org.junit.Test;
import ru.omsu.imit.novikova.rest.request.HumanRequest;
import ru.omsu.imit.novikova.rest.request.UserRequest;
import ru.omsu.imit.novikova.rest.response.HumanResponse;
import ru.omsu.imit.novikova.rest.response.TokenResponse;
import ru.omsu.imit.novikova.utils.ErrorCode;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

public class UserClientTest extends BaseClientTest {

    //Login

    @Test
    public void testLogin() {
        HumanRequest humanRequest = new HumanRequest("Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"),
                "80001342477", "b@mail.ru", "test");
        HumanResponse userResponse = addHuman(humanRequest, ErrorCode.SUCCESS);
        UserRequest request = new UserRequest(humanRequest.getEmail(), humanRequest.getPassword());
        TokenResponse response = login(request, ErrorCode.SUCCESS);
        assertEquals(userResponse.getId(), response.getId());
    }

    @Test()
    public void testLoginWithoutEmail() {
        UserRequest request = new UserRequest("ff@mail.tu", "eee");
        login(request, ErrorCode.ITEM_NOT_FOUND);
    }

    @Test()
    public void testLoginWithWrongPassword() {
        HumanRequest humanRequest = new HumanRequest("Дмитрий", "Пупкин", "Святославович", Date.valueOf("1954-7-1"),
                "Россия", "Саранск", Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        addHuman(humanRequest, ErrorCode.SUCCESS);
        UserRequest request = new UserRequest(humanRequest.getEmail(), "eee");
        login(request, ErrorCode.ITEM_NOT_FOUND);
    }
}
