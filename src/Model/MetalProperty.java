package Model;

// Свойство металла
public class MetalProperty {

    protected InterlockedObject _interlockedObject; //связанный скрепляемый игровой объект

    public MetalProperty(InterlockedObject io) {
        _interlockedObject = io;
    }

    //произвести магнитную реакцию с соседом
    protected void reactWithNeighbor(Direction d) {

        Cell neighborCell = _interlockedObject.getCell().getNeighbor(d); //соседняя ячейка
        if (neighborCell != null && !neighborCell.isEmpty()) { //если на ней имеется объект

            GameObject go = neighborCell.getGameObject();

            //попался сцепляемый объект
            if (go instanceof InterlockedObject) {
                InterlockedObject io = (InterlockedObject)go; //привести игровой объект к сцепляемому типу
                MetalProperty mp = io.getMetalProperty(); //получить возможное свойство металла

                if (mp instanceof MagneticProperty) //если ящик обладает свойством магнита
                    _interlockedObject.link(io); //прицепиться к ЛЮБОМУ магнитному ящику
            }
        }
    }
}
