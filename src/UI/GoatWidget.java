package UI;

import Model.Goat;

// Виджет козы
public class GoatWidget extends GameObjectWidget {

    public GoatWidget(Goat g) {
        super(g);
    }

    @Override
    public String getTextureName() {
        return "texture_goat.png";
    }
}
