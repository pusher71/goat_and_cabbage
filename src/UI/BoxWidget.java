package UI;

import Model.*;

// Виджет ящика
public class BoxWidget extends GameObjectWidget {

    public BoxWidget(Box b) {
        super(b);
    }

    @Override
    public String getTextureName() {
        return "texture_box.png";
    }
}
