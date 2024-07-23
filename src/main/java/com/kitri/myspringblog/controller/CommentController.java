package com.kitri.myspringblog.controller;

import com.kitri.myspringblog.domain.Comment;
import com.kitri.myspringblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public String createComment(Comment comment) {
        commentService.addComment(comment);
        return "redirect:/board/" + comment.getBoardId();
    }

    @PostMapping("/{id}/delete")
    public String deleteComment(@PathVariable long id, long boardId) {
        commentService.deleteComment(id);
        return "redirect:/board/" + boardId;
    }

}
