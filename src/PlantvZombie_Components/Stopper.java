package PlantvZombie_Components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Stopper extends JPanel implements MouseListener {

    private ActionListener actL;

    public Stopper(){
        setOpaque(false);
        addMouseListener(this);
        setSize(100,120);
    }

    public Plant assignedPlant;

    public void setPlant(Plant p){
        assignedPlant = p;
    }

    public void removePlant(){
        assignedPlant.stop();
        assignedPlant = null;
    }

    public boolean isInsideCollider(int tx){
        return (tx > getLocation().x) && (tx < getLocation().x + 100);
    }

    public void setAction(ActionListener actL){
        this.actL = actL;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(actL != null){
            actL.actionPerformed(new ActionEvent(this,ActionEvent.RESERVED_ID_MAX+1,""));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
