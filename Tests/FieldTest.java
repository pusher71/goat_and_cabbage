//import org.junit.Test;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class FieldTest {

    @Test
    public void createField() {
        Field f = new Field(5, 8);
        Assert.assertEquals(5, f.width());
        Assert.assertEquals(8, f.height());
    }

    @Test
    public void getCell() {
        Field f = new Field(5, 8);
        Cell c = new Cell();
        f.setCell(3, 3, c);
        Assert.assertEquals(c, f.getCell(3, 3));
        Assert.assertNotEquals(c, f.getCell(2, 3));
    }

    @Test
    public void outOfRange() {
        Field f = new Field(5, 8);

        //проверка задания ячейки за границами
        try {
            f.setCell(8, 7, new Cell());
            Assert.fail();
        }
        catch (RuntimeException ex) {
            Assert.assertEquals("Position out of range", ex.getMessage());
        }

        //проверка получения ячейки за границами
        try {
            Cell c = f.getCell(-1, 3);
            Assert.fail();
        }
        catch (RuntimeException ex) {
            Assert.assertEquals("Position out of range", ex.getMessage());
        }
    }
}