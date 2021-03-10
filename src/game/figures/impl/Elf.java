package game.figures.impl;

import game.figures.Figure;
import game.player.Player;

import java.awt.*;

public class Elf extends Figure {

    public Elf(Color color, Player owner) {
        super(color, owner, 5,1,10,3,3);
    }
}
