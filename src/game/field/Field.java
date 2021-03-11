package game.field;

import game.figures.Figure;

import java.awt.*;

public class Field {
    public static final int FIELD_SIZE = 100;

    private int x;
    private int y;

    private Color color;
    private Figure currentFigure;

    public Field(int x, int y, Color color) {
        setX(x);
        setY(y);
        this.color = color;
        this.currentFigure = null;
    }

    public void render(Graphics g) {
        if (currentFigure == null) {
            g.setColor(color);
            g.fillRect(x, y, Field.FIELD_SIZE, Field.FIELD_SIZE);
        } else {
            g.setColor(Color.white);
            currentFigure.render(g, x, y);
        }

        g.setColor(Color.BLACK);
        g.drawRect(x, y, Field.FIELD_SIZE, Field.FIELD_SIZE);
    }

    public boolean isFieldFree() {
        return this.currentFigure ==null;
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
