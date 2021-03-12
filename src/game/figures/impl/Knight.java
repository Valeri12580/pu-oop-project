package game.figures.impl;

import game.figures.Figure;

import java.awt.*;

public class Knight  extends Figure {

    public Knight(Color color) {
        super("K", color, 8, 3, 15, 1, 1);
    }

    @Override
    public boolean isValidMove(int currentRow, int currentCol, int desiredRow, int desiredCol) {
         int resultY=Math.abs(currentRow-desiredRow);
         int resultX=Math.abs(currentCol-desiredCol);


        return (resultY==1 && resultX==0)||(resultY==0 && resultX==1) ;
    }
}
