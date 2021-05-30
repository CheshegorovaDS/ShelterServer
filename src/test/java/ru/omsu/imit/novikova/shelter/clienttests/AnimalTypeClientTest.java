package ru.omsu.imit.novikova.shelter.clienttests;

import org.junit.Test;
import ru.omsu.imit.novikova.utils.ErrorCode;

public class AnimalTypeClientTest extends BaseClientTest {

    @Test
    public void testGetAllCards() {
        getAllAnimalTypes(3, ErrorCode.SUCCESS);
    }

}
