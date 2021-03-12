package game.dialogs;

import game.player.Player;
import game.stats.Stats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collection;

public class EndGameDialog extends JDialog {


    public EndGameDialog(Frame owner, Stats stats, Player playerOne, Player playerTwo, ActionListener actionListener) {
        super(owner, "The game is over", true);
        super.setLayout(new FlowLayout());
        super.add(constructJLabelWithResults(stats,playerOne,playerTwo));
        setButtons(actionListener);

        super.setSize(500, 500);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private void setButtons(ActionListener actionListener) {

        JButton restart = new JButton("Restart");
        restart.addActionListener(actionListener);

        JButton close = new JButton("Close");
        close.addActionListener((e) -> {
            System.exit(0);
        });

        this.add(restart);
        this.add(close);
    }

    private JLabel constructJLabelWithResults(Stats stats, Player playerOne, Player playerTwo){
        String text=String.format("Game rounds: %d\nPlayer 1 : \n-score: %d \n-Dead figures: %s\nPlayer 2 :\n-score: %d\n-Dead figures: %s "
                ,stats.getNumberOfRounds(),playerOne.getScore() ,stats.getDestroyedOFirstPlayerFigures().toString(),
                playerTwo.getScore(),
                stats.getDestroyedSecondFigures().toString()
                );


        return new JLabel(text);

    }
}
