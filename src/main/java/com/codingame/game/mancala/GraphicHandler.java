    public GraphicHandler(GraphicEntityModule graphicEntityModule){
        this.graphicEntityModule = graphicEntityModule;
        cups = new Sprite[12];
        no_plates = new Sprite[12];
        pots = new Sprite[2];
        pot_no_plates = new Sprite[2];

        drawBackgroundImage();
        drawBoardElements();
    }
}
