package ru.omsu.imit.novikova.mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import ru.omsu.imit.novikova.model.Organisation;
import ru.omsu.imit.novikova.model.User;

public interface OrganisationMapper {

    @Insert("INSERT INTO shelter_bd.ORGANISATION  ( id, TIN, title, additionalInfo, registrationDate ) VALUES" +
            " ( #{id}, #{TIN}, #{title}, #{additionalInfo}, #{registrationDate} )")
    @Options(useGeneratedKeys = true)
    public Integer insert(Organisation organisation);

    @Select("SELECT * FROM shelter_bd.ORGANISATION WHERE id = #{id}")
    @Results( {
            @Result(property = "user", column = "id", javaType = User.class, one = @One(select = "ru.omsu.imit.novikova.mappers.UserMapper.getById", fetchType = FetchType.EAGER))
    })
    public Organisation getById(int id);

    @Select("SELECT * FROM shelter_bd.ORGANISATION LEFT JOIN shelter_bd.USER USING(id) WHERE email = #{email}")
    @Results( {
            @Result(property = "user", column = "email",javaType = User.class,one = @One(select = "ru.omsu.imit.novikova.mappers.UserMapper.getByEmail", fetchType = FetchType.EAGER))
    })
    public Organisation getByEmail(String email);

    @Update("UPDATE shelter_bd.ORGANISATION SET TIN = #{o.TIN}, title = #{o.title}, additionalInfo = #{o.additionalInfo}, " +
            "registrationDate = #{o.registrationDate} WHERE id = #{id} ")
    public void changeHuman(@Param("id") int id, @Param("o") Organisation organisation);


    @Delete("DELETE FROM shelter_bd.ORGANISATION WHERE id = #{id}")
    public int delete(@Param("id") int id);

    @Delete("DELETE FROM shelter_bd.ORGANISATION")
    public void deleteAll();

}
