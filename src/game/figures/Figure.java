package game.figures;

import game.player.Player;

import java.awt.*;

public abstract class Figure {

    private String type;

    private Color color;

    private Player owner;

    private int attackPower;
    private int armor;
    private int health;
    private int attackRange;
    private int speed;

    public Figure(String type, Color color, int attackPower, int armor, int health, int attackRange, int speed) {
        this.type = type;
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

    public void render(Graphics g,int x,int y){
        g.setColor(color);
        g.drawString(type,x+50,y+50);
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

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
