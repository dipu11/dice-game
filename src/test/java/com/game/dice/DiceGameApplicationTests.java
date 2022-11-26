package com.game.dice;

import com.game.dice.dto.PlayerDto;
import com.game.dice.service.GameInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class DiceGameApplicationTests {

	/*private String BASE_ENDPOINT;
	private RestTemplate restTemplate;
	private GameInfo gameInfo;
	@Test
	void contextLoads() {
		restTemplate= new RestTemplate();
		gameInfo= new GameInfo();
		BASE_ENDPOINT="localhost:8080/dice-game/api/game";
	}

	@Test
	public void addPlayers(){
		PlayerDto player1= new PlayerDto(1L, "A", Integer.MIN_VALUE);
		PlayerDto player2= new PlayerDto(2L, "B", Integer.MIN_VALUE);
		PlayerDto player3= new PlayerDto(3L, "C", Integer.MIN_VALUE);
		PlayerDto player4= new PlayerDto(4L, "D", Integer.MIN_VALUE);

		restTemplate.postForEntity(BASE_ENDPOINT+"/player", player1, PlayerDto.class);
		restTemplate.postForEntity(BASE_ENDPOINT+"/player", player2, PlayerDto.class);
		restTemplate.postForEntity(BASE_ENDPOINT+"/player", player3, PlayerDto.class);
		restTemplate.postForEntity(BASE_ENDPOINT+"/player", player4, PlayerDto.class);

		AssertEq

	}*/

}
