package PlantvZombie_Components;

import java.awt.event.ActionEvent;
import javax.swing.*;

public class FreezePeashooter extends Plant {

    public Timer shootTimer;


    public FreezePeashooter(Game gp,int x,int y) {
        super(gp,x,y);
        shootTimer = new Timer(2000,(ActionEvent e) -> {
            if(gp.zombieIndieTrack.get(y).size() > 0) {
                gp.peasTrack.get(y).add(new FreezePea(gp, y, 103 + this.x * 100));
            }
            if(gp.zombieBossTrack.get(y).size() > 0) {
                gp.peasTrack.get(y).add(new FreezePea(gp, y, 103 + this.x * 100));
            }
        });
        shootTimer.start();
        setHealthPlant(800);
    }

    @Override
    public void stop(){
        shootTimer.stop();
    }

}