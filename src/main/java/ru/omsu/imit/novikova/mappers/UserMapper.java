package ru.omsu.imit.novikova.mappers;

import org.apache.ibatis.annotations.*;
import ru.omsu.imit.novikova.model.User;

public interface UserMapper {

    @Insert("INSERT INTO USER  ( phone, email, password) VALUES" +
            "( #{phone}, #{email}, #{password})")
    @Options(useGeneratedKeys = true)
    public Integer insert(User user);

    @Select("SELECT id, phone,  email, password FROM USER WHERE id = #{id}")
    public User getById(int id);

    @Update("UPDATE USER SET phone = #{us.phone}, email = #{us.email}, password = #{us.password} WHERE id = #{id}")
    public void changeUser(@Param("id")int id, @Param("us") User us);

    @Delete("DELETE FROM USER WHERE id = #{id}")
    public int delete(@Param("id") int id);

    @Delete("DELETE FROM USER")
    public void deleteAll();

}
