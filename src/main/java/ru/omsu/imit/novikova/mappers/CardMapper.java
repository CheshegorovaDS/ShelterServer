package ru.omsu.imit.novikova.mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import ru.omsu.imit.novikova.model.Animal;
import ru.omsu.imit.novikova.model.Card;
import ru.omsu.imit.novikova.model.Category;
import ru.omsu.imit.novikova.model.User;

import java.util.List;

public interface CardMapper {

    @Insert("INSERT INTO shelter_bd.CARD  ( idAnimal, idUser, idCategory ) VALUES" +
            " ( #{card.animal.id}, #{card.user.id}, #{card.category.id} )")
    @Options(useGeneratedKeys = true)
    public Integer insert( @Param("card") Card card);

    @Select("SELECT * FROM shelter_bd.CARD WHERE idAnimal = #{idAnimal}")
    @Results( {
            @Result(property = "animal", column = "idAnimal",javaType = Animal.class, one = @One(select = "ru.omsu.imit.novikova.mappers.AnimalMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "user", column = "idUser", javaType = User.class, one = @One(select = "ru.omsu.imit.novikova.mappers.UserMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "category", column = "idCategory", javaType = Category.class, one = @One(select = "ru.omsu.imit.novikova.mappers.CategoryMapper.getById", fetchType = FetchType.LAZY))
    })
    public Card getByAnimalId(@Param("idAnimal") int idAnimal);

    @Select("SELECT * FROM shelter_bd.CARD WHERE idUser = #{idUser} AND idAnimal = #{idAnimal}")
    @Results( {
            @Result(property = "animal", column = "idAnimal",javaType = Animal.class, one = @One(select = "ru.omsu.imit.novikova.mappers.AnimalMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "user", column = "idUser", javaType = User.class, one = @One(select = "ru.omsu.imit.novikova.mappers.UserMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "category", column = "idCategory", javaType = Category.class, one = @One(select = "ru.omsu.imit.novikova.mappers.CategoryMapper.getById", fetchType = FetchType.LAZY))
    })
    public Card getById(@Param("idUser") int idUser, @Param("idAnimal") int idAnimal);

    @Select("SELECT * FROM shelter_bd.CARD")
    @Results( {
            @Result(property = "animal", column = "idAnimal",javaType = Animal.class, one = @One(select = "ru.omsu.imit.novikova.mappers.AnimalMapper.getById", fetchType = FetchType.EAGER)),
            @Result(property = "user", column = "idUser", javaType = User.class, one = @One(select = "ru.omsu.imit.novikova.mappers.UserMapper.getById", fetchType = FetchType.EAGER)),
            @Result(property = "category", column = "idCategory", javaType = Category.class, one = @One(select = "ru.omsu.imit.novikova.mappers.CategoryMapper.getById", fetchType = FetchType.EAGER))
    })
    public List<Card> getAll();

    @Select("SELECT * FROM shelter_bd.CARD WHERE idCategory = #{idCategory}")
    @Results( {
            @Result(property = "animal", column = "idAnimal",javaType = Animal.class, one = @One(select = "ru.omsu.imit.novikova.mappers.AnimalMapper.getById", fetchType = FetchType.EAGER)),
            @Result(property = "user", column = "idUser", javaType = User.class, one = @One(select = "ru.omsu.imit.novikova.mappers.UserMapper.getById", fetchType = FetchType.EAGER)),
            @Result(property = "category", column = "idCategory", javaType = Category.class, one = @One(select = "ru.omsu.imit.novikova.mappers.CategoryMapper.getById", fetchType = FetchType.EAGER))
    })
    public List<Card> getAllByCategory(int idCategory);

    @Select("SELECT * FROM shelter_bd.CARD WHERE idUser = #{idUser}")
    @Results( {
            @Result(property = "animal", column = "idAnimal",javaType = Animal.class, one = @One(select = "ru.omsu.imit.novikova.mappers.AnimalMapper.getById", fetchType = FetchType.EAGER)),
            @Result(property = "user", column = "idUser", javaType = User.class, one = @One(select = "ru.omsu.imit.novikova.mappers.UserMapper.getById", fetchType = FetchType.EAGER)),
            @Result(property = "category", column = "idCategory", javaType = Category.class, one = @One(select = "ru.omsu.imit.novikova.mappers.CategoryMapper.getById", fetchType = FetchType.EAGER))
    })
    public List<Card> getAllByUser(int idUser);

    @Select("SELECT * FROM shelter_bd.CARD, shelter_bd.ANIMAL WHERE shelter_bd.CARD.idAnimal = shelter_bd.ANIMAL.id AND shelter_bd.ANIMAL.idAnimalType = #{idAnimalType}")
    @Results( {
            @Result(property = "animal", column = "idAnimal",javaType = Animal.class, one = @One(select = "ru.omsu.imit.novikova.mappers.AnimalMapper.getById", fetchType = FetchType.EAGER)),
            @Result(property = "user", column = "idUser", javaType = User.class, one = @One(select = "ru.omsu.imit.novikova.mappers.UserMapper.getById", fetchType = FetchType.EAGER)),
            @Result(property = "category", column = "idCategory", javaType = Category.class, one = @One(select = "ru.omsu.imit.novikova.mappers.CategoryMapper.getById", fetchType = FetchType.EAGER))
    })
    public List<Card> getAllByAnimalType(int idAnimalType);

    @Update("UPDATE shelter_bd.CARD SET idCategory = #{c.category.id} WHERE idAnimal = #{idAnimal} ")
    public void changeCard(@Param("idAnimal") int idAnimal, @Param("c") Card card);

    @Delete("DELETE FROM shelter_bd.CARD WHERE idAnimal = #{idAnimal}")
    public int delete(@Param("idAnimal") int idAnimal);

    @Delete("DELETE FROM shelter_bd.CARD")
    public void deleteAll();

}
