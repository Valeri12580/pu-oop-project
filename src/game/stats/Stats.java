package game.stats;

import game.figures.Figure;

import java.util.ArrayList;
import java.util.List;

public class Stats {

    private int numberOfRounds;
    private List<Figure> destroyedOFirstPlayerFigures;
    private List<Figure> destroyedSecondFigures;

    public Stats() {

        this.numberOfRounds = 0;
        this.destroyedSecondFigures = new ArrayList<>();
        this.destroyedOFirstPlayerFigures = new ArrayList<>();
    }

    /**
     * increase the number of rounds
     */
    public void increaseNumberOfRounds() {
        numberOfRounds++;
    }

    public void addDestroyedFigure(Figure figure, int playerId) {
        if (playerId == 1) {
            destroyedSecondFigures.add(figure);
        } else {
            destroyedOFirstPlayerFigures.add(figure);
        }
    }

    public List<Figure> getDestroyedOFirstPlayerFigures() {
        return destroyedOFirstPlayerFigures;
    }

    public List<Figure> getDestroyedSecondFigures() {
        return destroyedSecondFigures;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }
}
