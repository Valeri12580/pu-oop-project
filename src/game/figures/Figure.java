package game.figures;

import game.player.Player;

public abstract class Figure {

    private Player owner;

    private int attackPower;
    private int armor;
    private int health;
    private int attackRange;
    private int speed;

    public Figure(Player owner, int attackPower, int armor, int health, int attackRange, int speed) {
        this.owner = owner;
        this.attackPower = attackPower;
        this.armor = armor;
        this.health = health;
        this.attackRange = attackRange;
        this.speed = speed;

    }

}
