package IHM;

import javax.swing.*;
import java.awt.*;

public class Border extends JInternalFrame {
    JButton N,W,E,btn1,btn2,Center;
    JPanel ps;
    public Border(){

        this.setTitle("Border");
        this.setSize(1000, 700);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setResizable(true);

        //creation des bouttons

        N=new JButton("North");
        W=new JButton("West");
        E=new JButton("East");

        Center=new JButton("center");
        btn1=new JButton("btn1");
        btn2=new JButton("btn2");
        btn1.setPreferredSize(new Dimension(100,50));
        btn2.setPreferredSize(new Dimension(100,50));
        //creation panel
        ps=new JPanel();
        ps.setLayout(new FlowLayout());
        ps.add(btn1);
        ps.add(btn2);
        //ajout des bouttons
        this.add(N, BorderLayout.NORTH);
        this.add(ps,BorderLayout.SOUTH);
        this.add(W,BorderLayout.WEST);
        this.add(E,BorderLayout.EAST);
        this.add(Center,BorderLayout.CENTER);//par defaut

    }

}
