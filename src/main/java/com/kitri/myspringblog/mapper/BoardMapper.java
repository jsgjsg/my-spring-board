package com.kitri.myspringblog.mapper;

import com.kitri.myspringblog.domain.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Select("SELECT * FROM board")
    List<Board> findAll();

    @Select("SELECT * FROM board WHERE title LIKE CONCAT('%', #{search}, '%') OR writer LIKE CONCAT('%', #{search}, '%')")
    List<Board> findAllBySearch(@Param("search") String search);

    @Select("SELECT * FROM board WHERE id = #{id}")
    Board findById(long id);

    @Insert("INSERT INTO board(title, content, writer) VALUES (#{title}, #{content}, #{writer})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Board board);

    @Update("UPDATE board SET title=#{title}, content=#{content}, writer=#{writer} WHERE id = #{id}")
    void update(Board board);

    @Delete("DELETE FROM board WHERE id = #{id}")
    void delete(long id);

}