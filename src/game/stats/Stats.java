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

    /**
     * add destroyed figure to the stats
     * @param figure figure
     * @param playerId player
     */
    public void addDestroyedFigure(Figure figure, int playerId) {
        if (playerId == 1) {
            destroyedSecondFigures.add(figure);
        } else {
            destroyedOFirstPlayerFigures.add(figure);
        }
    }

    /**
     * getter
     * @return destroyed figures of first player
     */
    public List<Figure> getDestroyedOFirstPlayerFigures() {
        return destroyedOFirstPlayerFigures;
    }

    /**
     * getter
     * @return destroyed figures of second player
     */
    public List<Figure> getDestroyedSecondFigures() {
        return destroyedSecondFigures;
    }

    /**
     * get the number of rounds
     * @return rounds
     */
    public int getNumberOfRounds() {
        return numberOfRounds;
    }
}
