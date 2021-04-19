package ru.omsu.imit.novikova.mappers;

import org.apache.ibatis.annotations.*;
import ru.omsu.imit.novikova.model.User;

public interface UserMapper {

    @Insert("INSERT INTO shelter_bd.USER  (phone, email, password) VALUES(#{phone}, #{email}, #{password})")
    @Options(useGeneratedKeys = true)
    public Integer insert(User user);

    @Select("SELECT id, phone,  email, password FROM shelter_bd.USER WHERE id = #{id}")
    public User getById(int id);

    @Select("SELECT id, phone,  email, password FROM shelter_bd.USER WHERE email = #{email}")
    public User getByEmail(String email);

    @Update("UPDATE shelter_bd.USER SET phone = #{us.phone}, email = #{us.email}, password = #{us.password} WHERE id = #{id}")
    public void changeUser(@Param("id")int id, @Param("us") User us);

    @Delete("DELETE FROM shelter_bd.USER WHERE id = #{id}")
    public int delete(@Param("id") int id);

    @Delete("DELETE FROM shelter_bd.USER")
    public void deleteAll();

}
