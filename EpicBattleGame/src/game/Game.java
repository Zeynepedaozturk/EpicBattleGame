package game;

import characters.Bumblebee;
import characters.Hero;
import characters.SamWitwicky;
import characters.OptimusPrime;
import inventory.Armor;
import inventory.Inventory;
import inventory.Weapon;
import location.*;

import java.util.Scanner;

public class Game {

    Scanner scanner=new Scanner(System.in);

    private Player player;
    private Location location;
    private Weapon weapon=new Weapon(0,"Yok",0,0) ;
    private Armor armor=new Armor(0,"Yok",0,0);
    private Inventory inventory=new Inventory(false,false,false,false,weapon,armor);

    public void start(){
        System.out.println("MACERA OYUNUNA HOSGELDINIZ.");
        System.out.println("---------------------------");
        System.out.print("Nickinizi Giriniz : ");
        String nick=scanner.next();
        int heroNumber;
        Hero[] charList={new OptimusPrime(),new Bumblebee(),new SamWitwicky()};
        while (true) {
            System.out.println("Karakter Listesi ");
            for (Hero chr : charList){
                System.out.print(chr.getId()+"-"+ chr.getName() + " : Hasar="+chr.getDamage() +" Saglik="+chr.getHealth()+" Para="+chr.getMoney()+"\n");
            }
            System.out.print("Secmek istediginiz karakter numarasini giriniz : ");

            heroNumber = scanner.nextInt();
            if(heroNumber>3 || heroNumber<1){
                System.out.println("Yanlis numara girdiniz lutfen tekrar deneyin.");
            }else{
                break;
            }
        }
        player = new Player(heroNumber,nick,inventory);
        player.selectChar();
        while (true){
            System.out.println(player.toString());
            System.out.println("-------------------------------------------------------");
            System.out.println("Lokasyon Listesi :");
            Location[] locations={new SafeWitwickyHouse(player),new ToolPlanet(player),
                    new Forest(player),new Moon(player),new Barrage(player),new Cybertron(player)};
            for(Location loc: locations){
                System.out.println(loc.getId()+"-"+loc.getName());
            }
            System.out.println("7-Envanter");
            System.out.println("9-Oyunu Sonlandir.");
            System.out.print("Gitmek istediginiz lokasyonun numarasini yaziniz : ");
            int locationId=scanner.nextInt();

            if(locationId==9){
                System.out.println("Oyundan cikis yapiliyor. Tekrar bekleriz!");
                System.exit(0);
            }
            if(!player.selectLocation(locationId)){
                System.out.println("Oyun bitti! Kaybettiniz!");
                break;
            }
        }
    }

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}