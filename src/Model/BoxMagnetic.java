package Model;

import java.util.Map;

// Магнитный ящик
public class BoxMagnetic extends BoxMetal {

    private final Map<Direction, Pole> _poles; //полюса

    public BoxMagnetic(Map<Direction, Pole> poles) {
        _poles = poles;
    }

    //получить полюс
    public Pole getPole(Direction d) {
        return _poles.get(d);
    }

    //произвести магнитную реакцию с соседом
    @Override
    protected void reactWithNeighbor(Direction d) {

        Cell neighborCell = getCell().getNeighbor(d); //соседняя ячейка
        if (neighborCell != null && !neighborCell.isEmpty()) { //если на ней имеется объект

            GameObject go = neighborCell.getGameObject();

            //попался магнитный ящик
            if (go instanceof BoxMagnetic) {

                BoxMagnetic bm = (BoxMagnetic)go;

                //получить контактирующие полюса
                Pole p1 = getPole(d);
                Pole p2 = bm.getPole(d.opposite());

                if (p1 == p2) { //при одинаковых полюсах ящики отталкиваются друг от друга
                    bm.move(d); //попытаться подвинуть попавшийся ящик
                    if (!emptyAbove(d)) //если тот не подвинулся, то попытаться подвинуться самому
                        move(d.opposite());
                }
                else { //при разных полюсах ящики скрепляются друг с другом
                    linkBox(bm);
                }
            }
            else if (go instanceof BoxMetal) { //попался металлический ящик
                //любой металлический ящик прицепится к данному магнитному
                BoxMetal bm = (BoxMetal)go;
                linkBox(bm);
            }
        }
    }
}
