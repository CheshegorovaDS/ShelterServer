package ru.omsu.imit.novikova.mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import ru.omsu.imit.novikova.model.Human;
import ru.omsu.imit.novikova.model.User;

public interface HumanMapper {

    @Insert("INSERT INTO shelter_bd.HUMAN  ( id, firstName, lastName, patronymic, birthdate, city, country, registrationDate ) VALUES" +
            " ( #{id}, #{firstName}, #{lastName}, #{patronymic}, #{birthdate}, #{city}, #{country}, #{registrationDate} )")
    @Options(useGeneratedKeys = true)
    public Integer insert(Human human);

    @Select("SELECT * FROM shelter_bd.HUMAN WHERE id = #{id}")
    @Results( {
            @Result(property = "user", column = "id",javaType = User.class,one = @One(select = "ru.omsu.imit.novikova.mappers.UserMapper.getById", fetchType = FetchType.EAGER))
    })
    public Human getById(int id);

    @Select("SELECT * FROM shelter_bd.HUMAN LEFT JOIN shelter_bd.USER USING(id) WHERE email = #{email}")
    @Results( {
            @Result(property = "user", column = "email",javaType = User.class,one = @One(select = "ru.omsu.imit.novikova.mappers.UserMapper.getByEmail", fetchType = FetchType.EAGER))
    })
    public Human getByEmail(String email);

    @Update("UPDATE shelter_bd.HUMAN SET firstName = #{h.firstName}, lastName = #{h.lastName}, patronymic = #{h.patronymic}, " +
            "birthdate = #{h.birthdate}, city = #{h.city}, country = #{h.country}, registrationDate = #{h.registrationDate} WHERE id = #{id} ")
    public void changeHuman(@Param("id") int id, @Param("h") Human human);


    @Delete("DELETE FROM shelter_bd.HUMAN WHERE id = #{id}")
    public int delete(@Param("id") int id);

    @Delete("DELETE FROM shelter_bd.HUMAN")
    public void deleteAll();
}
