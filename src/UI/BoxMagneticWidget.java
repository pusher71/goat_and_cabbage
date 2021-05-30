package UI;

import Model.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BoxMagneticWidget extends GameObjectWidget {

    public BoxMagneticWidget(BoxMagnetic bm) {
        super(bm);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (gameObjectInCell()) {

            //отрисовать полюса на сторонах
            BoxMagnetic bm = (BoxMagnetic)getGameObject();
            g.drawImage(getImageByPath("texture_pole_north_" +
                    (bm.getPole(Direction.north()) == Pole.PLUS ? "P" : "N") + ".png"), 0, 0, null);
            g.drawImage(getImageByPath("texture_pole_east_" +
                    (bm.getPole(Direction.east()) == Pole.PLUS ? "P" : "N") + ".png"), 0, 0, null);
            g.drawImage(getImageByPath("texture_pole_south_" +
                    (bm.getPole(Direction.south()) == Pole.PLUS ? "P" : "N") + ".png"), 0, 0, null);
            g.drawImage(getImageByPath("texture_pole_west_" +
                    (bm.getPole(Direction.west()) == Pole.PLUS ? "P" : "N") + ".png"), 0, 0, null);
        }
    }

    @Override
    public String getTextureName() {
        return "texture_box_magnetic.png";
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
