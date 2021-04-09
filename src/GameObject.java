// Игровой объект
public abstract class GameObject {

    private Cell _cell = null; //ячейка

    public GameObject() {

    }

    //получить ячейку
    public Cell getCell() {
        return _cell;
    }

    //задать ячейку
    public void setCell(Cell c) {
        if (!inCell() || !_cell.equals(c)) {
            removeFromCell();
            _cell = c;
            _cell.setGameObject(this);
        }
    }

    //удалить объект из ячейки
    public void removeFromCell() {
        if (_cell != null) {
            Cell temp = _cell;
            _cell = null;
            temp.removeGameObject();
        }
    }

    //объект находится в ячейке
    public boolean inCell() {
        return _cell != null;
    }
}
