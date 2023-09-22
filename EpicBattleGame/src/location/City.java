package location;

import game.Player;

public abstract class City extends Location{

    public City(int id,String name, Player player) {
        super(id,name, player);
    }

    @Override
    public boolean onLocation() {
        return true;
    }
}