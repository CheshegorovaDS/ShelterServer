package ru.omsu.imit.novikova.mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import ru.omsu.imit.novikova.model.Cookie;
import ru.omsu.imit.novikova.model.User;

public interface CookieMapper {

    @Insert("INSERT INTO shelter_bd.COOKIE (id, accessToken) VALUES (#{cookie.user.id},#{cookie.uuid})")
    Integer insert(@Param("cookie") Cookie cookie);

    @Select("SELECT * FROM shelter_bd.COOKIE WHERE id = #{id}")
    @Results( {
            @Result(property = "user", column = "id",javaType = User.class,one = @One(select = "ru.omsu.imit.novikova.mappers.UserMapper.getById", fetchType = FetchType.EAGER))
    })
    Cookie getById(@Param("id")int id);

    @Select("SELECT * FROM shelter_bd.COOKIE WHERE accessToken = #{uuid}")
    @Results( {
            @Result(property = "user", column = "id",javaType = User.class,one = @One(select = "ru.omsu.imit.novikova.mappers.UserMapper.getById", fetchType = FetchType.EAGER))
    })
    Cookie getByUUID(@Param("uuid")String uuid);

    @Delete("DELETE FROM shelter_bd.COOKIE WHERE accessToken = #{uuid}")
    void deleteByUUID(@Param("uuid")String uuid);

    @Delete("DELETE FROM shelter_bd.COOKIE WHERE id = #{id}")
    void deleteById(@Param("id")int id);

    @Delete("DELETE FROM shelter_bd.COOKIE")
    void deleteAll();
}
