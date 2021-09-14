package com.example.tictactoe_spring;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TictactoeMapper {

    @Insert("INSERT INTO TTT VALUES(TTT_NO_SEQ.NEXTVAL, #{ttt_draw}, #{ttt_row}, #{ttt_col})")
    int insertTtt(TictactoeDto tictactoeDto);

    @Delete("DELETE FROM TTT WHERE TTT_NO = (SELECT MAX(TTT_NO) FROM TTT)")
    int deleteTtt();

    @Delete("DELETE FROM TTT")
    int initTtt();

    @Select("SELECT COUNT(*) FROM TTT")
    int countTtt();
}
