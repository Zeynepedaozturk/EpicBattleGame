package location;

import game.Player;
import inventory.*;

public class ToolPlanet extends City {
    public ToolPlanet(Player player) {
        super(2,"Envanter Gezegen", player);
    }
    @Override
    public boolean onLocation() {
        System.out.println("Envanter Gezegenine hoşgeldiniz.");
        menu();
        return true;
    }
    public void menu(){
        System.out.println("Gezegende mevcut olan ekipmanlar aşağıdaki gibidir.");
        System.out.println("1-SILAH\n2-ZIRH\n3-CIKIS YAP");
        System.out.print("Seciminiz :");
        int equipment= Location.scanner.nextInt();
        switch (equipment){
            case 1:
                getWeaponList();
                break;
            case 2:
                getArmorList();
                break;
            case 3:
            default:
                System.out.println("Cikis yapiliyor.");
                break;
        }

    }

    private void getWeaponList() {
        Weapon[] weapons = {new Gun(), new ProtectiveClothing(), new LaserWeapon()};

        for (Weapon weapon : weapons) {
            System.out.println(weapon.getId() + "-" + weapon.getName() + " -->  " +
                    "Hasari : " + weapon.getDamage() + " Ucreti : " + weapon.getMoney());
        }
        System.out.println("Mevcut Bakiyeniz : " + getPlayer().getHero().getMoney());
        System.out.print("Almak istediginiz silahin numarasini yaziniz (Cikis yapmak icin 9 yazin) : ");
        int number = scanner.nextInt();
        while (number < 1 || number > weapons.length) {
            if (number == 9) {
                System.out.println("Cikis yapiliyor.");
                break;
            }
            System.out.print("Gecersiz deger girdiniz, tekrar girin :");
            number = scanner.nextInt();
        }
        if (number != 9) buyWeapon(number);


    }

    private void buyWeapon(int number){
        Weapon[] weapons={new Gun(),new ProtectiveClothing(),new LaserWeapon()};
        if(getPlayer().getInventory().getWeapon().getName()==weapons[number-1].getName()){
            System.out.println("Bu silah envanterinizde mevcut.");
        }
        else if(getPlayer().getHero().getMoney()>=weapons[number-1].getMoney()){
            int balance=getPlayer().getHero().getMoney()-weapons[number-1].getMoney();
            getPlayer().getHero().setMoney(balance);
            getPlayer().getInventory().setWeapon(weapons[number-1]);
            System.out.println("Silah alindi.");
            int newDamage=getPlayer().getHero().getDefaultDamage()+weapons[number-1].getDamage();
            getPlayer().getHero().setDamage(newDamage);
            System.out.println("Mevcut Bakiyeniz :"+getPlayer().getHero().getMoney());
            System.out.println("Silahiniz :"+getPlayer().getInventory().getWeapon().getName()+
                    "\nHasari : "+getPlayer().getInventory().getWeapon().getDamage()+
                    "\nKarakterinizin hasari : "+getPlayer().getHero().getDamage());
        }
        else {
            System.out.println("Almak istediginiz silaha paraniz yetmemektedir!");
            System.out.println("Mevcut Bakiyeniz : "+getPlayer().getHero().getMoney()+ " Silahin Parasi : "+weapons[number-1].getMoney());
        }
    }

    private void getArmorList() {

        for (Armor armor: Armor.armorList()){
            System.out.println(armor.getId()+"-"+armor.getName()+" -->  " +
                    "Savunmasi : "+armor.getDefence()+" Ucreti : "+armor.getMoney());
        }
        System.out.println("Bakiyeniz :"+this.getPlayer().getPlayerMoney());
        System.out.print("Almak istediginiz zirhin numarasini yaziniz(Cikis icin 9'a basin) : ");
        int number=scanner.nextInt();
        while (number<1 || number> Armor.armorList().length){
            if(number==9){
                System.out.println("Cikis yapiliyor.");
                break;
            }
            System.out.print("Gecersiz deger girdiniz, tekrar girin :");
            number=scanner.nextInt();
        }
        if(number!=9) getPlayer().getInventory().setArmor(buyArmor(number));
    }

    private Armor buyArmor(int number){
        Armor selectedArmor=Armor.getArmorById(number);
        if(this.getPlayer().getInventory().getArmor().getId()==selectedArmor.getId()){
            System.out.println("Bu zirh envanterinizde mevcuttur.");
            selectedArmor=getPlayer().getInventory().getArmor();
        }
        else if(getPlayer().getHero().getMoney()< selectedArmor.getMoney()){
            System.out.println("Bakiyeniz yetersiz. Zirhi alamazsiniz.");
            selectedArmor=getPlayer().getInventory().getArmor();
        }
        else {
            getPlayer().getInventory().setArmor(selectedArmor);
            int balance=getPlayer().getHero().getMoney()- selectedArmor.getMoney();
            getPlayer().getHero().setMoney(balance);
            System.out.println("Zirhi aldiniz.");
            System.out.println("Mevcut bakiyeniz : "+getPlayer().getHero().getMoney());
            System.out.println("Zirh savunmasi :"+getPlayer().getInventory().getArmor().getDefence());

        }
        return selectedArmor;
    }
}
