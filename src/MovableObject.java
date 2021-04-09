// Сдвигаемый объект
public abstract class MovableObject extends GameObject {

    public void move(Direction d) {

        Cell neighbor = getCell().getNeighbor(d); //соседняя ячейка
        if (neighbor.isEmpty()) {
            removeFromCell(); //убраться из текущей ячейки
            setCell(neighbor); //поставиться на соседнюю ячейку
        }
    }
}
