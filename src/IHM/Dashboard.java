package IHM;

import Threads.DateAnimation;
import Threads.FrameAnimation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class Dashboard extends JFrame {
    static DateAnimation animation;
    JMenuBar menuBar;
    JMenu menuTp1, menuTp2,menuTp3;
    JMenuItem itemFlow, itemGrid, itemBorder, itemCv, itemGestionProfile,itemGestionEtudiant;
    JDesktopPane desktop;

    public Dashboard() {
        //creation du dashboard
        this.setTitle("Dashboard");
        this.setSize(1050, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        itemFlow = new JMenuItem("Flow");
        itemGrid = new JMenuItem("Grid");
        itemBorder = new JMenuItem("Border");
        itemCv = new JMenuItem("Curriculumn Vitae");
        itemGestionProfile = new JMenuItem("Gestion Profile");
        itemGestionEtudiant = new JMenuItem("Gestion Etudiant");
        menuTp1 = new JMenu("Tp1");
        menuTp2 = new JMenu("Tp2");
        menuTp3 = new JMenu("Tp3");
        menuTp1.add(itemFlow);
        menuTp1.add(itemGrid);
        menuTp1.add(itemBorder);
        menuTp2.add(itemCv);
        menuTp2.add(itemGestionProfile);
        menuTp3.add(itemGestionEtudiant);
        menuBar = new JMenuBar();
        menuBar.add(menuTp1);
        menuBar.add(menuTp2);
        menuBar.add(menuTp3);
        this.setJMenuBar(menuBar);
        //ajout events
        itemFlow.addActionListener(new EcouteurMenu());
        itemBorder.addActionListener(new EcouteurMenu());
        itemGrid.addActionListener(new EcouteurMenu());
        itemCv.addActionListener(new EcouteurMenu());
        itemGestionProfile.addActionListener(new EcouteurMenu());
        itemGestionEtudiant.addActionListener(new EcouteurMenu());

        desktop= new JDesktopPane();
        Dimension size = this.getContentPane().getSize();
        animation = new DateAnimation(Double.valueOf(size.width),Double.valueOf(size.height));

        animation.setBounds(0, 0, size.width, size.height);
//        System.out.println("Width: "+desktop.getWidth()+"\nHeight: "+desktop.getHeight());
        desktop.add(animation);

        this.add(desktop);
        desktop.repaint();
        }



    class EcouteurMenu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            animation.setRunning(false);
            desktop.removeAll();
            //desktop.revalidate();
            desktop.repaint();
            if(e.getSource()==itemFlow){
                FrameFlow fl = new FrameFlow();
                fl.setVisible(true);
                desktop.add(fl);
            }if(e.getSource()==itemGrid){
                Grid gl = new Grid();
                gl.setVisible(true);
                desktop.add(gl);


            }if(e.getSource()==itemBorder){
                Border bl = new Border();
                bl.setVisible(true);
                desktop.add(bl);

            }if(e.getSource()==itemCv){
                CurriculumVitae cv = new CurriculumVitae();
                cv.setVisible(true);
                desktop.add(cv);

            }if(e.getSource()==itemGestionProfile){
                GestionProfile gp = new GestionProfile();
                gp.setVisible(true);
                desktop.add(gp);

            }if(e.getSource()==itemGestionEtudiant){
                GestionEtudiant ge = new GestionEtudiant();
                ge.setVisible(true);
                desktop.add(ge);

            }

        }
    }
    /*lb.addMouseListener(new MouseListener(){
        public void mouseClicked(mouseEvent e){
            ...
        }
    })*/
    /*b.addActionlistener(new ActionListener(){
        public void actionperformer(actionevent e){
        ...
        }
    } OU
    b.addACtionListener(e->
        public void mouseClicked(mouseEvent e){
            ...
        }
    })*/
    public static void main(String[] args) {
        Dashboard dsh = new Dashboard();

    }
}
