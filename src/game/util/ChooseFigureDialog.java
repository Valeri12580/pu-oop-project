package game.util;

import game.figures.Figure;

import javax.swing.*;
import java.awt.*;

public class ChooseFigureDialog extends JDialog {

    private FigureInitializer availableFigures;
    private String chosenFigure = "";

    public ChooseFigureDialog(Frame owner, boolean modal, String title, FigureInitializer availableFigures) {
        super(owner, title, modal);
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
