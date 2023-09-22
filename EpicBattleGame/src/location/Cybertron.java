package location;

import game.Player;
import monster.Devastator;

public class Cybertron extends BattleLoc{
    public Cybertron(Player player) {
        super(6, "Cybertron Gezegeni", player, new Devastator(), "gold");
    }
}