/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;

import Modele.Employe;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Thomage du Fromage
 */
public class EmployeDAO extends DAO<Employe>{

    public EmployeDAO(Connection conn) {
        super(conn);
    }
    
    
    @Override
    public Employe create(Employe obj) {
        try {
          
                PreparedStatement prepare = conn
                        .prepareStatement(
                                "INSERT INTO employe (numero,nom,prenom,tel,adresse) VALUES(?,?,?,?,?)"
                        );
                prepare.setInt(1, obj.getIdEmploye());
                prepare.setString(2, obj.getNom());
                prepare.setString(3, obj.getPrenom());
                prepare.setString(4, obj.getTel());
                prepare.setString(5, obj.getAdresse());
                prepare.executeUpdate();
                obj = this.find(obj.getIdEmploye());

            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
        public Employe find(int no_docteur, int no_malade){return new Employe();} 

    @Override
    public Employe find(String code_service, int no_chambre){
    return new Employe();
    }
    @Override
    public Employe find(String code){return new Employe();}
    @Override
    public Employe find(int id) {
        Employe emp = null;
        try {
            ResultSet result = conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM employe WHERE numero = " + id
                    );
            if (result.first()) {
                emp = new Employe(
                        id,
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("tel"),
                        result.getString("adresse")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;

    }

    @Override
    public Employe update(Employe obj) {
        try {

            conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                            "UPDATE employe SET nom = '" + obj.getNom() + "',"
                                    + " prenom = '" + obj.getPrenom() + "',"
                                    + "tel = '" + obj.getTel() +
                                    "',adresse = '" + obj.getAdresse() +
                                    "' WHERE numero = " + obj.getIdEmploye());

            obj = this.find(obj.getIdEmploye());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    @Override
    public void delete(Employe obj) {
        try {

            conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                            "DELETE FROM employe WHERE numero = " + obj.getIdEmploye()
                    );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public ArrayList<Employe> getAll(){
        ArrayList<Employe> emp = new ArrayList(); 
        try {
            ResultSet result = conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM employe");
            while (result.next()) {
                emp.add(new Employe(
                        result.getInt("numero"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("tel"),
                        result.getString("adresse"))
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;
}
    
}
