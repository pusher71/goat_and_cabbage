package UI;

import java.awt.*;
import Model.*;

// Виджет стены
public class WallWidget extends GameObjectWidget {

    public WallWidget(Wall w) {
        super(w);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (gameObjectInCell())
            g.drawImage(getImage(), 0, 0, null);
    }

    @Override
    public String getTextureName() {
        return "texture_wall.png";
    }
}
