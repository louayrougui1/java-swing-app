package IHM;

import javax.swing.*;
import java.awt.*;

public class Grid extends JInternalFrame {
    JButton btn1,btn2,btn3,btn4,btn5,btn6,btn7;
    public Grid(){
        //creation frame
        this.setTitle("Grid");
        this.setSize(1000, 700);
        this.setLayout(new GridLayout(2,4));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setResizable(true);
        //creation des bouttons

        btn1=new JButton("btn1");
        btn2=new JButton("btn2");
        btn3=new JButton("btn3");
        btn4=new JButton("btn4");
        btn5=new JButton("btn5");
        btn6=new JButton("btn6");
        btn7=new JButton("btn7");
        //ajout des bouttons
        this.add(btn1);
        this.add(btn2);
        this.add(btn3);
        this.add(btn4);
        this.add(btn5);
        this.add(btn6);
        //this.add(btn7);
    }


}
