package game.player;

import game.figures.Figure;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;
    private int score;
    private List<Figure> figures;

    private Point playerField;

    public Player(int id, int playerFieldFrom, int playerFieldTo) {
        this.id = id;
        this.score=0;
        this.figures = new ArrayList<>();
        playerField = new Point(playerFieldFrom, playerFieldTo);


    }

    public int getId() {
        return id;
    }

    /**
     * check if the position is in the player battlefield
     * @param row
     * @return result
     */
    public boolean isInPlayerBattlefield(int row) {

        return row <= playerField.getY() && row >= playerField.getX();
    }

    /**
     * check if figures are empty
     * @return value
     */
    public boolean areFiguresEmpty() {
        return this.figures.size() == 0;
    }

    /**
     * remove figure
     * @param figure figure
     */
    public void removeFigure(Figure figure) {
        this.figures.remove(figure);
    }

    public void addFigure(Figure figure) {
        this.figures.add(figure);
    }

    /**
     * increase the score
     * @param points points
     */
    public void increaseScore(int points){
        this.score+=points;
    }

    /**
     * getter
     * @return scire
     */
    public int getScore() {
        return score;
    }
}
