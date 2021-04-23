package ru.omsu.imit.novikova.mybatis;

import org.junit.Test;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.Organisation;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AnnotationOrganisationTests extends BaseDAOTests {

//    Insert Organisation

    @Test
    public void testInsertOrganisation() throws ShelterException {
        Organisation organisation = new Organisation(0, "80001342422", "dg@mail.ru", "test",
                2445147854L, "Хвостики", null, Date.valueOf("2020-7-1"));
        organisationDao.insert(organisation);
        Organisation organisationFromDB = organisationDao.getById(organisation.getId());
        checkOrganisationFields(organisation, organisationFromDB);
    }

    @Test(expected = RuntimeException.class)
    public void testInsertOrganisationWithNullTitle() {
        Organisation organisation = new Organisation(0, "80001342422", "dg@mail.ru", "test",
                124512445147854L, null, null, Date.valueOf("2020-7-1"));
        organisationDao.insert(organisation);
    }

//    Update Organisation

    @Test
    public void testChangeOrganisation() throws ShelterException {
        Organisation organisation = new Organisation(0, "80001342422", "dg@mail.ru", "test",
                2445147854L, "Хвостик", null, Date.valueOf("2020-7-1"));
        organisationDao.insert(organisation);
        Organisation organisationFromDB = organisationDao.getById(organisation.getId());
        checkOrganisationFields(organisation, organisationFromDB);
        Organisation organisation2 = new Organisation(0, "80001342422", "dgg@mail.ru", "test",
                2445147854L, "Хвостики", null, Date.valueOf("2020-7-1"));
        organisationDao.changeOrganisation(organisation.getId(), organisation2);
        organisationFromDB = organisationDao.getById(organisation.getId());
        assertEquals("dgg@mail.ru", organisationFromDB.getUser().getEmail());
        assertEquals("Хвостики", organisationFromDB.getTitle());
    }

    @Test(expected = RuntimeException.class)
    public void testChangeHumanTitleToNull() throws ShelterException {
        Organisation organisation = new Organisation(0, "80001342422", "dg@mail.ru", "test",
                2445147854L, "Хвостик", null, Date.valueOf("2020-7-1"));
        organisationDao.insert(organisation);
        Organisation organisationFromDB = organisationDao.getById(organisation.getId());
        checkOrganisationFields(organisation, organisationFromDB);
        Organisation organisation2 = new Organisation(0, "80001342422", "dg@mail.ru", "test",
                2445147854L, null, null, Date.valueOf("2020-7-1"));
        organisationDao.changeOrganisation(organisation.getId(), organisation2);
    }

//    Select Organisation

    @Test
    public void testHumanGetByEmail() throws ShelterException {
        Organisation organisation = new Organisation(0, "80001342422", "dg@mail.ru", "test",
                2445147854L, "Хвостик", null, Date.valueOf("2020-7-1"));
        organisationDao.insert(organisation);
        Organisation organisation2 = new Organisation(0, "80001342444", "aaa@mail.ru", "test",
                2445222854L, "Друг", null, Date.valueOf("2020-5-1"));
        organisationDao.insert(organisation2);
        Organisation organisationFromDB = organisationDao.getByEmail(organisation.getUser().getEmail());
        checkOrganisationFields(organisation, organisationFromDB);
    }

    @Test(expected = ShelterException.class)
    public void testHumanGetByEmailWithoutThisEmail() throws ShelterException {
        organisationDao.getByEmail("not_email@mail.ru");
    }

    //Delete Organisation

    @Test(expected = ShelterException.class)
    public void testDeleteOrganisation() throws ShelterException {
        Organisation organisation = new Organisation(0, "80001342422", "dg@mail.ru", "test",
                2445147854L, "Хвостик", null, Date.valueOf("2020-7-1"));
        organisationDao.insert(organisation);
        Organisation organisationFromDB = organisationDao.getById(organisation.getId());
        checkOrganisationFields(organisation, organisationFromDB);
        organisationDao.delete(organisation.getId());
        organisationFromDB = organisationDao.getById(organisation.getId());
        assertNull(organisationFromDB);
    }

}
