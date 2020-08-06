package PlantvZombie_Components;

public class ZombieIndie extends Zombie {
    public ZombieIndie(Game gp,int zilane){
        super(gp,zilane);
        setSpeed(1);
        setHealth(800);
        setPower(80);
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}