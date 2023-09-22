package location;


import monster.Obstacle;
import java.util.Locale;
import java.util.Random;

import game.Player;

public abstract class BattleLoc extends Location{

    Random random=new Random();
    private Obstacle obstacle;
    private String award;
    private int numObs;
	private boolean finish;
    public BattleLoc(int id, String name, Player player, Obstacle obstacle, String award) {
        super(id, name, player);
        this.obstacle=obstacle;
        this.award=award;
    }

    @Override
    public boolean onLocation() {
    	System.out.println("------------------");
        System.out.println("Suan bulundugunuz yer : "+this.getName()+" ");
        setNumObs(this.getId());
        System.out.println("Dikkatli olun burada "+this.getNumObs()+" adet " +this.getObstacle().getName().toUpperCase()+ " yasiyor!");
        System.out.print("Canavarla savasmak için 'S' kacmak için 'K' basiniz : ");
        String sel=scanner.nextLine().toUpperCase();
        if(sel.equals("S")){
            if(!combat(getNumObs()) && this.getPlayer().getPlayerHealth()==0){
                return false;
            }
        }
        else if(sel.equals("K")){
            System.out.println("Canavardan kactiniz.");

        }
        else {
            System.out.println("Yanlis girdiniz! Ana Ekrana donuluyor!");
        }
        return true;
    }

    public boolean combat(int value){
        boolean isFirst=true;
        for (int i = 1; i <= value; i++) {

            System.out.println(this.getPlayer().toString());
            System.out.println(this.obstacle.toString());
            setFinish(true);
            while (this.obstacle.getHealth()>0 && this.getPlayer().getPlayerHealth()>0){
                if(firstShot()==2 && isFirst) { //ilk olarak oyuncu vuruyor
                    System.out.println("Vurus Hakki Sizle Baslıyor.");
                    System.out.println("Vurmak için 'V' , kacmak için 'K' basiniz .");
                    String match = scanner.nextLine().toUpperCase(Locale.ROOT);
                    if (match.equals("V")) {
                        System.out.println(this.getPlayer().getNick() + " " + this.obstacle.getName() + " canavarina "
                                + this.getPlayer().getPlayerDamage() + " puan hasar vurdunuz!");
                        int obsHealth = this.getObstacle().getHealth() - this.getPlayer().getPlayerDamage();
                        this.getObstacle().setHealth(obsHealth);
                        System.out.println(this.obstacle.getName() + " canavarinin cani " + this.getObstacle().getHealth() + " kaldi.");
                        if (this.getObstacle().getHealth() > 0) { //canavar olmezse oyuncuya vuruyor
                            System.out.println(" "+i + ".  Canavar size vurdu !");
                            int damage = this.getObstacle().getDamage() - getPlayer().getPlayerDefence();
                            if (damage < 0) damage = 0;
                            this.getPlayer().getHero().setHealth(this.getPlayer().getPlayerHealth() - damage);
                            System.out.println("Canınız: " + this.getPlayer().getPlayerHealth()+" ");
                            if (this.getPlayer().getPlayerHealth() == 0) {
                                return false;
                            }
                        }
                    }
                    else {
                        System.out.println("Savas alanindan kactiniz.");
                        return true;
                    }
                }
                else {
                    setFinish(true);
                    isFirst = false;
                    System.out.println("Vurus hakki canavarla basliyor.");
                    System.out.println(" "+i + ". Canavar size vurdu!");
                    int damage = this.getObstacle().getDamage() - getPlayer().getPlayerDefence();
                    if (damage < 0) damage = 0;
                    this.getPlayer().getHero().setHealth(this.getPlayer().getPlayerHealth() - damage);
                    System.out.println("Caniniz : " + this.getPlayer().getPlayerHealth()+" ");
                    if (this.getPlayer().getPlayerHealth() < 1) {
                        return false;
                    }
                    System.out.println("Vurmak icin 'V' , kacmak icin 'K' basiniz .");
                    String match = scanner.nextLine().toUpperCase(Locale.ROOT);
                    if (match.equals("V")) {
                        System.out.println(this.getPlayer().getNick() + " " + this.obstacle.getName() + " canavarina  "
                                + this.getPlayer().getPlayerDamage() + " puan hasar vurdunuz!");
                        int obsHealth = this.getObstacle().getHealth() - this.getPlayer().getPlayerDamage();
                        this.getObstacle().setHealth(obsHealth);
                        System.out.println(this.obstacle.getName() + " canavarinin cani " + this.getObstacle().getHealth() + " kaldi.");
                    } else {
                        System.out.println("Savas alanindan kactiniz.");

                        return true;
                    }
                }
            }
            if(this.getObstacle().getHealth()<=0){
                isFirst=true;
                int x=value-i;
                System.out.println("Kazandiniz. Canavar oldu.Kalan canavar sayisi:"+x+" ");

                    giveMoney(getObstacle().getAwardMoney());
                    System.out.println("Odulunuz verildi. Bakiyeniz : " + this.getPlayer().getPlayerMoney()+"");
                    if(x==0) {
                        giveItem(this.getAward());
                        System.out.println(" "+this.getAward() + " Odulunu kazandiniz!"+" ");
                    }

                if(x!=0){
                    this.getObstacle().setHealth(this.getObstacle().getDefaultHealth());
                }
            }
        }
        return true;
    }



    private int firstShot(){
        Random r=new Random();
        //1 cikarsa canavar, 2 cikarsa player ilk vursun.
        int x= r.nextInt(1,3);
        return x;
    }

    private void giveMoney(int awardMoney) {
        this.getPlayer().getHero().setMoney(this.getPlayer().getPlayerMoney()+awardMoney);
    }
    private void giveItem(String award){
        if(award.equals("food")){
            getPlayer().getInventory().setFood(true);
        }
        if(award.equals("water")){
            getPlayer().getInventory().setWater(true);
        }
        if(award.equals("wood")){
            getPlayer().getInventory().setWood(true);
        }
        if(award.equals("gold")){
            getPlayer().getInventory().setGold(true);
        }
    }

    public int getNumObs() {
        return numObs;
    }

    public void setNumObs(int id) {

            this.numObs = random.nextInt(1,4);
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}
}