package location;

import game.Player;
import monster.Megatron;

public class Barrage extends BattleLoc {
    
    public Barrage(Player player) {
        super(5, "Baraj", player, new Megatron(),"water");
    }

}
