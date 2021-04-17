package ru.omsu.imit.novikova.daoimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.novikova.dao.OrganisationDao;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.Organisation;

public class OrganisationDaoImpl extends BaseDAOImpl implements OrganisationDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrganisationDaoImpl.class);

    @Override
    public Organisation insert(Organisation organisation) {
        return null;
    }

    @Override
    public Organisation getById(int id) throws ShelterException {
        return null;
    }

    @Override
    public void changeOrganisation(int id, Organisation newOrganisation) throws ShelterException {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void delete(int id) {

    }
}
