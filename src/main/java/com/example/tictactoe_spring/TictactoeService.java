package com.example.tictactoe_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TictactoeService {
    @Autowired
    private TictactoeMapper tictactoeMapper;

    public String draw(int rowIdx, int colIdx) {
        String mark = "";

        if (tictactoeMapper.countTtt()%2 == 0) {
            mark = "X";
        } else {
            mark = "O";
        }

        tictactoeMapper.insertTtt(new TictactoeDto(0, mark, rowIdx, colIdx));

        return mark;
    }

    public String checkEnd(int rowIdx, int colIdx, int length, String mark) {
        if (tictactoeMapper.countTtt() < length * 2 - 1) {
            return "";
        }

        TictactoeDto tictactoeDto = new TictactoeDto(0, mark, rowIdx, colIdx);

        if (tictactoeMapper.checkEndRow(tictactoeDto) == length) {
            return mark + " 승리";
        }

        if (tictactoeMapper.checkEndCol(tictactoeDto) == length) {
            return mark + " 승리";
        }

        if (rowIdx == colIdx) {
            if (tictactoeMapper.checkEndDiagonal(tictactoeDto) == length) {
                return mark + " 승리";
            }
        }

        ReverseDiagonal reverseDiagonal = new ReverseDiagonal(length - 1, mark);

        if (rowIdx + colIdx == length - 1) {
            if (tictactoeMapper.checkEndReverseDiagonal(reverseDiagonal) == length) {
                return mark + " 승리";
            }
        }

        if (tictactoeMapper.countTtt() == length * length) {
            return "무승부";
        }
        return "";
    }

    public int cancelTtt() {
        System.out.println("delete");
        return tictactoeMapper.cancelTtt();
    }

    public int initTtt() {
        System.out.println("init");
        return tictactoeMapper.initTtt();
    }
}
