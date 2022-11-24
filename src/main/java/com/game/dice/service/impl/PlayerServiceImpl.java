package com.game.dice.service.impl;

import com.game.dice.dto.PlayerDto;
import com.game.dice.dto.PlayerRequest;
import com.game.dice.exception.GameExceptionHandler;
import com.game.dice.service.PlayerService;
import org.springframework.stereotype.Service;

/**
 * Created by Dipu on 11/24/22.
 */

@Service
public class PlayerServiceImpl implements PlayerService {
    @Override
    public PlayerDto addPlayer(PlayerRequest player) throws Exception {
        throw new Exception();
    }
}
