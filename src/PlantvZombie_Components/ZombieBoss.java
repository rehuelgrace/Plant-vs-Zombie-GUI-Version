package PlantvZombie_Components;

public class ZombieBoss extends Zombie {
    public ZombieBoss(Game gp,int zblane){
        super(gp,zblane);
        setSpeed(2);
        setHealth(1200);
        setPower(120);
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}