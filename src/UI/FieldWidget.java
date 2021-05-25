package UI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import javax.swing.*;

public class FieldWidget extends JPanel {
    /* ========================== Константы =============================== */

    static final int CELL_SIZE = 30;

    public FieldWidget() {
        setLayout(null);

        Dimension fieldDimension = new Dimension(CELL_SIZE*colCount(), CELL_SIZE*rowCount());
        setPreferredSize(fieldDimension);
        setMinimumSize(fieldDimension);
        setMaximumSize(fieldDimension);

        setFocusable(true);
    }

    private BufferedImage getImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("texture_background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /* =========================== Свойства =============================== */

    /** Размеры доски (в количестве клеток).
     */
    public static int colCount(){
        return 20;
    }
    public static int rowCount(){
        return 13;
    }

    /* -------------------------- Отрисовка --------------------------- */

    /** Отрисовывает поле
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(), 0, 0, null);
    }
}
