package com.game.dice.service;

import com.game.dice.dto.PlayerDto;
import com.game.dice.dto.PlayerRequest;
import com.game.dice.exception.GameExceptionHandler;

/**
 * Created by Dipu on 11/24/22.
 */
public interface PlayerService {

    PlayerDto addPlayer(PlayerRequest player) throws Exception;
}
