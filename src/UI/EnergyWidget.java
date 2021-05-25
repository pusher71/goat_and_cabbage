package UI;

import Model.Game;

import javax.swing.*;
import java.awt.*;

// Виджет статуса игры
public class EnergyWidget extends JLabel {

    public EnergyWidget() {
        super("Goat energy");
    }

    public void setEnergy(int energy) {
        setText("Goat energy: " + energy);
    }
}
