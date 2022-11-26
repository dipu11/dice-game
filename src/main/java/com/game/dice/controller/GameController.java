package com.game.dice.controller;

import com.game.dice.dto.PlayerDto;
import com.game.dice.dto.PlayerRequest;
import com.game.dice.dto.Response;
import com.game.dice.exception.GameExceptionHandler;
import com.game.dice.service.GameService;
import com.game.dice.service.PlayerService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Dipu on 11/21/22.
 */

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final PlayerService playerService;
    private final GameService gameService;

    @Autowired
    GameController(
            PlayerService playerService,
            GameService gameService
    ){
        this.playerService= playerService;
        this.gameService= gameService;
    }

    @PostMapping("/player")
    public Response<PlayerDto> addPlayer(
            @Valid @RequestBody PlayerRequest playerRequest
    ) throws Exception {
        PlayerDto playerDto= playerService.addPlayer(playerRequest);
        return new Response<>(playerDto);
    }

    @PostMapping("/start")
    public Response<Boolean> startGame() throws GameExceptionHandler {

        return new Response<>(gameService.startGame());
    }

    @PostMapping("/stop")
    public Response<Boolean> stopGame() throws GameExceptionHandler {

        return new Response<>(gameService.stopGame());
    }

    @GetMapping("/score")
    public Response<Object> getGameScore(){

        return new Response<>(gameService.getScore());
    }
}
