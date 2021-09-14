package com.example.tictactoe_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TictactoeService {
    @Autowired
    private TictactoeMapper tictactoeMapper;

    public int insertTtt(TictactoeDto tictactoeDto) {
        System.out.println("insert");
        return tictactoeMapper.insertTtt(tictactoeDto);
    }

    public int deleteTtt() {
        System.out.println("delete");
        return tictactoeMapper.deleteTtt();
    }

    public int initTtt() {
        System.out.println("init");
        return tictactoeMapper.initTtt();
    }

    public int countTtt() {
        System.out.println("count");
        return tictactoeMapper.countTtt();
    }
}
