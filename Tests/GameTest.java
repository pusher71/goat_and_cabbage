import org.junit.Assert;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    void init() {

        Game game = new Game();

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
    void checkEnding() {

        Game game = new Game();

        //разрядить козу с 50 до 2
        for (int i = 0; i < 24; i++) {
            game.moveGoat(Direction.north());
            game.moveGoat(Direction.south());
        }

        //игра в процессе
        Assert.assertEquals(Game.Status.PROCESS, game.getStatus());

        //игра проиграна
        game.moveGoat(Direction.north());
        game.moveGoat(Direction.south());
        Assert.assertEquals(Game.Status.LOSE, game.getStatus());

        //игра выиграна
        game = new Game();
        game.moveGoat(Direction.east());
        Assert.assertEquals(Game.Status.WIN, game.getStatus());
    }
}