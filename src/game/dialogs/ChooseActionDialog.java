package game.dialogs;

import game.util.Action;

import javax.swing.*;
import java.awt.*;

public class ChooseActionDialog extends JDialog {
    public static Action[] actions = {Action.ATTACK, Action.HEAL, Action.MOVE};

    private Action chosenAction;

    public ChooseActionDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);

        super.setLayout(new FlowLayout());

        setButtons();

        super.setSize(500, 500);
        super.setVisible(true);
    }

    private void setButtons() {

        for (Action action : actions) {
            JButton jButton = new JButton(action.toString());
            jButton.addActionListener((actionEvent) -> {
                chosenAction = action;
                super.dispose();
            });
            this.add(jButton);
        }

    }

    public Action getChosenAction() {
        return chosenAction;
    }
}
