package game.figures;

import game.player.Player;

import java.awt.*;
import java.util.Random;

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


    public abstract boolean isValidMove(int currentRow,int currentCol,int desiredRow,int desiredCol);

    @Override
    public String toString() {
        return String.format("Figure: %s",this.getClass().getSimpleName());
    }

    /**
     * get the owner
     * @return the owner of the figure
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * heal method
     * @param healPoints how much points to be recovered
     */
    public void healFigure(int healPoints) {
        health+=healPoints;

    }

    /**
     * set owner
     * @param owner owner
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public  abstract  boolean isAttackValid(int currentFigureRow, int currentFigureCol, int attackedFigureRow, int attackedFigureCol);

    public int attack(Figure attackedFigure) {
        int damage=rowDice(attackedFigure.health,this.attackPower- attackedFigure.armor);

        attackedFigure.setHealthAfterAttack(damage);

        return damage;

    }

    private int rowDice(int health,int damage){
        int maxDiceValue=(health/3)+1;
        Random random=new Random();
        int first =random.nextInt(maxDiceValue);
        int second =random.nextInt(maxDiceValue);
        int third =random.nextInt(maxDiceValue);

        int sum=first+second+third;

        if(sum==health){
            return 0;
        }else if(sum==1){
            return damage/2;
        }

        return damage;

    }

    public int getHealth() {
        return health;
    }

    public void setHealthAfterAttack(int damage){
        this.health-=damage;
    }


}
