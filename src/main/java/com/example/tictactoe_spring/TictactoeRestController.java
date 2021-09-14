package com.example.tictactoe_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TictactoeRestController {
    @Autowired
    TictactoeService tictactoeService;

    @GetMapping(value = "draw")
    public String draw() {
        String mark = "";
        
        if (tictactoeService.countTtt()%2 == 0) {
            mark = "X";
        } else {
            mark = "O";
        }

        tictactoeService.insertTtt(new TictactoeDto(0, mark));

        return mark;
    }

    @GetMapping(value = "init")
    public void init() {
        tictactoeService.initTtt();
    }
}