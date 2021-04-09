// Игра
public class Game {

    // Статус игры
    public enum Status {
        PROCESS, //игра в процессе
        WIN, //игра выиграна
        LOSE //игра проиграна
    }

    private Field _field; //игровое поле
    private Goat _goat; //текущая коза

    //начать игру
    public Game() {

        _field = new Field(20, 13); //создать поля
        Maze maze = new Maze(); //конструктор уровня
        maze.setField(_field); //привязка поля к конструктору

        //расставить игровые объекты на поле
        maze.addWall(1, 1);
        maze.addBox(2, 1);
        _goat = maze.addGoat(3, 1, 50); //получить поставленную козу
        maze.addCabbage(4, 1);
    }

    //определить состояние игре (можно возвращать enum[PROCESS, WIN, LOSE]?)
    public Game.Status getStatus() {
        if (_goat.gorged()) return Game.Status.WIN;
        else if (!_goat.energyEnough()) return Game.Status.LOSE;
        else return Game.Status.PROCESS;
    }

    //двигать козу
    public void moveGoat(Direction d) {
        _goat.go(d); //коза совершает ход
    }

    //получить объект с позиции
    public GameObject getFromPosition(int x, int y) {
        return _field.getCell(x, y).getGameObject();
    }
}
