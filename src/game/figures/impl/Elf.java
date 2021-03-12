package game.figures.impl;

import game.figures.Figure;

import java.awt.*;

public class Elf extends Figure {

    public Elf(Color color) {
        super("E", color, 5, 1, 10, 3, 3);
    }

    @Override
    public boolean isValidMove(int currentRow, int currentCol, int desiredRow, int desiredCol) {
        return false;
    }
}
