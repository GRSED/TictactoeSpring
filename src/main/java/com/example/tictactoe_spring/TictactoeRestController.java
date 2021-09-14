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
    public String draw(@RequestParam("rowIdx") int rowIdx, @RequestParam("colIdx") int colIdx) {
        return tictactoeService.draw(rowIdx, colIdx);
    }

    @GetMapping(value = "initialize")
    public void initialize() {
        tictactoeService.initTtt();
    }

    @GetMapping(value = "checkEnd")
    public String checkEnd(@RequestParam("rowIdx") int rowIdx
            , @RequestParam("colIdx") int colIdx
            , @RequestParam("length") int length
            , @RequestParam("mark") String mark) {
        return tictactoeService.checkEnd(rowIdx, colIdx, length, mark);
    }

    @GetMapping(value = "cancle")
    public void cancle() {
        tictactoeService.cancelTtt();
    }
}