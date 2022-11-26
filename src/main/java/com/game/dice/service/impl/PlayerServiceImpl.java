package com.game.dice.service.impl;

import com.game.dice.dto.PlayerDto;
import com.game.dice.dto.PlayerRequest;
import com.game.dice.service.GameInfo;
import com.game.dice.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Dipu on 11/24/22.
 */

@Service
public class PlayerServiceImpl implements PlayerService {
    private static Logger log = LoggerFactory.getLogger(PlayerServiceImpl.class);
    private final GameInfo gameInfo;

    @Autowired
    public PlayerServiceImpl(GameInfo gameInfo){
        this.gameInfo= gameInfo;

    }
    @Override
    public PlayerDto addPlayer(PlayerRequest playerRequest) throws Exception {

        PlayerDto player= new PlayerDto(playerRequest.getAge(), playerRequest.getName(), Integer.MIN_VALUE);
        gameInfo.addPlayer(player);
        log.info("Player: "+ player.getName()+" added");
       return player;
    }
}
