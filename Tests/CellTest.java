//import org.junit.Test;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CellTest {

    @Test
    public void setGameObject() {
        Cell cell = new Cell();
        GameObject go = new Wall();
        cell.setGameObject(go);
        Assert.assertEquals(go, cell.getGameObject());
        Assert.assertEquals(cell, go.getCell());
    }

    @Test
    public void isEmpty() {
        Cell cell = new Cell();
        GameObject go = new Wall();
        cell.setGameObject(go);
        Assert.assertFalse(cell.isEmpty());
        cell.removeGameObject();
        Assert.assertTrue(cell.isEmpty());
    }

    @Test
    public void doubleCells() {
        GameObject go = new Wall();
        Cell c1 = new Cell();
        Cell c2 = new Cell();
        go.setCell(c1);
        go.setCell(c2);
        Assert.assertEquals(c2, go.getCell());
    }

    @Test
    public void doubleGameObjects() {
        Cell c = new Cell();
        GameObject go1 = new Wall();
        GameObject go2 = new Wall();
        c.setGameObject(go1);
        c.setGameObject(go2);
        Assert.assertEquals(go2, c.getGameObject());
    }

    @Test
    public void removeFromEmpty() {
        Cell c = new Cell();
        c.removeGameObject();
        Assert.assertNull(c.getGameObject());
    }

    @Test
    public void getNeighbor() {
        Cell c1 = new Cell();
        Cell c2 = new Cell();
        c1.setNeighbor(c2, Direction.east());
        c2.setNeighbor(c1, Direction.west());
        Assert.assertEquals(c2, c1.getNeighbor(Direction.east()));
        Assert.assertEquals(c1, c2.getNeighbor(Direction.west()));
        Assert.assertNull(c1.getNeighbor(Direction.north()));
        Assert.assertNull(c2.getNeighbor(Direction.south()));
    }
}