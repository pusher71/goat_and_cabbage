package UI;

import java.awt.*;
import Model.*;

// Виджет ящика
public class BoxWidget extends GameObjectWidget {

    public BoxWidget(Box b) {
        super(b);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (gameObjectInCell())
            g.drawImage(getImage(), 0, 0, null);
    }

    @Override
    public String getTextureName() {
        return "texture_box.png";
    }
}
