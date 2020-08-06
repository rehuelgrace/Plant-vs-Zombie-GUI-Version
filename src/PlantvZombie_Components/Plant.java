
package PlantvZombie_Components;

public abstract class Plant {

    private int health;
    protected int x;
    protected int y;
    protected Game gp;


    public Plant(Game gp,int x,int y){
        this.x = x;
        this.y = y;
        gp = gp;
    }

    public void stop(){}

    public int getHealthPlant(){
    	return health;
    }
    public void setHealthPlant(int health){
        this.health = health;
    }
    public void damagePlant(int powerZombie){
        this.health -= powerZombie;
    }
}