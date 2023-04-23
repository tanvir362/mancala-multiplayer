package com.codingame.game.mancala;

import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.codingame.gameengine.module.entities.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphicHandler {
    private GraphicEntityModule graphicEntityModule;

    private static int ROW2Y = 370;
    private static int ROW1Y = 710;
    private static int CUP_GAPE = 230;

    private Sprite[] cups;
    private Sprite[] noPlates;

    private Sprite[] pots;
    private Sprite[] potNoPlates;

    private List<Sprite>[] cupStones;

    private int randomXOnCup(int cup_idx){
        int x = cups[cup_idx].getX();
        int dx = new Random().nextInt(101) - 50;

        return x+dx;
    }

    private int randomYonCup(int cup_idx){
        int y = cups[cup_idx].getY();
        int dy = new Random().nextInt(101) - 50;

        return y+dy;
    }


    public void drawBoardElements(){
        for(int i=0; i<12; i++){
            // Placing cup on board
            cups[i] = graphicEntityModule.createSprite()
                    .setImage("cup.png")
                    .setAnchor(0.5)
                    .setScale(1.5)
                    .setY(i<6 ? ROW1Y : ROW2Y)
                    .setX(150 + (i<6 ? (i+1)*CUP_GAPE : (11-i+1)*CUP_GAPE));

            // Placing cup's count no each plate
            noPlates[i] = graphicEntityModule.createSprite()
                    .setImage("no_plate.png")
                    .setAnchor(0.5)
                    .setScale(1.5)
                    .setY(i<6 ? ROW1Y+180 : ROW2Y-190)
                    .setX(150 + (i<6 ? (i+1)*CUP_GAPE : (11-i+1)*CUP_GAPE));

            // Placing initial stones in each cup
            cupStones[i] = new ArrayList<>();
            for(int j=0; j<4; j++){
                 cupStones[i].add(
                         graphicEntityModule.createSprite()
                                 .setImage(String.format("m%d_s.png", j+1))
                                 .setScale(1.2)
                                 .setAnchor(0.5)
                                 .setX(randomXOnCup(i))
                                 .setY(randomYonCup(i))
                 );
            }
        }

        for(int i=0; i<2; i++){
            // Placing mancala pot for each player
            pots[i] = graphicEntityModule.createSprite()
                    .setImage("pot.png")
                    .setAnchor(0.5)
                    .setScale(1.5)
                    .setY(540)
                    .setX(i==0 ? 120 : 1920-120);

            // Placing no plate for each mancala pot
            potNoPlates[i] = graphicEntityModule.createSprite()
                    .setImage("no_plate.png")
                    .setAnchor(0.5)
                    .setScale(1.5)
                    .setY(i==0 ? noPlates[11].getY() : noPlates[0].getY())
                    .setX(i==0 ? 120 : 1920-120);

        }
    }
    public GraphicHandler(GraphicEntityModule graphicEntityModule){
        this.graphicEntityModule = graphicEntityModule;
        cups = new Sprite[12];
        noPlates = new Sprite[12];
        pots = new Sprite[2];
        potNoPlates = new Sprite[2];
        cupStones = new List[14];

        drawBackground();
        drawBoardElements();
    }
    public void drawBackground(){
        graphicEntityModule.createSprite()
                .setImage("background.jpg")
                .setScaleX(1.5)
                .setScaleY(1.35)
                .setAnchor(0)
                .setX(0)
                .setY(0);
    }
}
