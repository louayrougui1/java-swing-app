package Threads;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class DateAnimation extends JInternalFrame  {
    boolean isRunning = true;

    PaintPanel pp;
    Double maxX,maxY;
    public DateAnimation(Double maxX,Double maxY) {
        super("",false,false,false,false);
        this.setBorder(null);
        this.setVisible(true);
        this.maxY=maxY;
        this.maxX=maxX;
        pp = new PaintPanel();
        pp.setPreferredSize(new Dimension(900, 600)); // fill the window
        this.setLayout(new BorderLayout());
        this.add(pp);
        Animation anim = new Animation();
        anim.start();

    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    class Animation extends Thread {
        @Override
        public void run() {
            maxY -= 43.2;
            maxX -= 36*0.6*28;
            while (isRunning) {


                    pp.x = (int) (Math.random()*maxX);
                    pp.y = (int) (Math.random() * maxY);
                    pp.repaint();


                try {
                    Thread.sleep(1000); // adjust speed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    e.printStackTrace();
                }
            }
        }
    }
}

class PaintPanel extends JPanel {
    int x, y;

    PaintPanel() {
        this.setBackground(Color.BLUE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.setFont(new Font("sans-serif", Font.BOLD, 36));
        System.out.println(new Date().toString().length());
        g.drawString(new Date().toString(), x, y);

    }
}