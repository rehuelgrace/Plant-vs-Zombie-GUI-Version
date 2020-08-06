package PlantvZombie_Components;

import javax.swing.*;
import java.awt.*;

public class Pea {

    protected int positionRow;
    protected Game gp;
    protected int track;
    private int power;

    public Pea(Game gp,int track,int startRow){
        this.gp = gp;
        this.track = track;
        positionRow = startRow;
        setPowerPlant(200);
    }

    public void advance(){
        Rectangle pRect = new Rectangle(positionRow,130+track*120,28,28);
        for (int i = 0; i < gp.zombieIndieTrack.get(track).size(); i++) {
            Zombie zi = gp.zombieIndieTrack.get(track).get(i);
            Rectangle zRect = new Rectangle(zi.zomposRow,109 + track*120,400,120);
            if(pRect.intersects(zRect)){
                zi.attacked(power);
                boolean exit = false;
                if(zi.getHealth() < 0){
                    System.out.println("ZOMBIE DIE");
                    gp.zombieIndieTrack.get(track).remove(i);
                    exit = true;
                }
                gp.peasTrack.get(track).remove(this);
                if(exit) break;
            }
        }    
        for (int j = 0; j < gp.zombieBossTrack.get(track).size(); j++) {
            Zombie zb = gp.zombieBossTrack.get(track).get(j);
            Rectangle zRectb = new Rectangle(zb.zomposRow,109 + track*120,400,120);
            if(pRect.intersects(zRectb)){
                zb.attacked(power);
                boolean exit = false;
                if(zb.getHealth() < 0){
                    System.out.println("ZOMBIE DIE");
                    
                    gp.zombieBossTrack.get(track).remove(j);
                    exit = true;
                }
                gp.peasTrack.get(track).remove(this);
                if(exit) break;
            }
        }
        positionRow += 25;
    }
    public int getPowerPlant(){
        return power;
    }
    public void setPowerPlant(int power){
        this.power = power;
    }

}
