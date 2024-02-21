package Model;

import java.util.List;
import java.util.ArrayList;

// Сцепляемый объект
public abstract class InterlockedObject extends MovableObject {

    protected List<InterlockedObject> _linked; //сцепленные Объекты
    private MetalProperty _metalProperty; //свойство металла

    public InterlockedObject() {
        _linked = new ArrayList<>();
        _linked.add(this); //в группе сцепления только текущий объект
    }

    //сдвинуть
    @Override
    public boolean move(Direction d) {

        //все ли объекты могут сдвинуться
        boolean allCanMove = true;
        for (InterlockedObject other : _linked)
            if (!other.emptyAbove(d))
                allCanMove = false;

        if (allCanMove) {
            //запомнить ячейки под сцепленными объектами и убрать эти объекты с ячеек
            List<Cell> cells = new ArrayList<>(); //объекты под сцепленными ящиками
            for (InterlockedObject other : _linked) {
                cells.add(other.getCell()); //запомнить ячейку
                other.removeFromCell(); //убрать объект из неё
            }

            //поставить объект на соседние ячейки
            for (int i = 0; i < _linked.size(); i++)
                _linked.get(i).setCell(cells.get(i).getNeighbor(d));

            //произвести возможные магнитные реакции с соседями
            for (int i = 0; i < _linked.size(); i++) {
                InterlockedObject bi = _linked.get(i); //очередной объект в группе скреплённых
                if (bi.isMetal()) { //если объект является металлическим
                    MetalProperty mp = bi.getMetalProperty(); //свойство металла
                    for (int j = 0; j < 2; j++) { //магнитные реакции с соседями
                        mp.reactWithNeighbor(Direction.north());
                        mp.reactWithNeighbor(Direction.east());
                        mp.reactWithNeighbor(Direction.south());
                        mp.reactWithNeighbor(Direction.west());
                    }
                }
            }
        }

        return allCanMove;
    }

    //рядом пустая ячейка
    @Override
    protected boolean emptyAbove(Direction d) {
        Cell neighbor = getCell().getNeighbor(d); //соседняя ячейка

        if (neighbor == null) return false; //край поля
        else if (neighbor.isEmpty()) return true; //пустая ячейка
        else { //проверка скрепления стоящего рядом объекта с текущим
            GameObject go = neighbor.getGameObject();
            return go instanceof InterlockedObject && _linked.contains(go);
        }
    }

    //сцепить объект с текущим
    public void link(InterlockedObject box) {

        //если такого объекта нет в сцеплении
        if (!_linked.contains(box)) {
            //сцепить обе группы объектов между собой
            for (InterlockedObject other : box._linked) {
                _linked.add(other);
                other._linked = _linked;
            }
        }
    }

    //отцепить объект
    public void unlink() {
        _linked.remove(this);
        _linked = new ArrayList<>();
        _linked.add(this);
    }

    //задать свойство металла
    public void setMetalProperty(MetalProperty mp) {
        _metalProperty = mp;
    }

    //получить свойство металла
    public MetalProperty getMetalProperty() {
        return _metalProperty;
    }

    //убрать свойство металла
    public void removeMetalProperty() {
        _metalProperty = null;
    }

    //свойство металла задано
    public boolean isMetal() {
        return _metalProperty != null;
    }
}
