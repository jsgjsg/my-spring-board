package com.kitri.myspringblog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    private long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;

    public Board(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Board(long id, String title, String content, String writer) {
        this(title, content, writer);
        this.id = id;
    }
}
