package game.figures.impl;

import game.figures.Figure;
import game.player.Player;

import java.awt.*;

public class Knight  extends Figure {

    public Knight(Color color, Player owner) {
        super(color, owner, 8, 3, 15, 1, 1);
    }
}
