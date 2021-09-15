package com.example.tictactoe_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TictactoeService {
    @Autowired
    private TictactoeMapper tictactoeMapper;

    public String draw(int rowIdx, int colIdx, String sessionId) {
        System.out.println("draw");
        String mark = "";

        if (tictactoeMapper.countTtt(sessionId)%2 == 0) {
            mark = "X";
        } else {
            mark = "O";
        }

        tictactoeMapper.insertTtt(new TictactoeDto(0, mark, rowIdx, colIdx, sessionId));

        return mark;
    }

    public String checkEnd(int rowIdx, int colIdx, int length, String mark, String sessionId) {
        System.out.println("checkEnd");
        if (tictactoeMapper.countTtt(sessionId) < length * 2 - 1) {
            return "";
        }

        TictactoeDto tictactoeDto = new TictactoeDto(0, mark, rowIdx, colIdx, sessionId);

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

        ReverseDiagonal reverseDiagonal = new ReverseDiagonal(length - 1, mark, sessionId);

        if (rowIdx + colIdx == length - 1) {
            if (tictactoeMapper.checkEndReverseDiagonal(reverseDiagonal) == length) {
                return mark + " 승리";
            }
        }

        if (tictactoeMapper.countTtt(sessionId) == length * length) {
            return "무승부";
        }
        return "";
    }

    public int cancel(String sessionId) {
        System.out.println("cancel");
        return tictactoeMapper.cancelTtt(sessionId);
    }

    public int initialize(String sessionId) {
        System.out.println("initialize");
        return tictactoeMapper.initTtt(sessionId);
    }
}
