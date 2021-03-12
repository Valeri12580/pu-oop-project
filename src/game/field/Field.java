package game.field;

import game.figures.Figure;

import java.awt.*;

public class Field {
    public static final int FIELD_SIZE = 100;

    private int x;
    private int y;

    private boolean isObstacle = false;

    private Color color;
    private Figure currentFigure;

    public Field(int x, int y, Color color) {
        setX(x);
        setY(y);
        this.color = color;
        this.currentFigure = null;
    }

    public Field(Figure currentFigure) {
        this.currentFigure = currentFigure;
    }

    /**
     * render method
     * @param g
     */
    public void render(Graphics g) {
        if (currentFigure == null) {
            g.setColor(color);
            if (isObstacle) {
                g.setColor(Color.black);
            }

            g.fillRect(x, y, Field.FIELD_SIZE, Field.FIELD_SIZE);
        } else {
            g.setColor(Color.white);
            currentFigure.render(g, x, y);
        }

        g.setColor(Color.BLACK);
        g.drawRect(x, y, Field.FIELD_SIZE, Field.FIELD_SIZE);
    }

    public boolean isObstacle() {
        return isObstacle;
    }

    public boolean isFieldFree() {
        return this.currentFigure == null;
    }

    public Figure getCurrentFigure() {
        return currentFigure;
    }

    public void setObstacle(boolean obstacle) {
        isObstacle = obstacle;
    }

    public void setCurrentFigure(Figure currentFigure) {
        this.currentFigure = currentFigure;
    }

    public void setX(int x) {
        this.x = x * FIELD_SIZE;
    }

    public void setY(int y) {
        this.y = y * FIELD_SIZE;
    }

    public int getX() {
        return x / Field.FIELD_SIZE;
    }

    public int getY() {
        return y / Field.FIELD_SIZE;
    }
}
