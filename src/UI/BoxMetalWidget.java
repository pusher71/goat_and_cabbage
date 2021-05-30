package UI;

import Model.BoxMetal;

// Виджет ящика
public class BoxMetalWidget extends GameObjectWidget {

    public BoxMetalWidget(BoxMetal bm) {
        super(bm);
    }

    @Override
    public String getTextureName() {
        return "texture_box_metal.png";
    }
}
