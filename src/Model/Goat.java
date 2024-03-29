package Model;

// Коза
public class Goat extends MovableObject {

    //требуемая энергия для действия козы
    private static int requirementEnergy() {
        return 1;
    }

    private int _energy; //энергия козы
    private boolean _gorged; //коза сыта

    public int energy() {
        return _energy;
    }
    public boolean gorged() {
        return _gorged;
    }

    public Goat(int energy) {
        _energy = energy;
        _gorged = false;
    }

    //сходить по направлению
    public void go(Direction d, boolean pull) {

        //если достаточно энергии
        if (energyEnough()) {

            Cell neighbor = getCell().getNeighbor(d); //соседняя ячейка
            if (neighbor != null) {
                GameObject neighborObject = neighbor.getGameObject(); //объект на соседней ячейке

                if (neighborObject instanceof MovableObject) //если соседний объект сдвигаемый
                    ((MovableObject)neighborObject).move(d); //сдвинуть его
                else if (neighborObject instanceof Cabbage) { //иначе если капуста
                    eatCabbage((Cabbage)neighborObject); //съесть её
                    _gorged = true;
                }

                //если соседняя ячейка пуста
                if (emptyAbove(d)) {

                    move(d); //сдвинуться на неё

                    if (pull) { //режим притягивания объектов
                        //притянуть возможный сдвигаемый объект
                        Cell neighbor1 = getCell().getNeighbor(d.opposite()).getNeighbor(d.opposite());
                        if (neighbor1 != null) {
                            GameObject neighborObject1 = neighbor1.getGameObject();
                            if (neighborObject1 instanceof MovableObject)
                                ((MovableObject)neighborObject1).move(d);
                        }
                    }

                    lowerEnergy(); //потратить энергию
                }
            }
        }
    }

    //съесть капусту
    private void eatCabbage(Cabbage c) {
        c.removeFromCell();
    }

    //достаточно энергии
    public boolean energyEnough() {
        return energy() >= requirementEnergy();
    }

    //отнять энергию
    private void lowerEnergy() {
        _energy -= requirementEnergy();
    }
}
