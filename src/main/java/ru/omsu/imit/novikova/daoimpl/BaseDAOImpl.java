package ru.omsu.imit.novikova.daoimpl;

import org.apache.ibatis.session.SqlSession;
import ru.omsu.imit.novikova.mappers.*;
import ru.omsu.imit.novikova.model.Animal;
import ru.omsu.imit.novikova.utils.MyBatisUtils;

public class BaseDAOImpl {

    protected SqlSession getSession() {
        return MyBatisUtils.getSqlSessionFactory().openSession();
    }

    protected UserMapper getUserMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(UserMapper.class);
    }

    protected HumanMapper getHumanMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(HumanMapper.class);
    }

    protected OrganisationMapper getOrganisationMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(OrganisationMapper.class);
    }

    protected CategoryMapper getCategoryMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(CategoryMapper.class);
    }

    protected AnimalTypeMapper getAnimalTypeMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(AnimalTypeMapper.class);
    }

    protected AnimalMapper getAnimalMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(AnimalMapper.class);
    }

    protected CardMapper getCardMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(CardMapper.class);
    }

}
