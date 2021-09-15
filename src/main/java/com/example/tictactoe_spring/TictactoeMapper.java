package com.example.tictactoe_spring;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TictactoeMapper {

    @Insert("INSERT INTO TTT VALUES(TTT_NO_SEQ.NEXTVAL, #{ttt_draw}, #{ttt_row}, #{ttt_col}, #{ttt_sessionId})")
    int insertTtt(TictactoeDto tictactoeDto);

    @Delete("DELETE FROM TTT WHERE TTT_SESSIONID = #{ttt_sessionId} AND TTT_NO = (SELECT MAX(TTT_NO) FROM TTT)")
    int cancelTtt(String ttt_sessionId);

    @Delete("DELETE FROM TTT WHERE TTT_SESSIONID = #{ttt_sessionId}")
    int initTtt(String ttt_sessionId);

    @Select("SELECT COUNT(*) FROM TTT WHERE TTT_SESSIONID = #{ttt_sessionId}")
    int countTtt(String ttt_sessionId);

    @Select("SELECT COUNT(*) FROM TTT WHERE TTT_SESSIONID = #{ttt_sessionId} AND TTT_DRAW = #{ttt_draw} AND TTT_ROW = #{ttt_row}")
    int checkEndRow(TictactoeDto tictactoeDto);

    @Select("SELECT COUNT(*) FROM TTT WHERE TTT_SESSIONID = #{ttt_sessionId} AND TTT_DRAW = #{ttt_draw} AND TTT_COL = #{ttt_col}")
    int checkEndCol(TictactoeDto tictactoeDto);

    @Select("SELECT COUNT(*) FROM TTT WHERE TTT_SESSIONID = #{ttt_sessionId} AND TTT_DRAW = #{ttt_draw} AND TTT_COL = TTT_ROW")
    int checkEndDiagonal(TictactoeDto tictactoeDto);

    @Select("SELECT COUNT(*) FROM TTT WHERE TTT_SESSIONID = #{ttt_sessionId} AND TTT_DRAW = #{ttt_draw} AND TTT_COL + TTT_ROW = #{reverseDiagonalSum}")
    int checkEndReverseDiagonal(ReverseDiagonal reverseDiagonal);
}
