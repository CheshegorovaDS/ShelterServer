package ru.omsu.imit.novikova.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import ru.omsu.imit.novikova.model.AnimalType;

import java.util.List;

public interface AnimalTypeMapper {
    @Select("SELECT id, title FROM shelter_bd.ANIMAL_TYPE WHERE id = #{id}")
    public AnimalType getById(int id);

    @Select("SELECT * FROM shelter_bd.ANIMAL_TYPE")
    public List<AnimalType> getAll();

    @Delete("DELETE FROM shelter_bd.ANIMAL_TYPE")
    public void deleteAll();
}
