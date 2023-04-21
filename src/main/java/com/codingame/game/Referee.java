package com.codingame.game;
import java.util.List;

import com.codingame.game.mancala.InvalidActionException;
import com.codingame.game.mancala.Mancala;
import com.codingame.gameengine.core.AbstractPlayer.TimeoutException;
import com.codingame.gameengine.core.AbstractReferee;
import com.codingame.gameengine.core.MultiplayerGameManager;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.google.inject.Inject;

public class Referee extends AbstractReferee {
    // Uncomment the line below and comment the line under it to create a Solo Game
    // @Inject private SoloGameManager<Player> gameManager;
    @Inject private MultiplayerGameManager<Player> gameManager;
    @Inject private GraphicEntityModule graphicEntityModule;
    private Mancala game;

    @Override
    public void init() {
        // Initialize your game here.
        game = new Mancala(gameManager.getPlayer(0), gameManager.getPlayer(1));
        for(Player player: gameManager.getActivePlayers()){
            player.sendInputLine(String.valueOf(player.getIndex()+1));
        }
    }

    @Override
    public void gameTurn(int turn) {
        Player player = game.getCurrentPlayer();
        for(String inputLine: game.getInputLines()){
            player.sendInputLine(inputLine);
        }
        player.execute();

        try {
            List<String> outputs = player.getOutputs();
            // Check validity of the player output and compute the new game state
            int output = Integer.parseInt(outputs.get(0));
            try {
                game.playTurn(output);
            } catch (InvalidActionException e) {
                throw new RuntimeException(e);
            }

            if(game.isGameOver()){
                gameManager.endGame();
            }

        } catch (TimeoutException e) {
            player.deactivate(String.format("$%d timeout!", player.getIndex()));
        }
    }
}
