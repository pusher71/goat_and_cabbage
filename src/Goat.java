// Коза
public class Goat extends MovableObject {

    //требуемая энергия для действия козы
    private static int requirementEnergy() {
        return 1;
    }

    private int energy; //энергия козы
    private boolean gorged; //коза сыта

    public int energy() {
        return energy;
    }
    public boolean gorged() {
        return gorged;
    }

    public Goat(int energy) {
        this.energy = energy;
        gorged = false;
    }

    //сходить по направлению
    public void go(Direction d) {

        //если достаточно энергии
        if (energyEnough()) {

            Cell neighbor = getCell().getNeighbor(d); //соседняя ячейка
            GameObject neighborObject = neighbor.getGameObject(); //объект на соседней ячейке

            if (neighborObject instanceof MovableObject) //если соседний объект - ящик
                ((MovableObject)neighborObject).move(d); //сдвинуть его
            else if (neighborObject instanceof Cabbage) { //иначе если капуста
                eatCabbage((Cabbage)neighborObject); //съесть её
                gorged = true;
            }

            //если соседняя ячейка пуста
            if (neighbor.isEmpty()) {
                move(d); //сдвинуться на неё
                lowerEnergy(); //потратить энергию
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
        energy -= requirementEnergy();
    }
}
