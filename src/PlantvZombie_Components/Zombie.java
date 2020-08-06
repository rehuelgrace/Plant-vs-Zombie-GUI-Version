package PlantvZombie_Components;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.*;
import java.util.TimerTask;

//abstractnya zombie, gabisa dicreate
public abstract class Zombie implements Runnable{

    public Timer attackTimer;
    private int health;
    private int power;
    private int speed;
    protected int zomposRow = 1000;
    protected int zombieLane;
    protected boolean isMoving = true;
    
    protected Game gp;

    public Zombie(Game gp,int lane){
        this.gp = gp;
        this.zombieLane = lane;
    }
    
    //getter
    public int getSpeed(){
    	return speed;
    }
    public int getHealth(){
        return health;
    }
    public int getPower(){
        return power;
    }
    
    //setter
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setPower(int power){
        this.power = power;
    }
    public void setHealth(int health){
        this.health = health;
    }
        
    int slowInt = 0;
    public void slow(){
        slowInt = 1000;
    }
    
    //movement
    public void attacked(int plantpower){
        this.health -= plantpower;
    }

    @SuppressWarnings("null")
    public void move(){
        if(isMoving) { //kalo blm nabrak
            boolean nabrak = false;
            Stopper makezstop = null;
            
            Plant plant;
            for (int i = zombieLane * 9; i < ((zombieLane + 1) * 9); i++) {
                if (gp.stoppers[i].assignedPlant != null && gp.stoppers[i].isInsideCollider(zomposRow)) {
                    nabrak = true;
                    makezstop = gp.stoppers[i];
                }
            }
            
            if (!nabrak) {
                if(slowInt>0){
                    if(slowInt % 2 == 0) {
                        if (this instanceof ZombieIndie)
                        {
                            zomposRow -= 1;
                        }
                        else if (this instanceof ZombieBoss)
                        {
                            zomposRow -= 2;
                        }
                    }
                    slowInt--;
                }
                else 
                {
                    if (this instanceof ZombieIndie)
                        {
                            zomposRow -= 1;
                        }
                    else if (this instanceof ZombieBoss)
                        {
                            zomposRow -= 2;
                        }
                    //zomposRow --;
                }                
            } 
            else //nabrak plant
            { 
                //new java.util.Timer().schedule(new TimerTask(){
                    //@Override
                    //public void run(){
                    try
                    {
                        
                        makezstop.assignedPlant.damagePlant(getPower());
                        if (makezstop.assignedPlant.getHealthPlant() > 0)
                        {
                            
                            Thread.sleep(500);
                        }
                        //else Thread.sleep(5000);
                    }
                    catch(InterruptedException ex)
                    {
                        System.out.println("x");
                    }
                    
                //System.out.println("kena plant"+makezstop.assignedPlant.getHealthPlant());
                if (makezstop.assignedPlant.getHealthPlant() <= 0) 
                {
                    makezstop.removePlant();
                }
            }
            
            //sdh nabrak tembok, zombie won ;))
            if (zomposRow < 0) {
                isMoving = false;
                JOptionPane.showMessageDialog(gp,"ZOMBIES MENGAMBIL ALIH RUMAHMU!" + '\n' + "Game berakhir!");
                System.exit(0);
            }
        }
    }


    
}
