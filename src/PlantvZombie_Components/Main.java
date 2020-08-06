package PlantvZombie_Components;

import javax.swing.*;
import java.awt.event.*;

public class Main extends JFrame {

    enum PlantType{
        None,
        Sunflower,
        Peashooter,
        FreezePeashooter
    }

    public Main(){
        setSize(1012,785);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel sun = new JLabel("SUN");
        sun.setLocation(37,80);
        sun.setSize(60,20);

        Game gp = new Game(sun);
        gp.setLocation(0,0);
        getLayeredPane().add(gp,new Integer(0));
        
        Card peashooter = new Card(new ImageIcon(this.getClass().getResource("/images/cards/card_peashooter.png")).getImage());
        peashooter.setLocation(110,8);
        peashooter.setAction((ActionEvent e) -> {
            gp.activePlantingBrush = PlantType.Peashooter;
        });
        getLayeredPane().add(peashooter,new Integer(3));

        Card freezepeashooter = new Card(new ImageIcon(this.getClass().getResource("/images/cards/card_freezepeashooter.png")).getImage());
        freezepeashooter.setLocation(175,8);
        freezepeashooter.setAction((ActionEvent e) -> {
            gp.activePlantingBrush = PlantType.FreezePeashooter;
        });
        getLayeredPane().add(freezepeashooter,new Integer(3));



        getLayeredPane().add(sun,new Integer(2));
        setResizable(false);
        setVisible(true);
    }
    
    static Main gw;
    public static void begin() {
       gw.dispose();
       gw = new Main();
    }
    public static void main(String[] args) {
        JFrame f=new JFrame(""); 
        JLabel a=new JLabel("Welcome to Plant vs Zombies");  
        a.setText("Welcome to Plant vs Zombies");
        a.setBounds(50,50,200,30);
        
        JButton b=new JButton("Start");  
        b.setBounds(50,100,95,30);  
        b.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
            new Main();  
        }  
        });
        
        JButton c = new JButton("Help");
        c.setBounds(50,150,95,30);
        c.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                JFrame g = new JFrame ("Help");
                JLabel d = new JLabel ("HelpMessage");
                JLabel d1 = new JLabel ("HelpMessage");
                JLabel d2 = new JLabel ("HelpMessage");
                JLabel d3 = new JLabel ("HelpMessage");
                JLabel d4 = new JLabel ("HelpMessage");
                d.setText("Ini adalah permainan Plant vs Zombie Sederhana");
                d1.setText("Zombie akan menyerang dari sisi kanan dan berjalan menuju ke kiri.");
                d2.setText("Pemain harus menghentikan zombie dengan cara menyerang menggunakan tanaman");
                d3.setText("Tanaman dapat dibeli menggunakan Sunflower Point");
                d4.setText("Jika zombie menyentuk sisi kiri maka permainan dinyatakan selesai");
                d.setBounds(50,20,500,30);
                d1.setBounds(50,50,500,30);
                d2.setBounds(50,80,500,30);
                d3.setBounds(50,110,500,30);
                d4.setBounds(50,140,500,30);
                g.add(d);
                g.add(d1);
                g.add(d2);
                g.add(d3);
                g.add(d4);
                g.setSize(600,400);
                g.setLayout(null);
                g.setVisible(true);
                g.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        
        f.add(b);
        f.add(a);  
        f.add(c);
        f.setSize(400,400);  
        f.setLayout(null);  
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
} 
    
    

