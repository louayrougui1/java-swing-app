package IHM;

import javax.swing.*;
import java.awt.*;

public class FrameFlow extends JInternalFrame {
    JButton btn1,btn2,btn3,btn4,btn5,btn6;
    public FrameFlow(){
        //creation frame
        this.setTitle("Flow");
        this.setSize(1000, 700);

        this.setLayout(new FlowLayout());
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
        //ajout des bouttons
        this.add(btn1);
        this.add(btn2);
        this.add(btn3);
        this.add(btn4);
        this.add(btn5);
        this.add(btn6);
    }

}
