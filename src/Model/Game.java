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
        public GameObject addToField(GameObject go, int x, int y) {

            if (fieldExists()) { //поле задано
                //установить объект на поле
                _field.getCell(x, y).setGameObject(go);

                //захватить козу
                if (go instanceof Goat)
                    _game._goat = (Goat)go;

                return go;
            }
            else //поле не задано
                throw new RuntimeException("Field is not specified!");
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
    public void moveGoat(Direction d, boolean pull) {
        //если игра в процессе
        if (_status == Status.PROCESS) {
            _goat.go(d, pull); //коза совершает ход
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
