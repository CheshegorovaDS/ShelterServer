package ru.omsu.imit.novikova.shelter.clienttests;

import org.junit.Test;
import ru.omsu.imit.novikova.rest.request.OrganisationRequest;
import ru.omsu.imit.novikova.rest.response.OrganisationResponse;
import ru.omsu.imit.novikova.utils.ErrorCode;

import java.sql.Date;

public class OrganisationClientTest extends BaseClientTest {

    //    Insert

    @Test
    public void testInsertOrganisation() {
        OrganisationRequest request = new OrganisationRequest(
                2445147854L, "Хвостик", null, Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        addOrganisation(request, ErrorCode.SUCCESS);
    }

    //    Get

    @Test
    public void testGetById() {
        OrganisationRequest request = new OrganisationRequest(
                2445147854L, "Хвостик", null, Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        OrganisationResponse response = getOrganisationById(addOrganisation(request, ErrorCode.SUCCESS).getId() , ErrorCode.SUCCESS);
        checkOrganisationFields(request,response);
    }

    @Test
    public void testGetByIdWithoutOrganisation() {
        getOrganisationById(100 , ErrorCode.ITEM_NOT_FOUND);
    }

    @Test
    public void testGetByEmail() {
        OrganisationRequest request = new OrganisationRequest(
                2445147854L, "Хвостик", null, Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        OrganisationResponse response = getOrganisationByEmail(addOrganisation(request, ErrorCode.SUCCESS).getEmail() , ErrorCode.SUCCESS);
        checkOrganisationFields(request,response);
    }

    @Test
    public void testGetByEmailWithoutOrganisation() {
        getOrganisationByEmail("not_email@mail.ru" , ErrorCode.ITEM_NOT_FOUND);
    }

    //    Update

    @Test
    public void testChangeOrganisation() {
        OrganisationRequest request = new OrganisationRequest(
                2445147854L, "Хвостик", null, Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        OrganisationResponse response = addOrganisation(request, ErrorCode.SUCCESS);
        OrganisationRequest request2 = new OrganisationRequest(
                2445147852L, "Хвостики", null, Date.valueOf("2020-7-1"),
                "80001342422", "dgg@mail.ru", "test");
        changeOrganisation(response.getId(), request2, ErrorCode.SUCCESS);
        checkOrganisationFields(request2,getOrganisationById(response.getId(),ErrorCode.SUCCESS));
    }

    @Test
    public void testChangeOrganisationWithoutOrganisation(){
        OrganisationRequest request = new OrganisationRequest(
                2445147854L, "Хвостик", null, Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        changeOrganisation(100, request, ErrorCode.ITEM_NOT_FOUND);
    }

    //    Delete

    @Test
    public void testDeleteById() {
        OrganisationRequest request = new OrganisationRequest(
                2445147854L, "Хвостик", null, Date.valueOf("2020-7-1"),
                "80001342422", "dg@mail.ru", "test");
        OrganisationResponse response = addOrganisation(request, ErrorCode.SUCCESS);
        deleteOrganisation(response.getId(), ErrorCode.SUCCESS);
    }

    @Test
    public void testDeleteWithoutOrganisation() {
        deleteOrganisation(100, ErrorCode.SUCCESS);
    }

}
