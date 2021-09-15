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
    public String draw(@RequestParam("rowIdx") int rowIdx, @RequestParam("colIdx") int colIdx, @RequestParam("sessionId") String sessionId) {
        return tictactoeService.draw(rowIdx, colIdx, sessionId);
    }

    @GetMapping(value = "initialize")
    public void initialize(@RequestParam("sessionId") String sessionId) {
        tictactoeService.initialize(sessionId);
    }

    @GetMapping(value = "checkEnd")
    public String checkEnd(@RequestParam("rowIdx") int rowIdx
            , @RequestParam("colIdx") int colIdx
            , @RequestParam("length") int length
            , @RequestParam("mark") String mark
            , @RequestParam("sessionId") String sessionId) {
        return tictactoeService.checkEnd(rowIdx, colIdx, length, mark, sessionId);
    }

    @GetMapping(value = "cancel")
    public void cancel(@RequestParam("sessionId") String sessionId) {
        tictactoeService.cancel(sessionId);
    }
}