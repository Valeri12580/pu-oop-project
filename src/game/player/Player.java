package game.player;

import game.figures.Figure;
import game.stats.Stats;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;

    private List<Figure> figures;


    private Stats stats;


    public Player(int id) {
        this.id = id;

        this.stats = new Stats();
        this.figures = new ArrayList<>();


    }


}
