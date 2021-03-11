package game.dialogs;

import game.util.ActionEnum;

import javax.swing.*;
import java.awt.*;

public class ChooseActionDialog extends JDialog {
    public static ActionEnum[] actions = {ActionEnum.ATTACK, ActionEnum.HEAL, ActionEnum.MOVE};

    private ActionEnum chosenAction;

    public ChooseActionDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);

        super.setLayout(new FlowLayout());

        setButtons();

        super.setSize(500, 500);
        super.setVisible(true);
    }

    private void setButtons() {

        for (ActionEnum action : actions) {
            JButton jButton = new JButton(action.toString());
            jButton.addActionListener((actionEvent) -> {
                chosenAction = action;
                super.dispose();
            });
            this.add(jButton);
        }

    }

    public ActionEnum getChosenAction() {
        return chosenAction;
    }
}
