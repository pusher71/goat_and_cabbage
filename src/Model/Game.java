package Model;

// Игра
public class Game {

    // Инструмент создания лабиринта
    public class MazeCreator {

        private Game _game; //связанная игра
        private Field _field; //поле для расставления объектов

        //поле задано
        private boolean fieldExists() {
            return _field != null;
        }

        //поместить игровой объект на поле
        private void addToField(GameObject go, int x, int y) {
            if (fieldExists())
                _field.getCell(x, y).setGameObject(go);
            else
                throw new RuntimeException("Field is not specified!");
        }

        //добавить стену на поле
        public Wall addWall(int x, int y) {
            Wall go = new Wall();
            addToField(go, x, y);
            return go;
        }

        //добавить ящик на поле
        public Box addBox(int x, int y) {
            Box go = new Box();
            addToField(go, x, y);
            return go;
        }

        //добавить козу на поле
        public Goat addGoat(int x, int y, int energy) {
            Goat go = new Goat(energy);
            addToField(go, x, y);
            _game._goat = go;
            return go;
        }

        //добавить капусту на поле
        public Cabbage addCabbage(int x, int y) {
            Cabbage go = new Cabbage();
            addToField(go, x, y);
            return go;
        }
    }

    // Статус игры
    public enum Status {
        DISABLED, //игра не запущена
        PROCESS, //игра в процессе
        WIN, //игра выиграна
        LOSE //игра проиграна
    }

    private final MazeCreator _mazeCreator; //инструмент размещения объектов
    private final Field _field; //игровое поле
    private Goat _goat; //текущая коза
    private Status _status; //статус игры

    public Game() {
        _field = new Field(20, 13); //создать поля
        _mazeCreator = new MazeCreator(); //конструктор уровня
        _mazeCreator._game = this;
        _mazeCreator._field = _field; //привязка поля к конструктору
        _status = Status.DISABLED; //игра пока не запущена
    }

    //запустить игру
    public void start() {
        if (_goat != null)
            _status = Status.PROCESS;
        else
            throw new RuntimeException("Goat doesn't exist");
    }

    //определить исход игры
    private void defineExodus() {
        if (_goat.gorged()) _status = Game.Status.WIN;
        else if (!_goat.energyEnough()) _status = Game.Status.LOSE;
    }

    //получить состояние игры
    public Game.Status getStatus() {
        return _status;
    }

    //получить создатель лабиринта
    public MazeCreator getMazeCreator() {
        return _mazeCreator;
    }

    //двигать козу
    public void moveGoat(Direction d) {
        //если игра в процессе
        if (_status == Status.PROCESS) {
            _goat.go(d); //коза совершает ход
            defineExodus(); //определяется возможный исход
        }
    }

    //получить энергию козы
    public int getGoatEnergy() {
        return _goat.energy();
    }

    //получить объект с позиции
    public GameObject getFromPosition(int x, int y) {
        return _field.getCell(x, y).getGameObject();
    }
}
