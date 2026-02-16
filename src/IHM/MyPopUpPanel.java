package IHM;

import Model.DataProfile;
import Model.Profile;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class MyPopUpPanel extends JPopupMenu {
    GestionProfile fen;

    public MyPopUpPanel(GestionProfile fen) {
        this.fen = fen;
        JMenuItem itemMod = new JMenuItem("Modifier");
        JMenuItem itemSup = new JMenuItem("Supprimer");
        JMenuItem itemSupTout = new JMenuItem("Supprimer Tout");
        itemSupTout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataProfile.resetData();
                fen.profileDashboardUpdate();

            }
        });
        itemSup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = fen.liste.getSelectedIndex();
                Profile user = DataProfile.getProfile(index);
                String pseudo = user.getPseudo();
                DataProfile.delProfile(index);
                index = fen.jtb.indexOfTab(pseudo);
                fen.jtb.remove(index);
                fen.profileDashboardUpdate();

            }
        });
        itemMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = fen.liste.getSelectedIndex();
                Profile user = DataProfile.getProfile(index);
                String new_nom = JOptionPane.showInputDialog("Nouveau Nom:");
                String new_prenom = JOptionPane.showInputDialog("Nouveau Prenom:");
                if (!Objects.equals(new_nom, "")) {
                    user.setNom(new_nom);
                }
                if (!Objects.equals(new_prenom, "")) {
                    user.setPrenom(new_prenom);
                }
                DataProfile.modProfile(user);
                fen.profileDashboardUpdate();
            }
        });

        this.add(itemSupTout);
        this.add(itemSup);
        this.add(itemMod);

    }
}
