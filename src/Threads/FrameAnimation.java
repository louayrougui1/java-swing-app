package Threads;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class FrameAnimation extends JFrame {
    PanelAnimation pa;
    JButton btn_start, btn_stop;
    boolean is_running = false;
    double a, b;

    FrameAnimation(){
        setTitle("Animation");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btn_start = new JButton("Start");
        btn_stop = new JButton("Stop");

        JPanel pn = new JPanel();
        pn.setBackground(Color.gray);
        pn.add(btn_start);
        pn.add(btn_stop);
        this.add(pn,BorderLayout.NORTH);

        pa = new PanelAnimation();
        this.add(pa);

        Animation anim = new Animation();
        anim.start();

        btn_start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                is_running = true;
            }

        });

        btn_stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                is_running = false;
            }
        });
        pa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pa.x_final = e.getX();
                pa.y_final = e.getY();

                Graphics g = pa.getGraphics();
                g.setColor(Color.white);
                g.drawRect(pa.x_final, pa.y_final, 150, 150);

                a = (pa.y_final - pa.y_init)/(double) (pa.x_final - pa.x_init);
                b = pa.y_final - a * pa.x_final;
            }
        });
    }
    class Animation extends Thread {
        int pas = 10;
        @Override
        public void run() {
            while (true){
                System.out.println(is_running);
                while (is_running && pa.x < pa.x_final && pa.x >= pa.x_init ) {
                    pa.x += pas;
                    pa.y = (int)(a*pa.x + b);
                    pa.repaint();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (pa.x >=pa.x_final|| pa.x < pa.x_init){
                    pas*=-1;
                    pa.x+=pas;
                }
            }
        }
    }

}

class PanelAnimation extends JPanel{
    int x = 10;
    int x_init = 10;
    int x_final = x_init;

    int y = 220;
    int y_init = 220;
    int y_final = y_init;

    PanelAnimation(){
        this.setBackground(Color.CYAN);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.blue);
        g.fillOval(x,y,120,120);
        g.setColor(Color.white);
        g.drawString(new Date().toLocaleString(), x, y);
    }

    public static void main(String[] args) {
        FrameAnimation f = new FrameAnimation();
        f.setVisible(true);
    }
}
