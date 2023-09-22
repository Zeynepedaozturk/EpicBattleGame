package game;


import characters.*;
import inventory.*;
import location.*;

public class Player {
    private int heroNumber;
    private Hero hero;
    private Inventory inventory;
    private String nick;
    private Location location;

    public Player(int heroNumber,String nick,Inventory inventory){
        this.heroNumber=heroNumber;
        if(nick.equals(""))
            nick="Player1";
        this.nick=nick;
        this.inventory=inventory;
    }

    public void selectChar(){
        switch (this.heroNumber){
            case 1:
            default:
                this.hero=new OptimusPrime();
                break;
            case 2:
                this.hero=new Bumblebee();
                break;
            case 3:
                this.hero=new SamWitwicky();
                break;
        }
    }

    public boolean selectLocation(int locId){
        switch (locId){
            case 1:
            default:
                this.location=new SafeWitwickyHouse(this);
                break;
            case 2:
                this.location=new ToolPlanet(this);
                break;
            case 3:
                if(isWinLoc(new Forest(this))){
                    this.location=new SafeWitwickyHouse(this);
                    break;
                }else {
                    this.location = new Forest(this);
                    break;
                }
            case 4:
                if(isWinLoc(new Moon(this))){
                    this.location=new SafeWitwickyHouse(this);
                    break;
                }else {
                    this.location = new Moon(this);
                    break;
                }
            case 5:
                if(isWinLoc(new Barrage(this))){
                    this.location=new SafeWitwickyHouse(this);
                    break;
                }else {
                    this.location=new Barrage(this);
                    break;
                }
            case 6:
                this.location=new Cybertron(this);
                break;
            case 7:
                getItemList();
                return true;
        }
        return location.onLocation();
    }


    private boolean isWinLoc(BattleLoc battleLoc){
        if(battleLoc.getId()==3 && this.getInventory().isWood()){
            System.out.println("Bu alanda savasi kazandiniz. Tekrar Giremezsiniz.");
            return true;
        }
        if(battleLoc.getId()==4 && this.getInventory().isFood()){
            System.out.println("Bu alanda savasi kazandiniz. Tekrar Giremezsiniz.");
            return true;
        }
        if(battleLoc.getId()==5 && this.getInventory().isWater()){
            System.out.println("Bu alanda savasi kazandiniz. Tekrar Giremezsiniz.");
            return true;
        }
        if(battleLoc.getId()==6 && this.getInventory().isGold()){
            System.out.println("Bu alanda savasi kazandiniz. Tekrar Giremezsiniz.");
            return true;
        }
        return false;

    }

    private void getItemList() {
        System.out.println("Silah : "+this.getInventory().getWeapon().getName());
        System.out.println("Zırh : "+this.getInventory().getArmor().getName());
        System.out.println("Water : "+this.getInventory().isWater());
        System.out.println("Food : "+this.getInventory().isFood());
        System.out.println("Wood : "+this.getInventory().isWood());
        System.out.println("Gold : "+this.getInventory().isGold());
    }

    public boolean win(){
        return this.getInventory().isWood() && this.getInventory().isFood() && this.getInventory().isWater()&&this.getInventory().isGold();
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getHeroNumber() {
        return heroNumber;
    }

    public void setHeroNumber(int heroNumber) {
        this.heroNumber = heroNumber;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    public int getPlayerHealth(){
        return this.getHero().getHealth();
    }
    public int getPlayerDamage(){
        return this.getHero().getDamage();
    }
    public int getPlayerMoney(){
        return this.getHero().getMoney();
    }
    public int getPlayerDefence(){
        return this.getInventory().getArmor().getDefence();
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public String toString() {
    	System.out.println("-----------------------");
        return  "Oyuncunun Ozellikleri :\n" +
                "Nick = " + this.nick +
                ", Seçilen karakter= " + this.hero.getName() +
                ", Karakter canı = " + this.hero.getHealth() +
                ", Karakterin başlangıçtaki hasarı = " + this.hero.getDefaultDamage() +
                ", Karakterin toplam hasarı = "+this.getHero().getDamage()+
                ", Paranız = " + this.getHero().getMoney() +
                ", Zırh savunması ="+this.getInventory().getArmor().getDefence()+
                ", Silah hasarı ="+this.getInventory().getWeapon().getDamage()+" ";
    }

}