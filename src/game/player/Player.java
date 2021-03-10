package game.player;

import game.figures.Figure;
import game.stats.Stats;

import java.awt.*;
import java.util.List;

public class Player {
    private int id;
    private List<Figure> figures;
    private Color color;
    private Stats stats;

    public Player(int id, List<Figure> figures, Color color) {
        this.id = id;
        this.figures = figures;
        this.color = color;
        this.stats=new Stats();
    }
}
