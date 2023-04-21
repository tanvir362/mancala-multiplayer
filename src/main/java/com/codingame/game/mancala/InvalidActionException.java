package com.codingame.game.mancala;

public class InvalidActionException extends Exception{
    public InvalidActionException(String message){
        super(message);
    }

    public InvalidActionException(){
        super();
    }
}
