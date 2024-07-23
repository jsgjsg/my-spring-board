package com.kitri.myspringblog.controller;

import com.kitri.myspringblog.domain.Board;
import com.kitri.myspringblog.domain.BoardByPagingDTO;
import com.kitri.myspringblog.domain.BoardWithCommentsDTO;
import com.kitri.myspringblog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public String list(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(name = "search", required = false, defaultValue = "") String search,
                       Model model) {
//        List<Board> boards = boardService.findAll();
//        BoardByPagingDTO boardsAndTotalPages = boardService.findAllByPage(page);
        BoardByPagingDTO boardsAndTotalPages = boardService.findAllByPageAndSearch(page, search);

        model.addAttribute("page", page);
        model.addAttribute("totalPages", boardsAndTotalPages.getTotalPages());

        model.addAttribute("boards", boardsAndTotalPages.getBoards());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());

        return "board/list";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable long id) {
        BoardWithCommentsDTO boardAndComments = boardService.findByIdWithComments(id);
        model.addAttribute("boardAndComments", boardAndComments);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());

        return "board/show";
    }

    @GetMapping("/write")
    public String write(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        return "board/write";
    }

    @PostMapping("/create")
    public String create(String title, String content, String writer) {
        boardService.insert(new Board(title, content, writer));

        return "redirect:/board/list";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable long id) {
        Board board = boardService.findById(id);
        model.addAttribute("board", board);
        return "board/edit";
    }

    @PostMapping("/{id}/update")
    public String update(Model model, @PathVariable long id, String title, String content, String writer) {
        boardService.update(new Board(id, title, content, writer));

        Board newBoard = boardService.findById(id);
        model.addAttribute("board", newBoard);
        return "board/show";
    }

    @PostMapping("/{id}/delete")
    public String delete(Model model, @PathVariable long id) {
        boardService.delete(id);

        return "redirect:/board/list";
    }
}