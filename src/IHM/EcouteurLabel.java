package IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class EcouteurLabel extends MouseAdapter {
    GestionProfile fen;

    public EcouteurLabel(GestionProfile fen) {
        this.fen = fen;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        if (e.getSource() == this.fen.lbNom) {
            this.fen.lbNom.setForeground(Color.CYAN);
        }
        if (e.getSource() == this.fen.lbPrenom) {
            this.fen.lbPrenom.setForeground(Color.CYAN);
        }
        if (e.getSource() == this.fen.lbPseudo) {
            this.fen.lbPseudo.setForeground(Color.CYAN);
        }
    }
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        if (e.getSource() == this.fen.lbNom) {
            this.fen.lbNom.setForeground(Color.BLACK);
        }
        if (e.getSource() == this.fen.lbPrenom) {
            this.fen.lbPrenom.setForeground(Color.BLACK);
        }
        if (e.getSource() == this.fen.lbPseudo) {
            this.fen.lbPseudo.setForeground(Color.BLACK);
        }
    }
}
