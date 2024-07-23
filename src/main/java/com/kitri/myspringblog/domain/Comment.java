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
public class Comment {//username content timestamp
    private long id;
    private String content;
    private String username;
    private LocalDateTime created_at;
    private long boardId;
}
