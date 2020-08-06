package PlantvZombie_Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Card extends JPanel implements MouseListener {

    Image img;
    ActionListener actL;

    public Card(Image img){
        setSize(64,90);
        this.img = img;
        addMouseListener(this);
    }

    public void setAction(ActionListener actL){
        this.actL = actL;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(img,0,0,null);
    }

    @Override
    public void mouseClicked(MouseEvent pointer) {

    }

    @Override
    public void mousePressed(MouseEvent pointer) {

    }

    @Override
    public void mouseReleased(MouseEvent pointer) {
        if(actL != null){
            actL.actionPerformed(new ActionEvent(this,ActionEvent.RESERVED_ID_MAX+1,""));
        }
    }

    @Override
    public void mouseEntered(MouseEvent pointer) {

    }

    @Override
    public void mouseExited(MouseEvent pointer) {

    }
    public class PlantButton {
	
	}
}
