package com.kitri.myspringblog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardByPagingDTO {
    private int totalPages;
    private List<Board> boards;
}
