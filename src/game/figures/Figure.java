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

    public Figure(Color color, int attackPower, int armor, int health, int attackRange, int speed) {
        this.color = color;
        this.owner = null;
        this.attackPower = attackPower;
        this.armor = armor;
        this.health = health;
        this.attackRange = attackRange;
        this.speed = speed;

    }

    public String getTypeName(){

        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "Figure{" +
                "color=" + color +
                ", owner=" + owner +
                ", attackPower=" + attackPower +
                ", armor=" + armor +
                ", health=" + health +
                ", attackRange=" + attackRange +
                ", speed=" + speed +
                '}';
    }
}
