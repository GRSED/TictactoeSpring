package com.example.tictactoe_spring;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class TictactoeDto {
    private int ttt_no;
    private String ttt_draw;
    private int ttt_row;
    private int ttt_col;
}
