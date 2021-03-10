package game.stats;

import game.figures.Figure;

import java.util.ArrayList;
import java.util.List;

public class Stats {
    private int opponentPoints;
    private int numberOfRounds;
    private List<Figure> destroyedOpponentsFigures;
    private List<Figure> destroyedMineFigures;

    public Stats() {
        this.numberOfRounds=0;
        this.opponentPoints=0;
        this.destroyedMineFigures=new ArrayList<>();
        this.destroyedOpponentsFigures=new ArrayList<>();
    }
}
