package game;

import game.field.Field;
import game.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Game extends JFrame implements MouseListener {
    private Field[][] fields;
    private Player playerOne;
    private Player playerTwo;


    public Game() throws HeadlessException {
        super("Knights-Elfs-Dwarfs -- By Valeri");
        super.addMouseListener(this);
    }

    public void start() {
        this.fields = new Field[7][9];
        initPlayers();

        initFields();
        initWindow();
    }


    private void initFields() {
        generateSpecificPlayerField(0, 1);
        generateBattleField();
        generateSpecificPlayerField(5, 6);


    }

    private void generateBattleField() {
        for (int row = 2; row <=4 ; row++) {
            for (int col = 0; col < 9; col++) {
                Field field=new Field(col,row,Color.RED);
                fields[row][col]=field;
            }
        }
    }

    private void generateSpecificPlayerField(int fromRow, int toRow) {
        Color currentColor = Color.GRAY;
        for (int row = fromRow; row <= toRow; row++) {
            for (int col = 0; col < 9; col++) {
                currentColor = currentColor.equals(Color.GRAY) ? Color.DARK_GRAY : Color.GRAY;

                Field field = new Field(col, row, currentColor);
                fields[row][col] = field;
            }
        }

    }

    private void initPlayers() {
        this.playerOne = new Player(1);
        this.playerTwo = new Player(1);

    }


    private void initWindow() {
        super.setSize(900, 700);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 9; col++) {
                fields[row][col].render(g);
            }
        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
