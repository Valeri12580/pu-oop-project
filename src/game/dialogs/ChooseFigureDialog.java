package game.dialogs;

import game.figures.Figure;
import game.figures.FigureInitializer;

import javax.swing.*;
import java.awt.*;

public class ChooseFigureDialog extends JDialog {

    private FigureInitializer availableFigures;
    private String chosenFigure = "";

    public ChooseFigureDialog(Frame owner, boolean modal, int playerId, FigureInitializer availableFigures) {
        super(owner, String.format("Player %d selection",playerId), modal);
        this.availableFigures = availableFigures;

        super.setLayout(new FlowLayout());

        setButtons();

        super.setSize(500, 500);
        super.setVisible(true);

    }

    private void setButtons() {
        availableFigures.getFigures().forEach(e -> {
            JButton jButton = new JButton(e.getTypeName());
            jButton.addActionListener(actionEvent -> {
                chosenFigure = jButton.getText();
                super.dispose();

            });
            this.add(jButton);
        });

    }

    public Figure getChosenFigure() {

        return availableFigures.getSpecificFigure(chosenFigure);
    }
}
