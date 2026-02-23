package IHM;

import Adapter.EtudiantTableModel;
import DataBase.EtudiantImplementation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class GestionEtudiant extends JFrame {
    JTable jt;
    JTextField tcin,tprenom,tnom,tmoy;
    EtudiantTableModel model;
    GestionEtudiant (){
        setTitle("Gestion Etudiants:");
        setSize(900,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //requete
        String requette="Select * from etudiant";
        EtudiantImplementation implementation=new EtudiantImplementation();
        ResultSet rs=implementation.selectEtudiant(requette);
        model = new EtudiantTableModel(rs,implementation);


        jt=new JTable();
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
        JButton btn_save = new JButton("Save");
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
                model.ajouterEtudiant(Integer.parseInt(tcin.getText()),tnom.getText(),tprenom.getText(),Double.parseDouble(tmoy.getText()));
            }
        });
        this.add(new JScrollPane(jt), BorderLayout.CENTER);
        this.add(jp,BorderLayout.NORTH);

    }




    public static void main(String[] args) {
        GestionEtudiant ge= new GestionEtudiant();
        ge.setVisible(true);
    }

}
