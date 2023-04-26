package com.codingame.game.mancala;

import com.codingame.game.Player;

import java.util.Arrays;

public class Mancala {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private int[] board;
    private int pot1;
    private  int pot2;
    private boolean gameOver;

    private GraphicHandler graphicHandler;


    private boolean isInPlayer1Court(int holeIndex){
        return holeIndex>=0 && holeIndex<=5;
    }

    private boolean isInPlayer2Court(int holeIndex){
        return holeIndex>=6 && holeIndex<=11;
    }

    private boolean checkGameOverCondition(){
        boolean player1Empty = true;
        boolean player2Empty = true;
        for(int i=0; i<6; i++){
            if(board[i]>0){
                player1Empty = false;
                break;
            }
        }

        for(int i=6; i<12; i++){
            if(board[i]>0){
                player2Empty = false;
            }
        }

        return player1Empty || player2Empty;
    }

    public Mancala(Player player1, Player player2, GraphicHandler graphicHandler){
        this.player1 = player1;
        this.player2 = player2;

        // Initializing game
        this.gameOver = false;
        this.board = new int[12];
        for(int i=0; i<board.length; i++) this.board[i] = 4;
        this.pot1 = 0;
        this.pot2 = 0;
        this.currentPlayer = this.player1;

        // Graphic initialization
        this.graphicHandler = graphicHandler;
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    public void playTurn(int hole) throws InvalidActionException{
        if(gameOver) throw new InvalidActionException("Game Over");
        boolean freeTurn = false;
        boolean captured = false;

        int holeIndex = hole-1;
        if(currentPlayer==player1 && !isInPlayer1Court(holeIndex)) throw new InvalidActionException("Invalid hole");
        if(currentPlayer==player2 && !isInPlayer2Court(holeIndex)) throw new InvalidActionException("Invalid hole");
        if(board[holeIndex]<1) throw new InvalidActionException("Picking from empty hole");

        int pick = board[holeIndex];
        graphicHandler.pickCupStones(holeIndex);
        board[holeIndex] = 0;

        int currentHole = (holeIndex+1)%12;
        int iThStone = 0;
        while(pick>0){
            if(currentHole==6 && currentPlayer==player1){
                pot1 += 1;
                pick -= 1;

                graphicHandler.putAStoneToPot(holeIndex, 0, iThStone);
                iThStone += 1;
            }
            else if(currentHole==0 && currentPlayer==player2){
                pot2 += 1;
                pick -= 1;

                graphicHandler.putAStoneToPot(holeIndex, 1, iThStone);
                iThStone += 1;
            }
            if(pick == 0){
                // Last stone dropped into players pot
                freeTurn = true;
                break;
            }

            if(pick == 1){
                // Last stone will dropped into hole
                if(board[currentHole]==0 && currentPlayer==player1 && isInPlayer1Court(currentHole) && board[11-currentHole]>=1){
                    // Captured by player1
                    pot1 += 1;
                    pot1 += board[11-currentHole];
                    board[11-currentHole] = 0;

                    graphicHandler.capture(11-currentHole, currentHole, 0);
                    captured = true;
                }
                else if(board[currentHole]==0 && currentPlayer==player2 && isInPlayer2Court(currentHole) && board[11-currentHole]>=1){
                    // Captured by player2
                    pot2 += 1;
                    pot2 += board[11-currentHole];
                    board[11-currentHole] = 0;

                    graphicHandler.capture(11-currentHole, currentHole, 1);
                    captured = true;
                }
            }

            if(!captured){
                board[currentHole] += 1;
                graphicHandler.putAStoneToCup(holeIndex, currentHole, iThStone);
                pick -= 1;
                iThStone += 1;
            }

            currentHole += 1;
            currentHole %= 12;
        }

        if(checkGameOverCondition()){
            for(int i=0; i<6; i++){
                pot1 += board[i];
                board[i] = 0;
            }

            for(int i=6; i<12; i++){
                pot2 += board[i];
                board[i] = 0;
            }

            gameOver = true;
        }

        if(freeTurn) return;
        currentPlayer = currentPlayer==player1? player2 : player1;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Player getWinner() {

        if(pot1>pot2){
            return player1;
        }
        else if (pot2>pot1){
            return player2;
        }

        return null;
    }

    public String[] getInputLines(){

        return new String[]{
                new StringBuilder(String.join(" ", Arrays.stream(board, 6, 12).mapToObj(String::valueOf).toArray(String[]::new))).reverse().toString(),
                String.join(" ", Arrays.stream(board, 0, 6).mapToObj(String::valueOf).toArray(String[]::new)),
                String.valueOf(pot1),
                String.valueOf(pot2)
        };
    }
}
