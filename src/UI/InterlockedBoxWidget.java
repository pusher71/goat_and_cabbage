package UI;

import Model.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Виджет сцепляемого ящика
public class InterlockedBoxWidget extends GameObjectWidget {

    public InterlockedBoxWidget(InterlockedBox ib) {
        super(ib);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (gameObjectInCell()) {
            GameObject go = getGameObject();
            if (go instanceof InterlockedObject) {
                InterlockedObject io = (InterlockedObject)go;
                if (io.isMetal()) {
                    MetalProperty mp = io.getMetalProperty();
                    if (mp instanceof MagneticProperty) { //магнитный ящик
                        MagneticProperty mgp = (MagneticProperty)mp;
                        g.drawImage(getImageByPath("texture_box_magnetic.png"), 0, 0, null);
                        g.drawImage(getImageByPath("texture_pole_north_" +
                                (mgp.getPole(Direction.north()) == Pole.PLUS ? "P" : "N") + ".png"), 0, 0, null);
                        g.drawImage(getImageByPath("texture_pole_east_" +
                                (mgp.getPole(Direction.east()) == Pole.PLUS ? "P" : "N") + ".png"), 0, 0, null);
                        g.drawImage(getImageByPath("texture_pole_south_" +
                                (mgp.getPole(Direction.south()) == Pole.PLUS ? "P" : "N") + ".png"), 0, 0, null);
                        g.drawImage(getImageByPath("texture_pole_west_" +
                                (mgp.getPole(Direction.west()) == Pole.PLUS ? "P" : "N") + ".png"), 0, 0, null);
                    }
                    else //металлический ящик (isMetal() == true)
                        g.drawImage(getImageByPath("texture_box_metal.png"), 0, 0, null);
                }
                else //скрепляемый ящик
                    g.drawImage(getImageByPath("texture_box.png"), 0, 0, null);
            }
            else //ящик
                g.drawImage(getImageByPath("texture_box.png"), 0, 0, null);
        }
    }

    @Override
    public String getTextureName() {
        return "texture_box.png";
    }

    protected BufferedImage getImageByPath(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
