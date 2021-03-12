package game.player;

import game.figures.Figure;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;

    private List<Figure> figures;

    private Point playerField;

    public Player(int id, int playerFieldFrom, int playerFieldTo) {
        this.id = id;
        this.figures = new ArrayList<>();
        playerField = new Point(playerFieldFrom, playerFieldTo);


    }

    public int getId() {
        return id;
    }

    public boolean isInPlayerBattlefield(int row) {

        return row <= playerField.getY() && row >= playerField.getX();
    }

    public boolean areFiguresEmpty() {
        return this.figures.size() == 0;
    }
}
