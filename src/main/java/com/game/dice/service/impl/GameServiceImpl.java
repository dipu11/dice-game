package com.game.dice.service.impl;

import com.game.dice.dto.DiceDto;
import com.game.dice.dto.PlayerDto;
import com.game.dice.exception.GameExceptionHandler;
import com.game.dice.service.GameInfo;
import com.game.dice.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private static Logger log = LoggerFactory.getLogger(GameServiceImpl.class);
    private final RestTemplate restTemplate;
    private final GameInfo gameInfo;
    MyThread gameDaemonThread =null;

    @Value("${dice.server.score.api}")
    private String DICE_ENDPOINT;

    @Value(("${min.player}"))
    private int MIN_PLAYER;

    @Value(("${min.winning.score}"))
    private int WINNING_SCORE;

    @Autowired
    public GameServiceImpl(
            RestTemplate restTemplate,
            GameInfo gameInfo
    ){
        this.restTemplate= restTemplate;
        this.gameInfo= gameInfo;
    }


    @Override
    public DiceDto drawDice() {

        DiceDto dice= restTemplate.getForEntity(DICE_ENDPOINT, DiceDto.class).getBody();
        //log.info("Dice number:"+ dice.getScore());
        if(dice.getScore()<0){
            dice.setScore(0);
        }
        return dice;
    }

    @Override
    public Boolean startGame() throws GameExceptionHandler {
        if(gameInfo.getTotalPlayer() < MIN_PLAYER){
            throw new GameExceptionHandler("Currently there are:"+
                    gameInfo.getTotalPlayer()+
                    " player(s). Min player needed to play the game is:"+ MIN_PLAYER);
        }

        if(gameInfo.getTotalPlayer() > gameInfo.MAX_PLAYER){
            throw new GameExceptionHandler("Currently there are:"+
                    gameInfo.getTotalPlayer()+
                    " player(s). Max players allowed to play the game is:"+ gameInfo.MAX_PLAYER);
        }

        if(gameInfo.IS_GAME_ON){
            throw new GameExceptionHandler("Game already started!");
        }

        MyThread gameThread= new MyThread();
        gameThread.setDaemon(true);
        gameThread.start();
        this.gameDaemonThread = gameThread;
        return Boolean.TRUE;
    }

    @Override
    public void startGameEngine() throws GameExceptionHandler {
        log.info("\n\n");
        log.info("~~~~~~ Game Started ~~~~~~");
        log.info("------< Total players participating the game:"+ gameInfo.getTotalPlayer()+" >------");
        gameInfo.IS_GAME_ON= Boolean.TRUE;
       try {
           while(gameInfo.IS_GAME_ON){
               PlayerDto currentPlayer=gameInfo.getCurrentPlayer();
               Thread.sleep(1000l);
               if(currentPlayer==null){
                   throw new GameExceptionHandler("No valid player found to play!");
               }

               Integer curScore=(currentPlayer.getScore()!=Integer.MIN_VALUE) ? currentPlayer.getScore() : null;
               log.info("\n");
               log.info("===============================================================");
               log.info("+++++++++< Current Player- Name:"+ currentPlayer.getName()+", age:"+ currentPlayer.getAge()+", current score:"+curScore+" >++++++++++");
               log.info("---------------------------------------------------------------");

               boolean flag=false;
               while(true){
                   Thread.sleep(500l);
                   DiceDto dice= this.drawDice();
                   log.info("---> Dice rolling score:"+ dice.getScore());

                   if(currentPlayer.getScore()==Integer.MIN_VALUE && dice.getScore() == 6){ //first dice score: 6
                       currentPlayer.setScore(0);
                       log.info("---> player:"+currentPlayer.getName()+" just passed the initial scoring barrier!");
                   }
                   else if(dice.getScore() == 6){ //again 6 after first successful dice score:6
                       currentPlayer.setScore(currentPlayer.getScore()+ dice.getScore());
                   }
                   else if(currentPlayer.getScore()!= Integer.MIN_VALUE){ //dice score: 1-5 for players who has started scoring
                       if(dice.getScore()==4){
                           int score= (currentPlayer.getScore()==0) ? Integer.MIN_VALUE : currentPlayer.getScore()-4; // if scores consecutive 6 and 4
                           currentPlayer.setScore(score);
                           log.info("---> player:"+currentPlayer.getName()+" got the negative score(-4)!");
                       }
                       else{
                           currentPlayer.setScore(currentPlayer.getScore()+dice.getScore());
                       }

                       this.gameInfo.removeCurrentPlayer();
                       this.gameInfo.addPlayer(currentPlayer);
                       //log.info("---> player:"+currentPlayer.getName()+", scored:"+ currentPlayer.getScore());
                       flag=true;
                   }
                   else{
                       this.gameInfo.removeCurrentPlayer();
                       this.gameInfo.addPlayer(currentPlayer);
                       flag=true;
                   }

                   log.info("::---- player:"+currentPlayer.getName()+", scored:"+ currentPlayer.getScore());

                   if(currentPlayer.getScore() >= WINNING_SCORE){
                       gameInfo.IS_GAME_ON=Boolean.FALSE;
                       log.info("\n\n");
                       log.info("======================================================================================");
                       log.info("============================>>            Winner Found               <<===================");
                       log.info("||****** The winner is- name:"+ currentPlayer.getName()+", id:"+ currentPlayer.getAge()+", Score:"+currentPlayer.getScore()+" **** ||");
                       log.info("======================================================================================");
                       break;
                   }
                   else if(flag){
                       break;
                   }
               }
           }
       }
       catch (Exception e){
           log.error("game engine error:"+ e.getMessage());
       }
    }

    @Override
    public List<PlayerDto> getScore() {
        return gameInfo.getAllScores();
    }

    @Override
    public Boolean stopGame() throws GameExceptionHandler{

        if(gameDaemonThread ==null){
            log.error("Game not started yet!");
            throw new GameExceptionHandler("Game not started yet!");
        }

        if(!gameDaemonThread.isAlive()){
            log.error("Game already stopped!");
            throw new GameExceptionHandler("Game already stopped!");
        }

        log.info("\n\n");
        log.info("-------- Stopping game engine...");
        log.info("isGameEngineAlive:"+ gameDaemonThread.isAlive());
        if(gameDaemonThread==null)
            throw new GameExceptionHandler("Invalid game state");

        if(gameInfo.IS_GAME_ON){
            throw new GameExceptionHandler("Game is still going on!");
        }
        gameDaemonThread.stop();
        log.info("isGameEngineAlive:"+ gameDaemonThread.isAlive());
        log.info("-------- Game engine stopped! --------");
        log.info("\n\n");

        return Boolean.TRUE;
    }

    public class MyThread extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                startGameEngine();
            } catch (GameExceptionHandler e) {
                throw new RuntimeException(e);
            }
        }
    }
}
