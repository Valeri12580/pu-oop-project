package game;

import game.field.Field;
import game.figures.Figure;
import game.player.Player;
import game.util.ChooseFigureDialog;
import game.util.FigureInitializer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Game extends JFrame implements MouseListener {
    private Field[][] fields;

    private Figure chosenFigure;

    private boolean isInitializationPhase;
    private boolean isPlacedFigure;


    private Player playerOne;
    private Player playerTwo;


    private Player currentPlayer;


    public Game() throws HeadlessException {
        super("Knights-Elfs-Dwarfs -- By Valeri");
        super.addMouseListener(this);
    }

    public void start() {

        this.fields = new Field[7][9];
        initPlayers();
        initFields();
        initWindow();
        initPlayerFigures();
        System.out.println("Start the game");

    }

    private void initPlayerFigures() {
        isInitializationPhase = true;
        FigureInitializer playerOneInitializer = new FigureInitializer(Color.BLUE);
        FigureInitializer playerTwoInitializer = new FigureInitializer(Color.GREEN);

        FigureInitializer[] figures = {playerOneInitializer, playerTwoInitializer};

        Player[] players = {playerOne, playerTwo};

        int current = 0;


        for (int i = 0; i < 12; i++) {
            currentPlayer = players[current];


            if (figures[current].getFigures().size() == 0) {
                continue;
            }
            ChooseFigureDialog dialog = new ChooseFigureDialog(this, true, currentPlayer.getId(), figures[current]);

            chosenFigure = dialog.getChosenFigure();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            current = current == 0 ? 1 : 0;
        }
        isInitializationPhase = false;

    }


    private void initFields() {
        generateSpecificPlayerField(0, 1);
        generateBattleField();
        generateSpecificPlayerField(5, 6);
    }

    private void generateBattleField() {
        for (int row = 2; row <= 4; row++) {
            for (int col = 0; col < 9; col++) {
                Field field = new Field(col, row, Color.RED);
                fields[row][col] = field;
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
        this.playerOne = new Player(1,5,6);
        this.playerTwo = new Player(2,0,1);
        this.currentPlayer = playerOne;

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
        int row = e.getY() / Field.FIELD_SIZE;
        int col = e.getX() / Field.FIELD_SIZE;

       if(isInitializationPhase){
           renderInvalidPositions();
           setFigureAtPlayerBattleground(row, col);

       }


        repaint();
    }

    private void renderInvalidPositions() {

    }

    private void setFigureAtPlayerBattleground(int row, int col) {
        if(currentPlayer.isInPlayerBattlefield(row) && fields[row][col].isFieldFree()){

            chosenFigure.setOwner(currentPlayer);
            fields[row][col].setCurrentFigure(chosenFigure);

        }else{
            JOptionPane.showMessageDialog(this,"Invalid position!");
        }
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
