package IHM;

import Adapter.EtudiantTableModel;
import DataBase.EtudiantImplementation;
import Model.DataProfile;
import Model.Profile;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class GestionEtudiant extends JFrame {
    JTable jt;
    JTextField tcin, tprenom, tnom, tmoy, tsearch;
    JButton btn_save;

    EtudiantTableModel model;

    GestionEtudiant() {
        setTitle("Gestion Etudiants:");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //requete
        String requette = "Select * from etudiant";
        EtudiantImplementation implementation = new EtudiantImplementation();
        ResultSet rs = implementation.selectEtudiant(requette);
        model = new EtudiantTableModel(rs, implementation);


        jt = new JTable();
        jt.setModel(model);
        //creation formulaire
        JPanel jp = new JPanel();
        JLabel lcin = new JLabel("CIN");
        JLabel lnom = new JLabel("nom");
        JLabel lprenom = new JLabel("prenom");
        JLabel lmoy = new JLabel("moyenne");
        tcin = new JTextField(10);
        tnom = new JTextField(10);
        tprenom = new JTextField(10);
        tmoy = new JTextField(10);
        btn_save = new JButton("Save");
        jp.add(lcin);
        jp.add(tcin);
        jp.add(lnom);
        jp.add(tnom);
        jp.add(lprenom);
        jp.add(tprenom);
        jp.add(lmoy);
        jp.add(tmoy);
        jp.add(btn_save);
        btn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.ajouterEtudiant(Integer.parseInt(tcin.getText()), tnom.getText(), tprenom.getText(), Double.parseDouble(tmoy.getText()));
            }
        });
        //textfield Search
        JPanel jpSouth = new JPanel();
        tsearch = new JTextField(20);
        //search logic
        tsearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                model.filterUpdate(tsearch.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                model.filterUpdate(tsearch.getText());

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                model.filterUpdate(tsearch.getText());

            }
        });
        //ajout dans une label
        JLabel lsearch = new JLabel("Search:");
        jpSouth.add(lsearch);
        jpSouth.add(tsearch);
        //ajout suppression
        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int row = jt.rowAtPoint(e.getPoint());
                    jt.setRowSelectionInterval(row, row);
                    JPopupMenu popUp = new JPopupMenu();
                    JMenuItem supprimer = new JMenuItem("Supprimer");
                    supprimer.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int index = jt.getSelectedRow();
                            int cin = (int) model.getValueAt(index, 0);
                            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete student with CIN: " + cin + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {
                                model.supprimerEtudiant(cin);
                            }
                        }
                    });
                    popUp.add(supprimer);
                    popUp.show(jt, e.getX(), e.getY());
                }
            }
        });
        //ajout dans le window
        this.add(new JScrollPane(jt), BorderLayout.CENTER);
        this.add(jp, BorderLayout.NORTH);
        this.add(jpSouth, BorderLayout.SOUTH);

    }


    public static void main(String[] args) {
        GestionEtudiant ge = new GestionEtudiant();
        ge.setVisible(true);
    }

}
