package com.codingame.game.mancala;

import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.codingame.gameengine.module.entities.Sprite;
import com.codingame.gameengine.module.entities.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphicHandler {
    private GraphicEntityModule graphicEntityModule;

    private static final int ROW2Y = 370;
    private static final int ROW1Y = 710;
    private static final int CUP_GAPE = 230;

    private Sprite[] cups;
    private Sprite[] noPlates;

    private Sprite[] pots;
    private Sprite[] potNoPlates;

    private List<Sprite>[] cupStones;
    private Text[] countTexts;

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

    private int randomXOnPot(int idx){
        int x = pots[idx].getX();
        int dx = new Random().nextInt(101) - 50;

        return x+dx;
    }

    private int randomYonPot(int idx){
        int y = pots[idx].getY();
        int dy = new Random().nextInt(141) - 70;

        return y+dy;
    }


    private void drawBoardElements(){
        for(int i=0; i<12; i++){
            // Placing cup on board
            cups[i] = graphicEntityModule.createSprite()
                    .setImage("cup.png")
                    .setAnchor(0.5)
                    .setScale(1.5)
                    .setY(i<6 ? ROW1Y : ROW2Y)
                    .setX(150 + (i<6 ? (i+1)*CUP_GAPE : (11-i+1)*CUP_GAPE));

            // Placing cup's count no plate for each cup
            noPlates[i] = graphicEntityModule.createSprite()
                    .setImage("no_plate.png")
                    .setAnchor(0.5)
                    .setScale(1.5)
                    .setY(i<6 ? ROW1Y+180 : ROW2Y-190)
                    .setX(150 + (i<6 ? (i+1)*CUP_GAPE : (11-i+1)*CUP_GAPE));

            // Placing initial count text
            countTexts[i] = graphicEntityModule.createText("4")
                    .setAnchor(0.5)
                    .setX(noPlates[i].getX())
                    .setY(noPlates[i].getY())
                    .setFontSize(50)
                    .setFillColor(0xffffff);

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
                    .setX(i==0 ? 1920-120 : 120);

            // Placing no plate for each mancala pot
            potNoPlates[i] = graphicEntityModule.createSprite()
                    .setImage("no_plate.png")
                    .setAnchor(0.5)
                    .setScale(1.5)
                    .setY(i==0 ? noPlates[0].getY() : noPlates[11].getY())
                    .setX(i==0 ? 1920-120 : 120);

            cupStones[12+i] = new ArrayList<>();

            countTexts[12+i] = graphicEntityModule.createText("0")
                    .setAnchor(0.5)
                    .setX(potNoPlates[i].getX())
                    .setY(potNoPlates[i].getY())
                    .setFontSize(50)
                    .setFillColor(0xffffff);

        }
    }
    public GraphicHandler(GraphicEntityModule graphicEntityModule){
        this.graphicEntityModule = graphicEntityModule;
        cups = new Sprite[12];
        noPlates = new Sprite[12];
        pots = new Sprite[2];
        potNoPlates = new Sprite[2];
        cupStones = new List[14]; // Also keeps pot stones at indexes 12 and 13
        countTexts = new Text[14];

        drawBackground();
        drawBoardElements();
    }

    public void updateCountText(int idx){
        countTexts[idx].setText(String.valueOf(cupStones[idx].size()));
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
