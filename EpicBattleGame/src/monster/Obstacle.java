package monster;

public class Obstacle {

    private int id;
    private String name;
    private int damage;
    private int health;
    private int awardMoney;
    private int defaultHealth;


    public Obstacle(int id,String name,int health,int damage,int awardMoney) {
        this.id = id;
        this.name=name;
        this.damage=damage;
        this.health=health;
        this.awardMoney=awardMoney;
        this.defaultHealth=health;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHealth(int health) {
        if(health<0){
            health=0;
        }
        this.health = health;
    }

    public int getAwardMoney() {
        return awardMoney;
    }

    public void setAwardMoney(int awardMoney) {
        this.awardMoney = awardMoney;
    }
    public int getDefaultHealth(){
        return defaultHealth;
    }

    @Override
    public String toString() {
        return "Canavarin ozellikleri :\n" +
                " Adi = " + this.getName() +
                ", Hasari = " + this.getDamage() +
                ", Cani = " + this.getHealth() +
                ", Odulu = " + this.getAwardMoney()+" ";
    }
}