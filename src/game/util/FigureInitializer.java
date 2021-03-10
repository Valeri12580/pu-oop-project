package game.util;

import game.figures.Figure;
import game.figures.impl.Dwarf;
import game.figures.impl.Elf;
import game.figures.impl.Knight;

import java.awt.*;
import java.util.List;
import java.util.*;

public class FigureInitializer {

    // todo change structure
    private Deque<Figure> knights;
    private Deque<Figure> elfs;
    private Deque<Figure> dwarfs;

    public FigureInitializer(Color color) {
        knights = new ArrayDeque<>(Arrays.asList(new Knight(color), new Knight(color)));
        elfs = new ArrayDeque<>(Arrays.asList(new Elf(color), new Elf(color)));
        dwarfs = new ArrayDeque<>(Arrays.asList(new Dwarf(color), new Dwarf(color)));
    }


    public List<Figure> getFigures() {
        List<Figure> all = new ArrayList<>();
        all.addAll(knights);
        all.addAll(dwarfs);
        all.addAll(elfs);
        return all;
    }


    public Figure getSpecificFigure(String type) {
        Figure figure = switch (type) {
            case "Knight" -> this.knights.pop();
            case "Elf" -> this.elfs.pop();
            case "Dwarf" -> this.dwarfs.pop();
            default -> null;
        };

        return figure;
    }
}
