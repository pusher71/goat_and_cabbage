import java.util.*;

// Ячейка
public class Cell {

    private GameObject _gameObject; //игровой объект
    private final Map<Direction, Cell> neighbors = new HashMap<>(); //соседние ячейки (0 - сверху, 1 - справа, 2 - снизу, 3 - слева)

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

    //удалить игровой объект из ячейки
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
        if (!neighbors.containsValue(c))
            neighbors.put(d, c);
    }

    //получить соседнюю ячейку
    public Cell getNeighbor(Direction d) {
        return neighbors.get(d);
    }
}
