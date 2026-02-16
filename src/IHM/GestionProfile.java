package IHM;

import Model.DataProfile;
import Model.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GestionProfile extends JInternalFrame {
    JLabel lbNom, lbPrenom, lbPseudo, lbHelp;
    JTextField tfNom, tfPrenom, tfPseudo;
    JPanel pn;
    JButton btnSave;
    JSplitPane jsp;
    JList<String> liste;
    DefaultListModel<String> model;
    JTabbedPane jtb;

    public GestionProfile() {
        this.setSize(1000, 700);

        this.setLayout(new BorderLayout());
        this.setTitle("Gestion Profile");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setResizable(true);
        //creation partie north
        lbNom = new JLabel("Nom: ");
        tfNom = new JTextField(15);
        lbPrenom = new JLabel("Prenom: ");
        tfPrenom = new JTextField(15);
        lbPseudo = new JLabel("Pseudo: ");
        tfPseudo = new JTextField(15);
        btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = tfNom.getText();
                String prenom = tfPrenom.getText();
                String pseudo = tfPseudo.getText();
                Profile prof = new Profile(nom, prenom, pseudo);
                DataProfile.addProfile(prof);
                tfPseudo.setText("saisir pseudo");
                tfNom.setText("saisir nom");
                tfPrenom.setText("saisir prenom");
                profileDashboardUpdate();
            }
        });
        //creation panel
        pn = new JPanel();
        pn.setLayout(new FlowLayout());
        pn.add(lbNom);
        pn.add(tfNom);
        pn.add(lbPrenom);
        pn.add(tfPrenom);
        pn.add(lbPseudo);
        pn.add(tfPseudo);
        pn.add(btnSave);

        this.add(pn, BorderLayout.NORTH);
        //Partie driote
        jtb = new JTabbedPane();

        //partie gauche
        model = new DefaultListModel<>();

        liste = new JList<>();
        liste.setModel(model);

        liste.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) {
                    int index = liste.getSelectedIndex();
                    Profile user = DataProfile.getProfile(index);
                    String pseudo = user.getPseudo();
                    index= jtb.indexOfTab(pseudo);
                    String ps = liste.getSelectedValue();
                    if (index==-1) {
                        FormPanel form = new FormPanel(ps);
                        jtb.addTab(ps, form);
                    }
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    MyPopUpPanel popUp = new MyPopUpPanel(GestionProfile.this);
                    popUp.show(liste, e.getX(), e.getY());
                }
            }
        });

        //Creation partie Centre
        jsp = new JSplitPane();
        jsp.setLeftComponent(liste);
        jsp.setRightComponent(jtb);
        this.add(jsp);
        //Creation partie South
        lbHelp = new JLabel("Help");
        this.add(lbHelp, BorderLayout.SOUTH);

        //partie mouse listener
        lbNom.addMouseListener(new EcouteurLabel(this));
        lbPrenom.addMouseListener(new EcouteurLabel(this));
        lbPseudo.addMouseListener(new EcouteurLabel(this));

        tfNom.addMouseListener(new EcouteurTextField(this));
        tfPrenom.addMouseListener(new EcouteurTextField(this));
        tfPseudo.addMouseListener(new EcouteurTextField(this));

        tfNom.addFocusListener(new PlaceHolder(this));
        tfPrenom.addFocusListener(new PlaceHolder(this));
        tfPseudo.addFocusListener(new PlaceHolder(this));

    }

    public void profileDashboardUpdate() {
        model.clear();
        ArrayList<Profile> allProfiles = DataProfile.getData();

        for (Profile p : allProfiles) {
            model.addElement(p.getPseudo());
        }
    }


}
