package ru.omsu.imit.novikova.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import ru.omsu.imit.novikova.model.Category;

public interface CategoryMapper {

    @Select("SELECT id, title FROM shelter_bd.CATEGORY WHERE id = #{id}")
    public Category getById(int id);

    @Delete("DELETE FROM shelter_bd.CATEGORY")
    public void deleteAll();
}
