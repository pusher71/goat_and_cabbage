package UI;

import java.awt.*;
import Model.*;

// Виджет козы
public class GoatWidget extends GameObjectWidget {

    public GoatWidget(Goat g) {
        super(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (gameObjectInCell())
            g.drawImage(getImage(), 0, 0, null);
    }

    @Override
    public String getTextureName() {
        return "texture_goat.png";
    }
}
