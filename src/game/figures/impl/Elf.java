package game.figures.impl;

import game.figures.Figure;

import java.awt.*;

public class Elf extends Figure {

    public Elf(Color color) {
        super("E", color, 5, 1, 10, 3, 3);
    }

    @Override
    public boolean isValidMove(int currentRow, int currentCol, int desiredRow, int desiredCol) {
        int resultY=Math.abs(currentRow-desiredRow);
        int resultX=Math.abs(currentCol-desiredCol);

        boolean mainDiagonalResult=(resultY==3 && resultX==0)||(resultY==0 && resultX==3);

        boolean gDialognalResult=(resultY==2 && resultX==1) || (resultY==1 && resultX==2);


        return mainDiagonalResult || gDialognalResult;
    }
}
