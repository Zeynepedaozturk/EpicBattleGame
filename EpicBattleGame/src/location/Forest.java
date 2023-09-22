package location;

import game.Player;
import monster.Scorponok;

public class Forest extends BattleLoc{
   
    public Forest(Player player) {
        super(3, "Orman", player, new Scorponok(),"wood");
    }
}