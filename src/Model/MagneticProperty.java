package Model;

import java.util.Map;

// Свойство магнита
public class MagneticProperty extends MetalProperty {

    private final Map<Direction, Pole> _poles; //полюса

    public MagneticProperty(InterlockedObject io, Map<Direction, Pole> poles) {
        super(io);
        _poles = poles;
    }

    //получить полюс
    public Pole getPole(Direction d) {
        return _poles.get(d);
    }

    //произвести магнитную реакцию с соседом
    @Override
    protected void reactWithNeighbor(Direction d) {

        Cell neighborCell = _interlockedObject.getCell().getNeighbor(d); //соседняя ячейка
        if (neighborCell != null && !neighborCell.isEmpty()) { //если на ней имеется объект

            GameObject go = neighborCell.getGameObject();

            //попался сцепляемый объект
            if (go instanceof InterlockedObject) {
                InterlockedObject io = (InterlockedObject)go; //привести игровой объект к сцепляемому типу
                MetalProperty mp = io.getMetalProperty(); //получить возможное свойство металла

                if (mp instanceof MagneticProperty) { //если ящик обладает свойством магнита
                    //получить контактирующие полюса
                    Pole p1 = getPole(d);
                    Pole p2 = ((MagneticProperty)mp).getPole(d.opposite());

                    if (p1 == p2) { //при одинаковых полюсах ящики отталкиваются друг от друга
                        io.move(d); //попытаться подвинуть попавшийся ящик
                        if (!io.emptyAbove(d)) //если тот не подвинулся, то попытаться подвинуться самому
                            _interlockedObject.move(d.opposite());
                    }
                    else { //при разных полюсах ящики скрепляются друг с другом
                        _interlockedObject.link(io);
                    }
                }
                else if (mp instanceof MetalProperty) //если ящик обладает свойством металла (!= null)
                    _interlockedObject.link(io); //любой металлический ящик прицепится к данному магнитному
            }
        }
    }
}
