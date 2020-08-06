
package PlantvZombie_Components;

import java.awt.*;


public class FreezePea extends Pea {

    public FreezePea(Game gp,int track,int startRow){
        super(gp,track,startRow);
        setPowerPlant(300);
    }

    @Override
    public void advance(){
        Rectangle pRect = new Rectangle(positionRow,130+track*120,28,28);
        for (int i = 0; i < gp.zombieIndieTrack.get(track).size(); i++) {
            Zombie zi = gp.zombieIndieTrack.get(track).get(i);
            Rectangle zRect = new Rectangle(zi.zomposRow,109 + track*120,400,120);
            if(pRect.intersects(zRect)){
                zi.attacked(getPowerPlant());
                zi.slow();
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
                zb.attacked(getPowerPlant());
                zb.slow();
                boolean exit = false;
                if(zb.getHealth() < 0){
                    System.out.println("ZOMBIE BOSS DIE");
                    gp.zombieBossTrack.get(track).remove(j);
                    exit = true;
                }
                gp.peasTrack.get(track).remove(this);
                if(exit) break;
            }
        }
        positionRow += 25;
    }
}