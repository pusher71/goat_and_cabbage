package UI;

import java.awt.*;
import Model.*;

// Виджет капусты
public class CabbageWidget extends GameObjectWidget {

    public CabbageWidget(Cabbage c) {
        super(c);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (gameObjectInCell())
            g.drawImage(getImage(), 0, 0, null);
    }

    @Override
    public String getTextureName() {
        return "texture_cabbage.png";
    }
}
