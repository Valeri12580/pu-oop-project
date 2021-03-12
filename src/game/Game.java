package game;

import game.dialogs.ChooseActionDialog;
import game.dialogs.ChooseFigureDialog;
import game.dialogs.EndGameDialog;
import game.field.Field;
import game.figures.Figure;
import game.figures.FigureInitializer;
import game.player.Player;
import game.stats.Stats;
import game.util.ActionEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Game extends JFrame implements MouseListener {
    private Field[][] fields;
    private Stats stats;


    private boolean isInitializationPhase;

    private Player playerOne;
    private Player playerTwo;
    private ActionEnum action;

    private Field currentField;
    private Field desiredField;


    private Player currentPlayer;


    public Game() throws HeadlessException {
        super("Knights-Elfs-Dwarfs -- By Valeri");
        super.addMouseListener(this);

    }

    /**
     * start the game
     */
    public void start() {

        this.stats = new Stats();
        this.fields = new Field[7][9];
        initPlayers();
        initFields();
        generateObstacles();
        initWindow();
        repaint();

        initPlayerFigures();
        startPlayersTurns();


    }

    /**
     * showing end game results
     */
    private void showEndGameResults() {
        EndGameDialog endGameDialog = new EndGameDialog(this,stats,playerOne,playerTwo, (e) -> {
            dispose();

            this.start();
        });


    }


    /**
     * initialization of the player figures
     */
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

            currentField = new Field(dialog.getChosenFigure());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            current = current == 0 ? 1 : 0;

        }
        currentField = null;
        isInitializationPhase = false;
        currentPlayer = playerOne;

    }

    /**
     * start player turns
     */
    private void startPlayersTurns() {
        while (!(playerOne.areFiguresEmpty() || playerTwo.areFiguresEmpty())) {

            ChooseActionDialog chooseActionDialog = new ChooseActionDialog(this, String.format("Player %d -- Choose Action", currentPlayer.getId()), true);
            action = chooseActionDialog.getChosenAction();

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if (currentField == null) {
                continue;
            }

        }

        showEndGameResults();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int row = e.getY() / Field.FIELD_SIZE;
        int col = e.getX() / Field.FIELD_SIZE;

        if (isInitializationPhase) {
            setFigureAtPlayerBattleground(row, col);

        } else if (currentField == null) {
            currentField = fields[row][col];

        } else {

            if (action.equals(ActionEnum.HEAL)) {

                if (currentField.getCurrentFigure().getOwner().equals(currentPlayer)) {
                    Random random = new Random();
                    healUnit(random);

                    JOptionPane.showMessageDialog(this, String.format("Healed! Health: %d ", currentField.getCurrentFigure().getHealth()));
                    this.stats.increaseNumberOfRounds();

                    if (random.nextInt(2) == 0) {
                        JOptionPane.showMessageDialog(this, "You are lucky!Your opponent lost turn!");

                    }else{
                        currentPlayer = currentPlayer.equals(playerOne) ? playerTwo : playerOne;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "You cant heal other unit!");

                }

                clearChosenFields();
            } else {

                desiredField = fields[row][col];

                Figure currentFigure = currentField.getCurrentFigure();

                int desiredRow = desiredField.getY();
                int desiredCol = desiredField.getX();
                int currentRow = currentField.getY();
                int currentCol = currentField.getX();

                if (ActionEnum.MOVE.equals(action) && currentFigure.getOwner().equals(currentPlayer)) {

                    if (currentFigure.isValidMove(currentRow, currentCol, desiredRow, desiredCol)) {

                        if (desiredField.isObstacle()) {

                            JOptionPane.showMessageDialog(this, "Obstacle in your desired destination,destroy it!");
                            //iznesi tezi otgore


                        } else if (!desiredField.isFieldFree()) {
                            JOptionPane.showMessageDialog(this, "Opponent unit in your desired destination,kill it!");

                        } else {
                            this.stats.increaseNumberOfRounds();
                            desiredField.setCurrentFigure(currentFigure);
                            currentField.setCurrentFigure(null);
                            currentPlayer = currentPlayer.equals(playerOne) ? playerTwo : playerOne;

                        }

                    } else {

                        JOptionPane.showMessageDialog(this, "Invalid move!");



                    }
                    currentField = null;
                    desiredField = null;

                } else if
                (ActionEnum.ATTACK.equals(action)) {

                    if (!desiredField.getCurrentFigure().getOwner().equals(currentPlayer)) {
                        Figure attackedFigure = desiredField.getCurrentFigure();


                        int points=currentFigure.attack(attackedFigure);
                        currentPlayer.increaseScore(points);
                        JOptionPane.showMessageDialog(this, String.format("Left health %d", attackedFigure.getHealth()));
                        this.stats.increaseNumberOfRounds();

                        if (attackedFigure.getHealth() <= 0) {
                            desiredField.setCurrentFigure(null);
                            stats.addDestroyedFigure(attackedFigure, currentPlayer.getId());

                            removeFigureFromPlayerCollection(attackedFigure,currentPlayer.equals(playerOne) ? playerTwo : playerOne);

                        }

                        currentPlayer = currentPlayer.equals(playerOne) ? playerTwo : playerOne;

                    } else {
                        JOptionPane.showMessageDialog(this, "You cant choose your units");


                    }
                    clearChosenFields();
                }

            }

        }


        repaint();
    }


    private void removeFigureFromPlayerCollection(Figure figure,Player player){
        player.removeFigure(figure);
    }

    private void clearChosenFields() {
        currentField = null;
        desiredField = null;
    }


    /**
     * heal the unit
     *
     * @param random random number that indicate the health point to be recovered
     */
    private void healUnit(Random random) {

        int healPoints = random.nextInt(6) + 1;
        currentField.getCurrentFigure().healFigure(healPoints);
    }

    /**
     * generator of obstacles
     */
    private void generateObstacles() {
        Random random = new Random();
        int countOfObstacles = random.nextInt(4) + 1;

        int randomRow;
        int randomCol;

        for (int i = 0; i < countOfObstacles; i++) {
            randomRow = random.nextInt(3) + 2;
            randomCol = random.nextInt(9);

            fields[randomRow][randomCol].setObstacle(true);
        }
    }


    /**
     * initialization of fields
     */
    private void initFields() {
        generateSpecificPlayerField(0, 1);
        generateBattleField();
        generateSpecificPlayerField(5, 6);
    }

    /**
     * generate the battlefield
     */
    private void generateBattleField() {
        for (int row = 2; row <= 4; row++) {
            for (int col = 0; col < 9; col++) {
                Field field = new Field(col, row, Color.RED);
                fields[row][col] = field;
            }
        }
    }

    /**
     * generate each field for the player
     *
     * @param fromRow from start position
     * @param toRow   end position
     */
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


    /**
     * initialization of the players
     */
    private void initPlayers() {
        this.playerOne = new Player(1, 5, 6);
        this.playerTwo = new Player(2, 0, 1);
        this.currentPlayer = playerOne;

    }


    /**
     * windows initialization
     */
    private void initWindow() {
        super.setSize(900, 700);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * render method
     *
     * @param g instance of graphics
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);


        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 9; col++) {
                fields[row][col].render(g);
            }
        }

    }




    /**
     * set figures at player battefield
     *
     * @param row
     * @param col
     */
    private void setFigureAtPlayerBattleground(int row, int col) {
        if (currentPlayer.isInPlayerBattlefield(row) && fields[row][col].isFieldFree()) {

            currentField.getCurrentFigure().setOwner(currentPlayer);
            currentPlayer.addFigure( currentField.getCurrentFigure());
            fields[row][col].setCurrentFigure(currentField.getCurrentFigure());

        } else {
            JOptionPane.showMessageDialog(this, "Invalid position!");
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
