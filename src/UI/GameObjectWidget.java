package UI;

import javax.swing.*;
import Model.*;

import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Виджет игрового объекта
public abstract class GameObjectWidget extends JPanel {

    private final GameObject _gameObject; //игровой объект

    public GameObjectWidget(GameObject go) {
        _gameObject = go;

        //установить позицию виджета
        Position pos = getGameObject().getCell().getPosition();
        setBounds(pos.X * FieldWidget.CELL_SIZE, pos.Y * FieldWidget.CELL_SIZE,
                FieldWidget.CELL_SIZE, FieldWidget.CELL_SIZE);
    }

    @Override
    public void paint(Graphics g) {
        if (gameObjectInCell()) {
            Position pos = getGameObject().getCell().getPosition();
            setBounds(pos.X * FieldWidget.CELL_SIZE, pos.Y * FieldWidget.CELL_SIZE,
                    FieldWidget.CELL_SIZE, FieldWidget.CELL_SIZE);
        }
    }

    protected GameObject getGameObject() {
        return _gameObject;
    }

    //объект имеется и находится на ячейке
    protected boolean gameObjectInCell() {
        return getGameObject() != null && getGameObject().getCell() != null;
    }

    public abstract String getTextureName();

    protected BufferedImage getImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(getTextureName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
