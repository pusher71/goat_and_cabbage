package Model;

// Сдвигаемый объект
public abstract class MovableObject extends GameObject {

    //сдвинуть
    public boolean move(Direction d) {
        if (emptyAbove(d)) {
            Cell neighbor = getCell().getNeighbor(d); //соседняя ячейка
            removeFromCell(); //убраться из текущей ячейки
            setCell(neighbor); //поставиться на соседнюю ячейку
            return true;
        }
        else return false;
    }

    //рядом пустая ячейка
    protected boolean emptyAbove(Direction d) {
        Cell neighbor = getCell().getNeighbor(d); //соседняя ячейка
        return neighbor != null && neighbor.isEmpty(); //имеется и является пустой
    }
}
