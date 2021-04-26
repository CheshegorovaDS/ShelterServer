package ru.omsu.imit.novikova.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ru.omsu.imit.novikova.model.Card;

public interface CardMapper {

    @Insert("INSERT INTO shelter_bd.CARD  ( idAnimal, idUser, idCategory ) VALUES" +
            " ( #{card.animal.id}, #{card.user.id}, #{card.category.id} )")
    @Options(useGeneratedKeys = true)
    public Integer insert( @Param("card") Card card);

}
