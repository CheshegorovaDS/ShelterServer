package ru.omsu.imit.novikova.shelter.clienttests;

import org.junit.Test;
import ru.omsu.imit.novikova.utils.ErrorCode;

public class CategoryClientTest extends BaseClientTest {

    @Test
    public void testGetAllCards() {
        getAllCategories(3, ErrorCode.SUCCESS);
    }

}
