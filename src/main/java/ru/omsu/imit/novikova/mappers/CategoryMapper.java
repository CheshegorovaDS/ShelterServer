package ru.omsu.imit.novikova.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import ru.omsu.imit.novikova.model.Category;

import java.util.List;

public interface CategoryMapper {

    @Select("SELECT id, title FROM shelter_bd.CATEGORY WHERE id = #{id}")
    public Category getById(int id);

    @Select("SELECT * FROM shelter_bd.CATEGORY")
    public List<Category> getAll();

    @Delete("DELETE FROM shelter_bd.CATEGORY")
    public void deleteAll();
}
