package Adapter;

import DataBase.EtudiantImplementation;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class EtudiantTableModel extends AbstractTableModel {
    ResultSetMetaData rsmd;
    ArrayList<Object[]> data;
    EtudiantImplementation implementation;

    public EtudiantTableModel(ResultSet rs, EtudiantImplementation implementation) {
        this.implementation = implementation;
        try {
            rsmd = rs.getMetaData();
            data = new ArrayList<>();
            while (rs.next()) {

                Object[] ligne = new Object[rsmd.getColumnCount()];
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    ligne[i] = rs.getObject(i + 1);

                }
                data.add(ligne);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        try {
            return rsmd.getColumnCount();
        } catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        try {
            return rsmd.getColumnName(column + 1);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return getColumnName(columnIndex).equalsIgnoreCase("moyenne");
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        int cin=(int) data.get(rowIndex)[0];
        String nom=(String) data.get(rowIndex)[1];
        String prenom=(String) data.get(rowIndex)[2];
        Double newMoyenne = Double.parseDouble(aValue+"");
        int a=implementation.modifyEudiant(cin,nom,prenom,newMoyenne);
        if(a>0){
        data.get(rowIndex)[columnIndex] = aValue;

        }else {
            JOptionPane.showInputDialog(null,"erreurs de la modification");
        }
    }

    public void ajouterEtudiant(int cin, String nom, String prenom, double moyenne) {
        int x = implementation.insertEtudiant(cin, nom, prenom, moyenne);
        if(x>0){
            Object[] newLigne=new Object[4];
            newLigne[0]=cin;
            newLigne[1]=nom;
            newLigne[2]=prenom;
            newLigne[3]=moyenne;
            data.add(newLigne);
            fireTableDataChanged();
        }
    }
}
