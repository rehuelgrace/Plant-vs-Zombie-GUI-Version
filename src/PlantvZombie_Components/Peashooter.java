package PlantvZombie_Components;

import java.awt.event.ActionEvent;
import javax.swing.*;

public class Peashooter extends Plant {

    public Timer shootTimer;


    public Peashooter(Game gp,int x,int y) {
        super(gp,x,y);
        shootTimer = new Timer(2000,(ActionEvent e) -> {
            if(gp.zombieIndieTrack.get(y).size() > 0) {
                gp.peasTrack.get(y).add(new Pea(gp, y, 103 + this.x * 100));
            }
            if(gp.zombieBossTrack.get(y).size() > 0) {
                gp.peasTrack.get(y).add(new Pea(gp, y, 103 + this.x * 100));
            }
        });
        shootTimer.start();
        setHealthPlant(400);
    }

    @Override
    public void stop(){
        shootTimer.stop();
    }

}
