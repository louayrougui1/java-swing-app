package DataBase;

import java.sql.*;

public class Test {
    public static void main(String[] args) {
        try {
            Class.forName(Config.nomDriver);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Erreur Driver" + cnfe.getMessage());
        }
        EtudiantImplementation etu = new EtudiantImplementation();
        // Insert a student
        int x = etu.insertEtudiant(12345, "Salem", "karim", 10.83);
        System.out.println("Rows inserted: " + x);
        //List<Profile> Profiles = etu.selectEtudiant("SELECT * FROM Etudiant");
        // Optional: Select students
        ResultSet rs = etu.selectEtudiant("SELECT * FROM Etudiant");
        etu.afficherResultSet(rs);
        x = etu.modifyEudiant(12345, "loulou", "rougui", 18);
        System.out.println("Rows updated: " + x);
        rs = etu.selectEtudiant("SELECT * FROM Etudiant");
        etu.afficherResultSet(rs);
        x = etu.deleteEudiant(12345);
        rs = etu.selectEtudiant("SELECT * FROM Etudiant");
        etu.afficherResultSet(rs);


    }


}