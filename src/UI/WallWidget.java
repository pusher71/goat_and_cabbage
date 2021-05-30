package UI;

import Model.Wall;

// Виджет стены
public class WallWidget extends GameObjectWidget {

    public WallWidget(Wall w) {
        super(w);
    }

    @Override
    public String getTextureName() {
        return "texture_wall.png";
    }
}
