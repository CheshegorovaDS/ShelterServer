package ru.omsu.imit.novikova.daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.novikova.dao.OrganisationDao;
import ru.omsu.imit.novikova.exception.ShelterException;
import ru.omsu.imit.novikova.model.Organisation;
import ru.omsu.imit.novikova.utils.ErrorCode;

public class OrganisationDaoImpl extends BaseDAOImpl implements OrganisationDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrganisationDaoImpl.class);

    @Override
    public Organisation insert(Organisation organisation) {
        LOGGER.debug("DAO Insert Organisation {}", organisation);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).insert(organisation.getUser());
                getOrganisationMapper(sqlSession).insert(organisation);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Organisation {}, {}", organisation, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return organisation;
    }

    @Override
    public Organisation getById(int id) throws ShelterException {
        try (SqlSession sqlSession = getSession()) {
            Organisation organisation = getOrganisationMapper(sqlSession).getById(id);
            if(organisation == null) {
                throw new ShelterException(ErrorCode.ITEM_NOT_FOUND, Integer.toString(id));
            }
            return organisation;
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Organisation By Id {}", ex);
            throw ex;
        }
    }

    @Override
    public Organisation getByEmail(String email) throws ShelterException {
        try (SqlSession sqlSession = getSession()) {
            Organisation organisation = getOrganisationMapper(sqlSession).getByEmail(email);
            if(organisation == null) {
                throw new ShelterException(ErrorCode.ITEM_NOT_FOUND, email);
            }
            return organisation;
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Organisation By Email {}", ex);
            throw ex;
        }
    }

    @Override
    public void changeOrganisation(int id, Organisation newOrganisation) throws ShelterException {
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).changeUser(id, newOrganisation.getUser());
                getOrganisationMapper(sqlSession).changeHuman(id, newOrganisation);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't change Organisation {} {} ", id, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO Delete All Organisations {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getOrganisationMapper(sqlSession).deleteAll();
                getUserMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Organisations {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession sqlSession = getSession()) {
            try {
                getOrganisationMapper(sqlSession).delete(id);
                getUserMapper(sqlSession).delete(id);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete Organisation by id {} {}", id, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
