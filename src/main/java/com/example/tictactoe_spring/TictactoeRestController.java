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
        String mark = "";
        
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

    @GetMapping(value = "checkEnd")
    public String checkEnd(@RequestParam("rowIdx") int rowIdx, @RequestParam("colIdx") int colIdx, @RequestParam("length") int length, @RequestParam("mark") String mark) {
        if (tictactoeService.countTtt() < length * 2 - 1) {
            return "";
        }

        TictactoeDto tictactoeDto = new TictactoeDto(0, mark, rowIdx, colIdx);
        ReverseDiagonal reverseDiagonal = new ReverseDiagonal(length - 1, mark);

        if (tictactoeService.checkEndRow(tictactoeDto) == length) {
            return mark + " 승리";
        } else if (tictactoeService.checkEndCol(tictactoeDto) == length) {
            return mark + " 승리";
        } else if (rowIdx == colIdx) {
            if (tictactoeService.checkEndDiagonal(tictactoeDto) == length) {
                return mark + " 승리";
            }
        } else if (rowIdx + colIdx == length - 1) {
            System.out.println("역대각조건만족");
            if (tictactoeService.checkEndReverseDiagonal(reverseDiagonal) == length) {
                return mark + " 승리";
            }
        } else {
            if (tictactoeService.countTtt() == length * length) {
                return "무승부";
            }
        }

        return "";
    }

    @GetMapping(value = "cancle")
    public void cancle() {
        tictactoeService.deleteTtt();
    }
}