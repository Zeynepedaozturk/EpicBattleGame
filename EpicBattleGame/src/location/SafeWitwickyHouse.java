package location;

import game.Player;

public class SafeWitwickyHouse extends City{
    public SafeWitwickyHouse(Player player) {
        super(1,"Güvenli Witwicky Evi" ,player);
    }

    @Override
    public boolean onLocation() {
    	System.out.println("----------------------");
        System.out.println("Guvenli Evdesiniz.");
        if(getPlayer().win()){
            System.out.println("Tebrikler, tum itemleri topladiniz ve oyunu kazandiniz.");
            System.exit(0);
        }
        System.out.println("Mevcut Sagliginiz :"+getPlayer().getHero().getHealth());
        System.out.println("Caniniz yenilendi.");
        getPlayer().getHero().setHealth(getPlayer().getHero().getDefaultHealth());
        System.out.println("Sagliginiz :"+getPlayer().getHero().getHealth());
        return true;
    }
}
