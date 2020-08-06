package PlantvZombie_Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Sunflower extends JPanel {

    Game gp;
    Image sunImage;

    static int value;

    int destruct = 200;

    public Sunflower(Game parent){
        this.gp = parent;
        this.value = 200;
    }

    public void addValue()
    {
        gp.setSunPoint(gp.getSunPoint()+50);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(sunImage,0,0,null);
    }
    
}
