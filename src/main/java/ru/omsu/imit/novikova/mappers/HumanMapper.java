package ru.omsu.imit.novikova.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import ru.omsu.imit.novikova.model.Human;

public interface HumanMapper {

    @Insert("INSERT INTO shelter_bd.HUMAN  ( id, firstName, lastName, patronymic, birthdate, city, country, registrationDate ) VALUES" +
            " ( #{id}, #{firstName}, #{lastName}, #{patronymic}, #{birthdate}, #{city}, #{country}, #{registrationDate} )")
    @Options(useGeneratedKeys = true)
    public Integer insert(Human human);

    @Delete("DELETE FROM shelter_bd.HUMAN WHERE id = #{id}")
    public int delete(@Param("id") int id);

    @Delete("DELETE FROM shelter_bd.HUMAN")
    public void deleteAll();
}
