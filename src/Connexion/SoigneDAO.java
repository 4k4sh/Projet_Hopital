/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;


import Modèle.Soigne;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author UltralabÃ¹
 * Les tests concernant la date de rendez-vous ne sont pas concluants
 */
public class SoigneDAO extends DAO<Soigne>{

    public SoigneDAO(Connection conn) {
        super(conn);
    }
    
    
    @Override
    public Soigne create(Soigne obj) {
        try {
                        PreparedStatement prepare = conn
                        .prepareStatement(
                                "INSERT INTO soigne (no_docteur,no_malade,jour,heure) VALUES(?,?,?,?)"
                        );
                prepare.setInt(1, obj.getIdDocteur().getEmp().getIdEmploye());
                prepare.setInt(2, obj.getIdMalade().getIdMalade());
                //prepare.setString(3, obj.getJour());
                //prepare.setString(4, obj.getHeure());
                prepare.executeUpdate();
                obj = this.find(obj.getIdDocteur().getEmp().getIdEmploye(),obj.getIdMalade().getIdMalade());

            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public Soigne find(int no_docteur, int no_malade){
        Soigne ch = null;
        try {
           
            
            ResultSet result = conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM soigne WHERE no_docteur = "+ no_docteur
                            +" AND no_malade="+ no_malade
                    );
            if (result.first()) {
                ch = new Soigne(
                        new DocteurDAO(conn).find(no_docteur),
                        new MaladeDAO(conn).find(no_malade)
                        //result.getString("jour"),
                        //result.getString("heure")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ch;
    } 

     @Override
    public Soigne find(String code){return new Soigne();}
    
     @Override
     public Soigne find(int id) {
        return new Soigne();
    }
     @Override
     public Soigne find(String code_soigne, int no_soigne){
        return new Soigne();
    }

    @Override
    public Soigne update(Soigne obj) {
        try {

            conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                            
                            "UPDATE soigne SET no_malade = '" + obj.getIdMalade().getIdMalade()
                                    + "' WHERE no_docteur = " + obj.getIdDocteur().getEmp().getIdEmploye());
                            
                            /*"UPDATE soigne SET jour = '" + obj.getJour()
                                    +"', heure='"+obj.getHeure()
                                    + "' WHERE no_docteur = " + obj.getIdDocteur().getEmp().getIdEmploye()
                            +" AND no_malade="+ obj.getIdMalade().getIdMalade());*/

            obj = this.find(obj.getIdDocteur().getEmp().getIdEmploye(),obj.getIdMalade().getIdMalade());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    @Override
    public void delete(Soigne obj) {
        try {

            conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                            "DELETE FROM soigne WHERE no_docteur = " + obj.getIdDocteur().getEmp().getIdEmploye()
                            +" AND no_malade="+ obj.getIdMalade().getIdMalade()
                    );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public ArrayList<Soigne> getAll(){
        ArrayList<Soigne> ch = new ArrayList();
        try {
           
            
            ResultSet result = conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM soigne"
                    );
            while(result.next()) {
                ch.add( new Soigne(
                        new DocteurDAO(conn).find(result.getInt("no_docteur")),
                        new MaladeDAO(conn).find(result.getInt("no_malade")))
                        //result.getString("jour"),
                        //result.getString("heure"))
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ch;
    }
}
