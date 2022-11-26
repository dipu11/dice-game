package com.game.dice.service;

import com.game.dice.dto.DiceDto;
import com.game.dice.dto.PlayerDto;
import com.game.dice.exception.GameExceptionHandler;

import java.util.List;

public interface GameService {

    DiceDto drawDice();

    Boolean startGame() throws GameExceptionHandler;

    void startGameEngine() throws GameExceptionHandler;

    List<PlayerDto> getScore();

    Boolean stopGame() throws GameExceptionHandler;

}
