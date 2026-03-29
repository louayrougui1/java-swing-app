package Threads;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class DateAnimation extends JPanel {
    boolean isRunning = true;

    PaintPanel pp;

    public DateAnimation() {
        pp = new PaintPanel();
        pp.setPreferredSize(new Dimension(900, 600)); // fill the window
        this.setLayout(new BorderLayout()); // optional but recommended
        this.add(pp, BorderLayout.CENTER);
        Animation anim = new Animation();
        anim.start();

    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    class Animation extends Thread {
        @Override
        public void run() {
            while (isRunning) {
                String date = new Date().toString();
                int maxX = getWidth() - 300; // approximate text width
                int maxY = getHeight() - 300; // approximate text height
                if (maxX > 0 && maxY > 0) {
                    pp.x = (int) (Math.random() * maxX);
                    pp.y = (int) (Math.random() * maxY);
                    pp.repaint();

                }
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
        this.setBackground(Color.darkGray);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString(new Date().toLocaleString(), x, y);

    }
}