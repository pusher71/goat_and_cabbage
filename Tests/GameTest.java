import org.junit.Assert;
import org.junit.jupiter.api.Test;
import Model.*;

class GameTest {

    @Test
    void init() {

        Game game = new Game();
        Game.MazeCreator creator = game.getMazeCreator();

        //расставить игровые объекты на поле
        creator.addToField(new Wall(), 1, 1);
        creator.addToField(new Box(), 2, 1);
        creator.addToField(new Goat(50), 3, 1);
        creator.addToField(new Cabbage(), 4, 1);

        //проверка стены
        GameObject expectedWall = game.getFromPosition(1, 1);
        Assert.assertEquals(Wall.class, expectedWall.getClass());

        //проверка ящика
        GameObject expectedBox = game.getFromPosition(2, 1);
        Assert.assertEquals(Box.class, expectedBox.getClass());

        //проверка козы
        GameObject expectedGoat = game.getFromPosition(3, 1);
        Assert.assertEquals(Goat.class, expectedGoat.getClass());
        Assert.assertEquals(50, ((Goat)expectedGoat).energy());

        //проверка капусты
        GameObject expectedCabbage = game.getFromPosition(4, 1);
        Assert.assertEquals(Cabbage.class, expectedCabbage.getClass());
    }

    @Test
    void withoutGoat() {

        Game game = new Game();
        Game.MazeCreator creator = game.getMazeCreator();

        //расставить игровые объекты на поле
        creator.addToField(new Wall(), 1, 1);
        creator.addToField(new Box(), 2, 1);
        creator.addToField(new Cabbage(), 4, 1);

        try {
            game.start();
            Assert.fail();
        }
        catch (RuntimeException e) {
            Assert.assertEquals("Goat doesn't exist", e.getMessage());
        }
    }

    @Test
    void checkEnding_DisabledProcessLose() {

        Game game = new Game();
        Game.MazeCreator creator = game.getMazeCreator();

        //расставить игровые объекты на поле
        creator.addToField(new Wall(), 1, 1);
        creator.addToField(new Box(), 2, 1);
        creator.addToField(new Goat(50), 3, 1);
        creator.addToField(new Cabbage(), 4, 1);

        //игра не запущена
        Assert.assertEquals(Game.Status.DISABLED, game.getStatus());

        game.start();
        //разрядить козу с 50 до 2
        for (int i = 0; i < 24; i++) {
            game.moveGoat(Direction.north(), false);
            game.moveGoat(Direction.south(), false);
        }

        //игра в процессе
        Assert.assertEquals(Game.Status.PROCESS, game.getStatus());

        //игра проиграна
        game.moveGoat(Direction.north(), false);
        game.moveGoat(Direction.south(), false);
        Assert.assertEquals(Game.Status.LOSE, game.getStatus());
    }

    @Test
    void checkEnding_Win() {

        Game game = new Game();
        Game.MazeCreator creator = game.getMazeCreator();

        //расставить игровые объекты на поле
        creator.addToField(new Wall(), 1, 1);
        creator.addToField(new Box(), 2, 1);
        creator.addToField(new Goat(50), 3, 1);
        creator.addToField(new Cabbage(), 4, 1);

        game.start();

        //игра выиграна
        game.moveGoat(Direction.east(), false);
        Assert.assertEquals(Game.Status.WIN, game.getStatus());
    }
}