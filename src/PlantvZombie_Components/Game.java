
package PlantvZombie_Components;

import javax.swing.*;
import java.awt.*;  
import java.awt.event.*; // event ActionEvent, event Action Listener, event Mouse Event, event Mouse Motion
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.*; //logging level, logging logger
public class Game extends JLayeredPane implements MouseMotionListener {

    Image bgImage;
    Image peashooterImage;
    Image freezePeashooterImage;
    Image sunflowerImage;
    Image peaImage;
    Image freezePeaImage;

    Image zombieIndieImage;
    Image zombieBossImage;
    Stopper[] stoppers;
    
    ArrayList<ArrayList<ZombieIndie>> zombieIndieTrack;
    ArrayList<ArrayList<ZombieBoss>> zombieBossTrack;
    ArrayList<ArrayList<Pea>> peasTrack;
    ArrayList<Sunflower> activeSuns;

    Timer redrawTimer;
    Timer advancerTimer;
    Timer sunProducer;
    Timer zombieBossProducer;
    Timer zombieIndieProducer;
    JLabel sunPointBoard;

    Main.PlantType activePlantingBrush = Main.PlantType.None;

    int mouseX , mouseY;

    private int sunPoint;

    public int getSunPoint() {
        return sunPoint;
    }

    public void setSunPoint(int sunPoint) {
        this.sunPoint = sunPoint;
        sunPointBoard.setText(String.valueOf(sunPoint));  // set nilai sunflower point di papan daftar
    }

    public Game(JLabel sunPointboard){
        setSize(1000,752);
        setLayout(null);
        addMouseMotionListener(this);
        this.sunPointBoard = sunPointboard;
        setSunPoint(250);  //nilai awal sunflower point

        bgImage  = new ImageIcon(this.getClass().getResource("/images/mainBG.png")).getImage();

        //plant
        peashooterImage = new ImageIcon(this.getClass().getResource("/images/plants/peashooter.gif")).getImage();
        freezePeashooterImage = new ImageIcon(this.getClass().getResource("/images/plants/freezepeashooter.gif")).getImage();
        peaImage = new ImageIcon(this.getClass().getResource("/images/pea.png")).getImage();
        freezePeaImage = new ImageIcon(this.getClass().getResource("/images/freezepea.png")).getImage();
        
        //zombies
        zombieIndieImage = new ImageIcon(this.getClass().getResource("/images/zombies/zombieindie.png")).getImage();
        zombieBossImage = new ImageIcon(this.getClass().getResource("/images/zombies/zombieboss.png")).getImage();

        //buat jalur untuk zombie
        zombieIndieTrack = new ArrayList<>();
        zombieIndieTrack.add(new ArrayList<>()); //line 1
        zombieIndieTrack.add(new ArrayList<>()); //line 2
        zombieIndieTrack.add(new ArrayList<>()); //line 3
        zombieIndieTrack.add(new ArrayList<>()); //line 4
        zombieIndieTrack.add(new ArrayList<>()); //line 5

        zombieBossTrack = new ArrayList<>();
        zombieBossTrack.add(new ArrayList<>()); //line 1
        zombieBossTrack.add(new ArrayList<>()); //line 2
        zombieBossTrack.add(new ArrayList<>()); //line 3
        zombieBossTrack.add(new ArrayList<>()); //line 4
        zombieBossTrack.add(new ArrayList<>()); //line 5
        
        //buat jalur untuk pea
        peasTrack = new ArrayList<>();
        peasTrack.add(new ArrayList<>()); //line 1
        peasTrack.add(new ArrayList<>()); //line 2
        peasTrack.add(new ArrayList<>()); //line 3
        peasTrack.add(new ArrayList<>()); //line 4
        peasTrack.add(new ArrayList<>()); //line 5

        stoppers = new Stopper[45];
        for (int i = 0; i < 45; i++) {
            Stopper a = new Stopper();
            a.setLocation(44 + (i%9)*100,109 + (i/9)*120);
            a.setAction(new PlantActionListener((i%9),(i/9)));
            stoppers[i] = a;
            add(a,new Integer(0));
        }

        activeSuns = new ArrayList<>();

        redrawTimer = new Timer(25,(ActionEvent e) -> {
            repaint();
        });
        redrawTimer.start();

        advancerTimer = new Timer(60,(ActionEvent e) -> move());
        advancerTimer.start();
        
        sunProducer = new Timer(5000,(ActionEvent e) -> {
            setSunPoint(getSunPoint()+25);
        });
        sunProducer.start();
        

        zombieIndieProducer = new Timer(5000,(ActionEvent e) -> {
            Random randomZombies = new Random();
            
            int lane = randomZombies.nextInt(5);
            Zombie zi = null;
            zi = new ZombieIndie(this, lane);
            zombieIndieTrack.get(lane).add((ZombieIndie) zi);
        });
        zombieIndieProducer.start();

        zombieBossProducer = new Timer(15000, (ActionEvent e) -> {
            Random randomZombies = new Random();
            int lane = randomZombies.nextInt(5);
            Zombie zb = new ZombieBoss(this, lane);
            zombieBossTrack.get(lane).add((ZombieBoss) zb);
        });
        zombieBossProducer.start();
        
        
    }
    

    private void move(){
        for (int i = 0; i < 5 ; i++) {
            for(Zombie zi : zombieIndieTrack.get(i)){
                zi.move();
            }

            for(Zombie zb : zombieBossTrack.get(i)){
                zb.move();
            }
            
            for (int j = 0; j < peasTrack.get(i).size(); j++) {
                Pea p = peasTrack.get(i).get(j);
                p.advance();
            }

        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(bgImage,0,0,null);

        //Draw Plants
        for (int i = 0; i < 45; i++) {
            Stopper c = stoppers[i];
            if(c.assignedPlant != null){
                Plant plant = c.assignedPlant;
                if(plant instanceof Peashooter){
                    graphics.drawImage(peashooterImage,60 + (i%9)*100,129 + (i/9)*120,null);
                }
                if(plant instanceof FreezePeashooter){
                    graphics.drawImage(freezePeashooterImage,60 + (i%9)*100,129 + (i/9)*120,null);
                }
            }
            else //null
            {
            }
        }

        
        for (int i = 0; i < 5 ; i++) {
            //Draw Zombies
            for(Zombie zb : zombieBossTrack.get(i)){
                graphics.drawImage(zombieBossImage,zb.zomposRow,109+(i*120),null);
            }
            
            for(Zombie zi: zombieIndieTrack.get(i))
            {
                graphics.drawImage(zombieIndieImage,zi.zomposRow,109+(i*120),null);
            }

            //Draw Peas
            for (int j = 0; j < peasTrack.get(i).size(); j++) {
                Pea p = peasTrack.get(i).get(j);
                if(p instanceof FreezePea){
                    graphics.drawImage(freezePeaImage, p.positionRow, 130 + (i * 120), null);
                }else {
                    graphics.drawImage(peaImage, p.positionRow, 130 + (i * 120), null);
                }
            }
        }
    }

    class PlantActionListener implements ActionListener {

        int x,y;

        public PlantActionListener(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(activePlantingBrush == Main.PlantType.Peashooter){
                if(getSunPoint() >= 100) {
                    stoppers[x + y * 9].setPlant(new Peashooter(Game.this, x, y));
                    setSunPoint(getSunPoint()-100);
                }
            }

            if(activePlantingBrush == Main.PlantType.FreezePeashooter){
                if(getSunPoint() >= 175) {
                    stoppers[x + y * 9].setPlant(new FreezePeashooter(Game.this, x, y));
                    setSunPoint(getSunPoint()-175);
                }
            }
            activePlantingBrush = Main.PlantType.None;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
