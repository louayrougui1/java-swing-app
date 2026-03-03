package Threads;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class FrameAnimation extends JFrame {
    int x = 10;
    int x_initiale = 10;
    int x_finale = x_initiale;

    int y = 220;
    int y_initiale = 220;
    int y_finale = y_initiale;

    double a,b;
    boolean isRunning = false;
    PanelAnimation pa;
    JButton btnStart, btnStop;

    FrameAnimation() {
        setTitle("Animation");
        setSize(900, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnStop = new JButton("Stop");
        btnStart = new JButton("Start");

        JPanel pn = new JPanel();
        pn.setBackground(Color.red);
        pn.add(btnStart);
        pn.add(btnStop);
        this.add(pn, BorderLayout.NORTH);
        pa = new PanelAnimation();
        this.add(pa);

        Animation anim = new Animation();
        anim.start();
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRunning = true;
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRunning = false;
            }
        });
        pa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                x_finale=e.getX();
                y_finale=e.getY();
                Graphics g=pa.getGraphics();
                g.drawRect(x_finale,y_finale,150,150);
                a=(y_finale-y_initiale)/(double)(x_finale-x_initiale);
                b=y_finale-a*x_finale;
            }
        });

    }


    class PanelAnimation extends JPanel {
        PanelAnimation() {
            this.setBackground(Color.cyan);

        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.blue);
            g.fillOval(x, y, 120, 120);
            g.setColor(Color.white);
            g.drawString(new Date().toLocaleString(), x, y);
        }
    }

    class Animation extends Thread {
        @Override
        public void run() {
            int pas = 10;
            while (true) {
                System.out.println(isRunning);
                while (isRunning && x < getWidth() - 120 &&   x > x_initiale) {
                    x += pas;
                    y=(int) (a*x+b);
                    pa.repaint();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (x >=x_finale || x <= x_initiale) {
                    pas = 10;
                    x += pas;

                }
                if (x >= getWidth() - 120) {
                    pas *= -1;
                    x += pas;
                }
            }
        }
    }
    public static void main(String[] args) {
        FrameAnimation fa = new FrameAnimation();
        fa.setVisible(true);
    }
}
