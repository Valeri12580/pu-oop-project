package game.figures.impl;

import game.figures.Figure;
import game.player.Player;

import java.awt.*;

public class Dwarf extends Figure {
    public Dwarf(Color color, Player owner) {
        super(color, owner, 6, 2, 12, 2, 2);
    }
}
