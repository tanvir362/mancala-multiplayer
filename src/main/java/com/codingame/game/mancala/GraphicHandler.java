package com.codingame.game.mancala;

import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.codingame.gameengine.module.entities.Sprite;

public class GraphicHandler {
    private GraphicEntityModule graphicEntityModule;

    private static int ROW2Y = 370;
    private static int ROW1Y = 710;
    private static int CUP_GAPE = 230;

    private Sprite[] cups;
    private Sprite[] no_plates;

    private Sprite[] pots;
    private Sprite[] pot_no_plates;


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
            no_plates[i] = graphicEntityModule.createSprite()
                    .setImage("no_plate.png")
                    .setAnchor(0.5)
                    .setScale(1.5)
                    .setY(i<6 ? ROW1Y+180 : ROW2Y-190)
                    .setX(150 + (i<6 ? (i+1)*CUP_GAPE : (11-i+1)*CUP_GAPE));
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
            pot_no_plates[i] = graphicEntityModule.createSprite()
                    .setImage("no_plate.png")
                    .setAnchor(0.5)
                    .setScale(1.5)
                    .setY(i==0 ? no_plates[11].getY() : no_plates[0].getY())
                    .setX(i==0 ? 120 : 1920-120);

        }
    }
    public GraphicHandler(GraphicEntityModule graphicEntityModule){
        this.graphicEntityModule = graphicEntityModule;
        cups = new Sprite[12];
        no_plates = new Sprite[12];
        pots = new Sprite[2];
        pot_no_plates = new Sprite[2];

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
