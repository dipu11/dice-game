package com.game.dice.exception;

/**
 * Created by Dipu on 11/24/22.
 */
public class GameExceptionHandler extends Exception{

    public GameExceptionHandler(String msg){
        super(msg);
    };

    public GameExceptionHandler(Throwable throwable){
        super(throwable);
    };

    public GameExceptionHandler(String cause, Throwable throwable){
        super(cause, throwable);
    };
}
