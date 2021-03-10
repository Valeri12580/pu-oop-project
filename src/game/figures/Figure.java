package game.figures;

import game.player.Player;

import java.awt.*;

public abstract class Figure {


    private Color color;

    private Player owner;

    private int attackPower;
    private int armor;
    private int health;
    private int attackRange;
    private int speed;

    public Figure(Color color,Player owner, int attackPower, int armor, int health, int attackRange, int speed) {
        this.color = color;
        this.owner = owner;
        this.attackPower = attackPower;
        this.armor = armor;
        this.health = health;
        this.attackRange = attackRange;
        this.speed = speed;

    }

}
