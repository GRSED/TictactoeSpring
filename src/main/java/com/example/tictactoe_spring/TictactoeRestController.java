package com.example.tictactoe_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TictactoeRestController {
    @Autowired
    TictactoeService tictactoeService;

    @GetMapping(value = "draw")
    public String draw(@RequestParam("ttt_row") int rowIdx, @RequestParam("ttt_col") int colIdx) {
        String mark = "";
        String message = "";
        
        if (tictactoeService.countTtt()%2 == 0) {
            mark = "X";
        } else {
            mark = "O";
        }

        tictactoeService.insertTtt(new TictactoeDto(0, mark, rowIdx, colIdx));

        return mark;
    }

    @GetMapping(value = "initialize")
    public void initialize() {
        tictactoeService.initTtt();
    }
}