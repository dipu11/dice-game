package com.game.dice.service;

import com.game.dice.dto.PlayerDto;
import com.game.dice.exception.GameExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Repository
public class GameInfo {
    private static Logger log = LoggerFactory.getLogger(GameInfo.class);
    private Queue<PlayerDto> playerList= new LinkedList<>();
    public  Boolean IS_GAME_ON= Boolean.FALSE;
    @Value("${max.player}")
    public int MAX_PLAYER; //needs to be configurable


    public boolean addPlayer(PlayerDto player) throws GameExceptionHandler {
        if(
            player!=null &&
            (this.playerList!=null && this.playerList.size()<MAX_PLAYER)
        ){
            if(isDuplicatePlayer(player))
                throw new GameExceptionHandler("Player with same name already existed!");

            //player.setScore(Integer.MIN_VALUE);
            this.playerList.add(player);
            log.debug("Player added- name:"+ player.getName()+", age:"+ player.getAge());
            return true;
        }

        //this is allowed only for new members, not for the existing ones
       /* else if(this.IS_GAME_ON)
            throw new GameExceptionHandler("Game already started!");*/
        else if(this.playerList.size() >= MAX_PLAYER)
            throw new GameExceptionHandler("Maximum player added!");
        else return false;
    }

    public int getTotalPlayer(){
        return this.playerList!=null ? this.playerList.size() : 0;
    }

    public PlayerDto getCurrentPlayer(){

        return (this.playerList!=null && !this.playerList.isEmpty()) ? this.playerList.peek() : null;
    }

    public PlayerDto removeCurrentPlayer(){
        if(this.playerList!=null && !this.playerList.isEmpty()){
            return this.playerList.poll();
        }
        return new PlayerDto();
    }

    public List<PlayerDto> getAllScores(){
        return (List)this.playerList;
    }

    private boolean isDuplicatePlayer(PlayerDto playerDto){
        if(this.playerList.isEmpty()){
            return false;
        }

        for(PlayerDto player: this.playerList){
            if(player.getName().equalsIgnoreCase(playerDto.getName()))
                return true;
        }
        return false;
    }
}
