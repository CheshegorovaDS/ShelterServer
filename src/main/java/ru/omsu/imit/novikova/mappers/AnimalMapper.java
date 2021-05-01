package ru.omsu.imit.novikova.mappers;

import org.apache.ibatis.annotations.*;
import ru.omsu.imit.novikova.model.Animal;

public interface AnimalMapper {

    @Insert("INSERT INTO shelter_bd.ANIMAL  (id, name, photo, age, sex, breed, idAnimalType, passport, description) " +
            "VALUES( #{id}, #{name}, #{photo}, #{age}, #{sex}, #{breed}, #{age}, #{passport}, #{description} )")
    @Options(useGeneratedKeys = true)
    public Integer insert(Animal animal);

    @Select("SELECT id, name, photo, age, sex, breed, idAnimalType, passport, description FROM shelter_bd.ANIMAL WHERE id = #{id}")
    public Animal getById(int id);

    @Update("UPDATE shelter_bd.ANIMAL SET name = #{a.name}, photo = #{a.photo}, age = #{a.age}, " +
            "sex = #{a.sex}, breed = #{a.breed}, idAnimalType = #{a.idAnimalType}, " +
            "passport = #{a.passport}, description = #{a.description}  WHERE id = #{id}")
    public void changeAnimal(@Param("id")int id, @Param("a") Animal animal);

    @Delete("DELETE FROM shelter_bd.ANIMAL WHERE id = #{id}")
    public int delete(@Param("id") int id);

    @Delete("DELETE FROM shelter_bd.ANIMAL")
    public void deleteAll();
}
