package game.figures.impl;

import game.figures.Figure;

import java.awt.*;

public class Dwarf extends Figure {
    public Dwarf(Color color) {
        super("D", color, 6, 2, 12, 2, 2);
    }

    @Override
    public boolean isValidMove(int currentRow, int currentCol, int desiredRow, int desiredCol) {
        return false;
    }
}
