import org.junit.Assert;
import org.junit.jupiter.api.Test;

class GoatTest {

    @Test
    void init() {
        int energyExpected = 15;
        Goat g = new Goat(energyExpected);
        Assert.assertEquals(energyExpected, g.energy());
    }

    @Test
    void energyEnough() {
        boolean exp3 = true;
        boolean exp1 = true;
        boolean exp0 = false;

        Goat g3 = new Goat(3);
        Goat g1 = new Goat(1);
        Goat g0 = new Goat(0);

        Assert.assertEquals(exp3, g3.energyEnough());
        Assert.assertEquals(exp1, g1.energyEnough());
        Assert.assertEquals(exp0, g0.energyEnough());
    }

    @Test
    void goingAndLoweringOfEnergy() {
        Field f = new Field(3, 3);
        Goat g = new Goat(3);
        f.getCell(0, 0).setGameObject(g);
        g.go(Direction.east());

        Assert.assertEquals(g, f.getCell(1, 0).getGameObject()); //проверка позиции
        Assert.assertEquals(2, g.energy()); //проверка энергии
    }
}