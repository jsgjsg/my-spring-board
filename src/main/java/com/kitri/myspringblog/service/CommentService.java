package com.kitri.myspringblog.service;

import com.kitri.myspringblog.domain.Comment;
import com.kitri.myspringblog.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;

    public List<Comment> getComments(Long boardId) {
        return commentMapper.findByBoardId(boardId);
    }

//    public Comment getComment(Long id) {}

    public void addComment(Comment comment) {
        commentMapper.insert(comment);
    }

    public void deleteComment(Long commentId) {
        commentMapper.delete(commentId);
    }

}
