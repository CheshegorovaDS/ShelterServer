package ru.omsu.imit.novikova.daoimpl;

import org.apache.ibatis.session.SqlSession;
import ru.omsu.imit.novikova.mappers.HumanMapper;
import ru.omsu.imit.novikova.mappers.UserMapper;
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
}
