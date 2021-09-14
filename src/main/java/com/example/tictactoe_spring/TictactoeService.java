package com.example.tictactoe_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TictactoeService {
    @Autowired
    private TictactoeMapper tictactoeMapper;

    public int insertTtt(TictactoeDto tictactoeDto) {
        System.out.println("insert");
        return tictactoeMapper.insertTtt(tictactoeDto);
    }

    public int cancelTtt() {
        System.out.println("delete");
        return tictactoeMapper.cancelTtt();
    }

    public int initTtt() {
        System.out.println("init");
        return tictactoeMapper.initTtt();
    }

    public int countTtt() {
        System.out.println("count");
        return tictactoeMapper.countTtt();
    }

    public int checkEndRow(TictactoeDto tictactoeDto) {
        System.out.println("checkEndRow");
        return tictactoeMapper.checkEndRow(tictactoeDto);
    }

    public int checkEndCol(TictactoeDto tictactoeDto) {
        System.out.println("checkEndCol");
        return tictactoeMapper.checkEndCol(tictactoeDto);
    }

    public int checkEndDiagonal(TictactoeDto tictactoeDto) {
        System.out.println("checkEndDiagonal");
        return tictactoeMapper.checkEndDiagonal(tictactoeDto);
    }

    public int checkEndReverseDiagonal(ReverseDiagonal reverseDiagonal) {
        System.out.println("checkEndReverseDiagonal");
        return tictactoeMapper.checkEndReverseDiagonal(reverseDiagonal);
    }
}
