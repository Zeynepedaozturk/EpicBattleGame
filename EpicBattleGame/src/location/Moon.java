package location;

import game.Player;
import monster.Thundercracker;

public class Moon extends BattleLoc {
    public Moon( Player player) {
        super(4, "Ay", player, new Thundercracker(),"food");
    }
}