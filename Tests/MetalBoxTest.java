//import org.junit.Test;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import Model.*;

import java.util.HashMap;

class MetalBoxTest {

    @Test
    void magneticPush() {
        Field f = new Field(3, 3);
        InterlockedBox box1 = new InterlockedBox();
        box1.setMetalProperty(new MagneticProperty(box1, new HashMap<Direction, Pole>() {{
            put(Direction.north(), Pole.PLUS);
            put(Direction.west(), Pole.PLUS);
            put(Direction.south(), Pole.PLUS);
            put(Direction.east(), Pole.MINUS);
        }}));
        InterlockedBox box2 = new InterlockedBox();
        box2.setMetalProperty(new MagneticProperty(box2, new HashMap<Direction, Pole>() {{
            put(Direction.north(), Pole.PLUS);
            put(Direction.west(), Pole.MINUS);
            put(Direction.south(), Pole.PLUS);
            put(Direction.east(), Pole.PLUS);
        }}));
        f.getCell(0, 0).setGameObject(box1);
        f.getCell(1, 1).setGameObject(box2);
        box1.move(Direction.south());

        Assert.assertEquals(box2, f.getCell(2, 1).getGameObject()); //проверка позиции
    }

    @Test
    void magneticInterlock() {
        Field f = new Field(3, 3);
        InterlockedBox box1 = new InterlockedBox();
        box1.setMetalProperty(new MagneticProperty(box1, new HashMap<Direction, Pole>() {{
            put(Direction.north(), Pole.PLUS);
            put(Direction.west(), Pole.PLUS);
            put(Direction.south(), Pole.PLUS);
            put(Direction.east(), Pole.MINUS);
        }}));
        InterlockedBox box2 = new InterlockedBox();
        box2.setMetalProperty(new MagneticProperty(box2, new HashMap<Direction, Pole>() {{
            put(Direction.north(), Pole.PLUS);
            put(Direction.west(), Pole.PLUS);
            put(Direction.south(), Pole.PLUS);
            put(Direction.east(), Pole.PLUS);
        }}));
        f.getCell(0, 0).setGameObject(box1);
        f.getCell(1, 1).setGameObject(box2);
        box1.move(Direction.south());
        box1.move(Direction.south());

        Assert.assertEquals(box2, f.getCell(1, 2).getGameObject()); //проверка позиции
    }

    @Test
    void metalInterlock() {
        Field f = new Field(3, 3);
        InterlockedBox box1 = new InterlockedBox();
        box1.setMetalProperty(new MagneticProperty(box1, new HashMap<Direction, Pole>() {{
            put(Direction.north(), Pole.PLUS);
            put(Direction.west(), Pole.PLUS);
            put(Direction.south(), Pole.PLUS);
            put(Direction.east(), Pole.MINUS);
        }}));
        InterlockedBox box2 = new InterlockedBox();
        box2.setMetalProperty(new MetalProperty(box2));
        f.getCell(0, 0).setGameObject(box1);
        f.getCell(1, 1).setGameObject(box2);
        box1.move(Direction.south());
        box1.move(Direction.south());

        Assert.assertEquals(box2, f.getCell(1, 2).getGameObject()); //проверка позиции
    }
}
