package com.kitri.myspringblog.mapper;

import com.kitri.myspringblog.domain.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("INSERT INTO comment(content, username, board_id) VALUES (#{content}, #{username}, #{boardId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Comment comment);

    @Select("SELECT * FROM comment WHERE board_id = #{boardId}")
    List<Comment> findByBoardId(long boardId);

    @Delete("DELETE FROM comment WHERE id = #{id}")
    void delete(long id);
}