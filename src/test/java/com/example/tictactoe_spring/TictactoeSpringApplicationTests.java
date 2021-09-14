package com.example.tictactoe_spring;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TictactoeSpringApplicationTests {
	@Autowired
	TictactoeService tictactoeService;

	@Test
	public void serviceTest() {
		System.out.println(tictactoeService.countTtt());
	}

	@Test
	public void servieTest2() {
		tictactoeService.deleteTtt();
	}

	@Test
	public void serviceTest3() {
		tictactoeService.insertTtt(new TictactoeDto(0, "X", 0, 0));
	}
}
