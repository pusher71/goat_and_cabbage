import java.util.ArrayList;

// Поле
public class Field {

    private final int _width; //длина
    private final int _height; //высота

    public int width() { return _width; }
    public int height() { return _height; }
    private final ArrayList<ArrayList<Cell>> _cells; //двумерный массив ячеек

    public Field(int width, int height) {
        _width = width;
        _height = height;

        //создать ячейки
        _cells = new ArrayList<>();
        for (int x = 0; x < width(); x++) {
            _cells.add(new ArrayList<>());
            for (int y = 0; y < height(); y++)
                _cells.get(x).add(new Cell());
        }

        //связать ячейки между собой
        for (int x = 0; x < width(); x++) {
            for (int y = 0; y < height(); y++) {
                if (x != 0) _cells.get(x).get(y).setNeighbor(_cells.get(x - 1).get(y), Direction.west());
                if (y != 0) _cells.get(x).get(y).setNeighbor(_cells.get(x).get(y - 1), Direction.north());
                if (x != width() - 1) _cells.get(x).get(y).setNeighbor(_cells.get(x + 1).get(y), Direction.east());
                if (y != height() - 1) _cells.get(x).get(y).setNeighbor(_cells.get(x).get(y + 1), Direction.south());
            }
        }
    }

    //получить ячейку из заданной позиции
    public Cell getCell(int x, int y) {
        if (x < 0 || x > width() - 1 || y < 0 || y > height() - 1)
            throw new RuntimeException("Position out of range");
        else
            return _cells.get(x).get(y);
    }

    //поставить ячейку на заданную позицию
    public void setCell(int x, int y, Cell cell) {
        if (x < 0 || x > width() - 1 || y < 0 || y > height() - 1)
            throw new RuntimeException("Position out of range");
        else
            _cells.get(x).set(y, cell);
    }
}
