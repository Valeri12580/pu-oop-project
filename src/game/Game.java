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

    private Figure chosenFigure;

    private boolean isInitializationPhase;

    private boolean isPlacedFigure;

    private Player playerOne;
    private Player playerTwo;
    private ActionEnum action;
    private Field clickedField;


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
        EndGameDialog endGameDialog = new EndGameDialog(this, (e) -> {
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

            chosenFigure = dialog.getChosenFigure();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //разчитаме на click!
            current = current == 0 ? 1 : 0;

        }
        isInitializationPhase = false;
        currentPlayer = playerOne;

    }

    /**
     * start player turns
     */
    private void startPlayersTurns() {
        while (playerOne.areFiguresEmpty() || playerTwo.areFiguresEmpty()) {
            ChooseActionDialog chooseActionDialog = new ChooseActionDialog(this, String.format("Player %d -- Choose Action", currentPlayer.getId()), true);
             action = chooseActionDialog.getChosenAction();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (chosenFigure == null) {
                continue;
            }

            if (action.equals(ActionEnum.ATTACK) && !chosenFigure.getOwner().equals(currentPlayer)) {
                System.out.println("Attack");

            } else if (chosenFigure.getOwner().equals(currentPlayer)) {

                if (action.equals(ActionEnum.HEAL)) {
                    System.out.println("Heal");
                    Random random = new Random();
                    healUnit(random);

                    if (random.nextInt(2) == 0) {
                        chosenFigure = null;
                        clickedField=null;
                        continue;
                    }

                } else if (action.equals(ActionEnum.MOVE)) {
                    moveUnit();


                }
            } else {
                chosenFigure = null;
                clickedField=null;
                System.out.println("invalid!!");
                continue;
            }

            currentPlayer = currentPlayer.equals(playerOne) ? playerTwo : playerOne;
            chosenFigure = null;
            clickedField=null;

            this.stats.increaseNumberOfRounds();
        }

        showEndGameResults();



    }



    /**
     * method that move unit
     *
     */
    private void moveUnit() {


    }

    /**
     * heal the unit
     * @param random random number that indicate the health point to be recovered
     */
    private void healUnit(Random random) {

        int healPoints = random.nextInt(6) + 1;
        chosenFigure.healFigure(healPoints);
        System.out.println("heal unit!!");
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
     * @param fromRow from start position
     * @param toRow end position
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
     * handler
     * @param e event
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        int row = e.getY() / Field.FIELD_SIZE;
        int col = e.getX() / Field.FIELD_SIZE;

        if (isInitializationPhase) {
            setFigureAtPlayerBattleground(row, col);

        } else if (chosenFigure == null) {
            chosenFigure = fields[row][col].getCurrentFigure();
            clickedField=fields[row][col];
        }else{
            if(ActionEnum.MOVE.equals(action)){
                System.out.println("desired move");
                System.out.println(clickedField.getY()+" "+clickedField.getX());
            }
        }


        repaint();
    }


    /**
     * set figures at player battefield
     * @param row
     * @param col
     */
    private void setFigureAtPlayerBattleground(int row, int col) {
        if (currentPlayer.isInPlayerBattlefield(row) && fields[row][col].isFieldFree()) {

            chosenFigure.setOwner(currentPlayer);
            fields[row][col].setCurrentFigure(chosenFigure);

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
