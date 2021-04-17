package ru.omsu.imit.novikova.dao;

import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.Organisation;

public interface OrganisationDao {

    public Organisation insert(Organisation organisation);

    public Organisation getById(int id) throws ShelterException;

    public void changeOrganisation(int id, Organisation newOrganisation) throws ShelterException;

    public void deleteAll();

    public void delete(int id);
}
