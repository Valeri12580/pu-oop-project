package game.figures.impl;

import game.figures.Figure;

import java.awt.*;

public class Dwarf extends Figure {
    public Dwarf(Color color) {
        super("D", color, 6, 2, 12, 2, 2);
    }

    @Override
    public boolean isValidMove(int currentRow, int currentCol, int desiredRow, int desiredCol) {
        int resultY=Math.abs(currentRow-desiredRow);
        int resultX=Math.abs(currentCol-desiredCol);

        return (resultY==2 && resultX==0)||(resultY==0 && resultX==2);
    }

    @Override
    public boolean isAttackValid(int currentFigureRow, int currentFigureCol, int attackedFigureRow, int attackedFigureCol) {
        int resultY=Math.abs(currentFigureRow-attackedFigureRow);
        int resultX=Math.abs(currentFigureCol-attackedFigureCol);
        return (resultY==2 && resultX==0)||(resultY==0 && resultX==2);
    }
}
