package game.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EndGameDialog extends JDialog {
    public EndGameDialog(Frame owner, ActionListener actionListener) {
        super(owner, "The game is over", true);
        super.setLayout(new FlowLayout());

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
}
