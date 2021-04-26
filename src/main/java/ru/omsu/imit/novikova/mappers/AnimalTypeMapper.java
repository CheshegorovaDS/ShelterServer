package ru.omsu.imit.novikova.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import ru.omsu.imit.novikova.model.AnimalType;

public interface AnimalTypeMapper {
    @Select("SELECT id, title FROM shelter_bd.ANIMAL_TYPE WHERE id = #{id}")
    public AnimalType getById(int id);

    @Delete("DELETE FROM shelter_bd.ANIMAL_TYPE")
    public void deleteAll();
}
