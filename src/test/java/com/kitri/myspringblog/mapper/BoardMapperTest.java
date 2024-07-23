package com.kitri.myspringblog.mapper;

import com.kitri.myspringblog.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class BoardMapperTest {

    @Autowired
    BoardMapper boardMapper;

    @Test
    void findAll() {
        List<Board> boards = boardMapper.findAll();
        assertThat(boards).hasSize(5);
    }

    @Test
    void findById() {
        Board board = new Board();
        board.setTitle("test title");
        board.setContent("test content");
        board.setWriter("test writer");

        boardMapper.insert(board);
        Board foundBoard = boardMapper.findById(board.getId());
        assertThat(foundBoard).isNotNull();
        assertThat(foundBoard.getTitle()).isEqualTo("test title");
        assertThat(foundBoard.getContent()).isEqualTo("test content");
        assertThat(foundBoard.getWriter()).isEqualTo("test writer");
    }

    @Test
    void insert() {
        Board board = new Board();
        board.setTitle("test title");
        board.setContent("test content");
        board.setWriter("test writer");
        boardMapper.insert(board);

        assertThat(boardMapper.findById(board.getId())).isNotNull();
    }

    @Test
    void update() {
        Board board = new Board();
        board.setTitle("test title");
        board.setContent("test content");
        board.setWriter("test writer");
        boardMapper.insert(board);
        Board foundBoard = boardMapper.findById(board.getId());

        foundBoard.setTitle("new test title");
        foundBoard.setContent("new test content");
        foundBoard.setWriter("new test writer");
        boardMapper.update(foundBoard);

        Board updatedBoard = boardMapper.findById(board.getId());
        assertThat(updatedBoard).isNotNull();
        assertThat(updatedBoard.getTitle()).isEqualTo("new test title");
        assertThat(updatedBoard.getContent()).isEqualTo("new test content");
        assertThat(updatedBoard.getWriter()).isEqualTo("new test writer");
    }

    @Test
    void delete() {
        Board board = new Board();
        board.setTitle("test title");
        board.setContent("test content");
        board.setWriter("test writer");
        boardMapper.insert(board);
        assertThat(boardMapper.findById(board.getId())).isNotNull();

        boardMapper.delete(board.getId());
        assertThat(boardMapper.findById(board.getId())).isNull();
    }
}