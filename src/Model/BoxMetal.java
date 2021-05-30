package Model;

// Магнитный ящик
public class BoxMetal extends BoxInterlocked {

    @Override
    public boolean move(Direction d) {
        if (super.move(d)) { //если ящик смог сдвинуться
            //произвести возможные магнитные реакции с соседями
            for (int i = 0; i < _linkedBoxes.size(); i++) {
                BoxInterlocked bi = _linkedBoxes.get(i); //очередной ящик в группе скреплённых
                if (bi instanceof BoxMetal) { //если ящик умеет магнитно реагировать
                    BoxMetal bm = (BoxMetal)bi; //приведение скрепляемого ящика к металлическому
                    for (int j = 0; j < 2; j++) { //магнитные реакции с соседями
                        bm.reactWithNeighbor(Direction.north());
                        bm.reactWithNeighbor(Direction.east());
                        bm.reactWithNeighbor(Direction.south());
                        bm.reactWithNeighbor(Direction.west());
                    }
                }
            }
            return true;
        }
        else return false;
    }

    //произвести магнитную реакцию с соседом
    protected void reactWithNeighbor(Direction d) {

        Cell neighborCell = getCell().getNeighbor(d); //соседняя ячейка
        if (neighborCell != null && !neighborCell.isEmpty()) { //если на ней имеется объект

            GameObject go = neighborCell.getGameObject();

            //попался магнитный ящик
            if (go instanceof BoxMagnetic) {
                //прицепиться к ЛЮБОМУ магнитному ящику
                BoxMagnetic bm = (BoxMagnetic)go;
                linkBox(bm);
            }
        }
    }
}
