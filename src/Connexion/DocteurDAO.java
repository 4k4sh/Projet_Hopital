/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;

import Modele.Docteur;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author DocThom
 */
public class DocteurDAO extends DAO<Docteur>{

    public DocteurDAO(Connection conn) {
        super(conn);
    }
    
     @Override
    public Docteur create(Docteur obj) {
        try {
            
             // Si l'employé n'existe pas, situation de conflit
            if(obj.getEmp().getIdEmploye()==0){
				
			}
            
                           PreparedStatement prepare = conn
                        .prepareStatement(
                                "INSERT INTO docteur (numero,specialite) VALUES(?,?)"
                        );
                prepare.setInt(1, obj.getEmp().getIdEmploye());
                prepare.setString(2, obj.getSpe());
                prepare.executeUpdate();
                obj = this.find(obj.getEmp().getIdEmploye());

            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Docteur find(int no_docteur, int no_malade){return new Docteur();} 
     @Override
    public Docteur find(String code_service, int no_chambre){
    return new Docteur();
    }
     @Override
    public Docteur find(String code){return new Docteur();}
    @Override
    public Docteur find(int id) {
        Docteur doc = null;
        try {
           
            
            ResultSet result = conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM docteur WHERE numero = " + id
                    );
            if (result.first()) {
                doc = new Docteur(
                        new EmployeDAO(conn).find(id),
                        result.getString("specialite")
                );
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doc;

    }

    @Override
    public Docteur update(Docteur obj) {
        try {

            conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                            "UPDATE docteur SET specialite = '" + obj.getSpe()
                                    + "'WHERE  numero = " + obj.getEmp().getIdEmploye());

            obj = this.find(obj.getEmp().getIdEmploye());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    @Override
    public void delete(Docteur obj) {
        try {

            conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                            "DELETE FROM docteur WHERE numero = " + obj.getEmp().getIdEmploye()
                    );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Docteur> getAll(){
        
        ArrayList<Docteur> doc = new ArrayList();
        try {
           
            
            ResultSet result = conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM docteur");
            while(result.next()) {
                doc.add( new Docteur(
                        new EmployeDAO(conn).find(result.getInt("numero")),
                        result.getString("specialite"))    );
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doc;
    
    }
}

