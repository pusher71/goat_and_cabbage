package Model;

import java.util.*;

// Ячейка
public class Cell {

    private Position _position; //позиция на уровне
    private GameObject _gameObject; //игровой объект
    private final Map<Direction, Cell> _neighbors = new HashMap<>(); //соседние ячейки (0 - сверху, 1 - справа, 2 - снизу, 3 - слева)

    //получить игровой объект
    public GameObject getGameObject() {
        return _gameObject;
    }

    //установить игровой объект
    public void setGameObject(GameObject go) {
        if (isEmpty() || !_gameObject.equals(go)) {
            removeGameObject();
            _gameObject = go;
            _gameObject.setCell(this);
        }
    }

    //убрать игровой объект из ячейки
    public void removeGameObject() {
        if (!isEmpty()) {
            GameObject temp = _gameObject;
            _gameObject = null;
            temp.removeFromCell();
        }
    }

    //ячейка пуста
    public boolean isEmpty() {
        return _gameObject == null;
    }

    //задать соседнюю ячейку
    public void setNeighbor(Cell c, Direction d) {
        if (!_neighbors.containsValue(c))
            _neighbors.put(d, c);
    }

    //получить соседнюю ячейку
    public Cell getNeighbor(Direction d) {
        return _neighbors.get(d);
    }

    //задать позицию
    public void setPosition(Position pos) {
        _position = pos;
    }

    //получить позицию
    public Position getPosition() {
        return _position;
    }
}
