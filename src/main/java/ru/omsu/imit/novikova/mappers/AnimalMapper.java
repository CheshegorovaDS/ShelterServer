package ru.omsu.imit.novikova.mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import ru.omsu.imit.novikova.model.Animal;
import ru.omsu.imit.novikova.model.AnimalType;

public interface AnimalMapper {

    @Insert("INSERT INTO shelter_bd.ANIMAL  (id, name, photo, age, sex, breed, idAnimalType, passport, description) " +
            "VALUES( #{id}, #{name}, #{photo}, #{age}, #{sex}, #{breed}, #{animalType.id}, #{passport}, #{description} )")
    @Options(useGeneratedKeys = true)
    public Integer insert(Animal animal);

    @Select("SELECT id, name, photo, age, sex, breed, idAnimalType, passport, description FROM shelter_bd.ANIMAL WHERE id = #{id}")
    @Results( {
            @Result(property = "animalType", column = "idAnimalType",javaType = AnimalType.class, one = @One(select = "ru.omsu.imit.novikova.mappers.AnimalTypeMapper.getById", fetchType = FetchType.LAZY))
    })
    public Animal getById(int id);

    @Update("UPDATE shelter_bd.ANIMAL SET name = #{a.name}, photo = #{a.photo}, age = #{a.age}, " +
            "sex = #{a.sex}, breed = #{a.breed}, idAnimalType = #{a.animalType.id}, " +
            "passport = #{a.passport}, description = #{a.description}  WHERE id = #{id}")
    public void changeAnimal(@Param("id")int idAnimal,@Param("a") Animal animal);

    @Delete("DELETE FROM shelter_bd.ANIMAL WHERE id = #{id}")
    public int delete(@Param("id") int id);

    @Delete("DELETE FROM shelter_bd.ANIMAL")
    public void deleteAll();
}
