package UI;

import Model.Game;

import javax.swing.*;
import java.awt.*;

// Виджет статуса игры
public class StatusWidget extends JLabel {

    public StatusWidget() {
        super("Game exodus");
    }

    public void setStatus(Game.Status s) {
        switch (s) {
            case LOSE:
                setText("You lost!");
                setForeground(Color.red);
                break;
            case WIN:
                setText("You win!");
                setForeground(Color.green);
                break;
            default:
                setText("");
                break;
        }
    }
}
