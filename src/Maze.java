// Лабиринт
public class Maze {

    private Field _field; //поле для расставления объектов

    //привязать поле
    public void setField(Field f) {
        _field = f;
    }

    //отвязать поле
    public void removeField() {
        _field = null;
    }

    //поле задано
    public boolean fieldExists() {
        return _field != null;
    }

    //добавить стены на поле
    public Wall addWall(int x, int y) {
        if (fieldExists()) {
            Wall go = new Wall();
            _field.getCell(x, y).setGameObject(go);
            return go;
        }
        else throw new RuntimeException("Field is not specified!");
    }

    //добавить ящики на поле
    public Box addBox(int x, int y) {
        if (fieldExists()) {
            Box go = new Box();
            _field.getCell(x, y).setGameObject(go);
            return go;
        }
        else throw new RuntimeException("Field is not specified!");
    }

    //добавить козу на поле
    public Goat addGoat(int x, int y, int energy) {
        if (fieldExists()) {
            Goat go = new Goat(energy);
            _field.getCell(x, y).setGameObject(go);
            return go;
        }
        else throw new RuntimeException("Field is not specified!");
    }

    //добавить капусту на поле
    public Cabbage addCabbage(int x, int y) {
        if (fieldExists()) {
            Cabbage go = new Cabbage();
            _field.getCell(x, y).setGameObject(go);
            return go;
        }
        else throw new RuntimeException("Field is not specified!");
    }
}
