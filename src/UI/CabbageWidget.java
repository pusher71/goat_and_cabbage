package UI;

import Model.Cabbage;

// Виджет капусты
public class CabbageWidget extends GameObjectWidget {

    public CabbageWidget(Cabbage c) {
        super(c);
    }

    @Override
    public String getTextureName() {
        return "texture_cabbage.png";
    }
}
