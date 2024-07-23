package com.kitri.myspringblog.service;


import com.kitri.myspringblog.domain.Board;
import com.kitri.myspringblog.domain.BoardByPagingDTO;
import com.kitri.myspringblog.domain.BoardWithCommentsDTO;
import com.kitri.myspringblog.domain.Comment;
import com.kitri.myspringblog.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private CommentService commentService;

    public List<Board> findAll() {
        return boardMapper.findAll();
    }

    public BoardByPagingDTO findAllByPage(int page) {
        List<Board> boards = boardMapper.findAll();
        Collections.reverse(boards);

        List<Board> subBoards = boards.subList((page - 1) * 5, Math.min(boards.size(), page * 5));
        return new BoardByPagingDTO((boards.size() - 1) / 5 + 1, subBoards);
    }

    public BoardByPagingDTO findAllByPageAndSearch(int page, String search) {
        List<Board> boards = boardMapper.findAllBySearch(search);
        Collections.reverse(boards);

        List<Board> subBoards = boards.subList((page - 1) * 5, Math.min(boards.size(), page * 5));
        return new BoardByPagingDTO((boards.size() - 1) / 5 + 1, subBoards);
    }

    public Board findById(long id) {
        return boardMapper.findById(id);
    }

    public BoardWithCommentsDTO findByIdWithComments(long id) {
        Board board = boardMapper.findById(id);
        List<Comment> comments = commentService.getComments(id);

        return new BoardWithCommentsDTO(board, comments);
    }

    public void insert(Board board) {
        boardMapper.insert(board);
    }

    public void update(Board board) {
        boardMapper.update(board);
    }

    public void delete(long id) {
        boardMapper.delete(id);
    }

}
