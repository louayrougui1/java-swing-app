package IHM;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PlaceHolder extends FocusAdapter {
    GestionProfile fen;

    public PlaceHolder(GestionProfile fen) {
        this.fen = fen;
        this.fen.tfNom.setText("saisir nom");
        this.fen.tfPrenom.setText("saisir prenom");
        this.fen.tfPseudo.setText("saisir pseudo");
    }

    @Override
    public void focusGained(FocusEvent e) {
        super.focusGained(e);
        if (e.getSource() == this.fen.tfNom) {
            if (this.fen.tfNom.getText().equals("saisir nom")) {

                this.fen.tfNom.setText("");
            }
        }
        if (e.getSource() == this.fen.tfPrenom) {
            if (this.fen.tfPrenom.getText().equals("saisir prenom")) {

                this.fen.tfPrenom.setText("");
            }
        }
        if (e.getSource() == this.fen.tfPseudo) {
            if (this.fen.tfPseudo.getText().equals("saisir pseudo")) {

                this.fen.tfPseudo.setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        super.focusLost(e);
        if (e.getSource() == this.fen.tfNom) {
            if (this.fen.tfNom.getText().isEmpty()) {

                this.fen.tfNom.setText("saisir nom");
            }
        }
        if (e.getSource() == this.fen.tfPrenom) {
            if (this.fen.tfPrenom.getText().isEmpty()) {

                this.fen.tfPrenom.setText("saisir prenom");
            }
        }
        if (e.getSource() == this.fen.tfPseudo) {
            if (this.fen.tfPseudo.getText().isEmpty()) {

                this.fen.tfPseudo.setText("saisir pseudo");
            }
        }
    }
}

