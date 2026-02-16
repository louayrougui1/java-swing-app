package IHM;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EcouteurTextField extends MouseAdapter {
    GestionProfile fen;

    public EcouteurTextField(GestionProfile fen) {
        this.fen = fen;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        if (e.getSource() == this.fen.tfNom) {
            this.fen.lbHelp.setText("HELP: Veuiller entrer Nom");
        }
        if (e.getSource() == this.fen.tfPrenom) {
            this.fen.lbHelp.setText("HELP: Veuiller entrer Prenom");
        }
        if (e.getSource() == this.fen.tfPseudo) {
            this.fen.lbHelp.setText("HELP: Veuiller entrer Pseudo");
        }
    }

    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        this.fen.lbHelp.setText("Help");
    }
}
