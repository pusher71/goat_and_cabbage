package Model;

import java.util.List;
import java.util.ArrayList;

// Сцепляемый ящик
public class BoxInterlocked extends Box {

    protected List<BoxInterlocked> _linkedBoxes; //сцепленные ящики

    public BoxInterlocked() {
        _linkedBoxes = new ArrayList<>();
        _linkedBoxes.add(this); //в группе сцепления только текущий ящик
    }

    //сдвинуть
    @Override
    public boolean move(Direction d) {

        //все ли ящики могут сдвинуться
        boolean allCanMove = true;
        for (BoxInterlocked other : _linkedBoxes)
            if (!other.emptyAbove(d))
                allCanMove = false;

        if (allCanMove) {
            //запомнить ячейки под сцепленными ящиками и убрать эти ящики с ячеек
            List<Cell> cells = new ArrayList<>(); //ячейки под сцепленными ящиками
            for (BoxInterlocked other : _linkedBoxes) {
                cells.add(other.getCell()); //запомнить ячейку
                other.removeFromCell(); //убрать ящик из неё
            }

            //поставить ящики на соседние ячейки
            for (int i = 0; i < _linkedBoxes.size(); i++)
                _linkedBoxes.get(i).setCell(cells.get(i).getNeighbor(d));
        }

        return allCanMove;
    }

    //рядом пустая ячейка
    @Override
    protected boolean emptyAbove(Direction d) {
        Cell neighbor = getCell().getNeighbor(d); //соседняя ячейка

        if (neighbor == null) return false; //край поля
        else if (neighbor.isEmpty()) return true; //пустая ячейка
        else { //проверка скрепления стоящего рядом ящика с текущим
            GameObject go = neighbor.getGameObject();
            return go instanceof BoxInterlocked && _linkedBoxes.contains(go);
        }
    }

    //сцепить ящик с текущим
    public void linkBox(BoxInterlocked box) {

        //если такого ящика нет в сцеплении
        if (!_linkedBoxes.contains(box)) {
            //сцепить обе группы ящиков между собой
            for (BoxInterlocked other : box._linkedBoxes) {
                _linkedBoxes.add(other);
                other._linkedBoxes = _linkedBoxes;
            }
        }
    }

    //отцепить ящик
    public void unlinkBox() {
        _linkedBoxes.remove(this);
        _linkedBoxes = new ArrayList<>();
        _linkedBoxes.add(this);
    }
}
