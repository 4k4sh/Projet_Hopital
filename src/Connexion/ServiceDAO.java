/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;


import Modèle.Service;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Thomargoulin
 */
public class ServiceDAO extends DAO<Service>{

    public ServiceDAO(Connection conn) {
        super(conn);
    }
    
    @Override
    public Service create(Service obj) {
        try {
            
             // Il faut un directeur pour crÃ©er un service
            if(obj.getDirecteur().getEmp().getIdEmploye()==0){
			//Sans directeur, pas de service	
			}
            
                           PreparedStatement prepare = conn
                        .prepareStatement(
                                "INSERT INTO service (code,nom,batiment,directeur) VALUES(?,?,?,?)"
                        );
                prepare.setString(1, obj.getIdService());
                prepare.setString(2, obj.getNomService());
                prepare.setString(3, Character.toString(obj.getBatiment()));
                prepare.setInt(4, obj.getDirecteur().getEmp().getIdEmploye());
                prepare.executeUpdate();
                obj = this.find(obj.getIdService());

            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

     @Override
    public Service find(int no_docteur, int no_malade){return new Service();} 

     @Override
    public Service find(String code_service, int no_chambre){
    return new Service();
    }
     @Override
    public Service find(int id){
    return new Service();
    }
    @Override
    public Service find(String code) {
        Service serv = null;
        try {
           
            
            ResultSet result = conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM service WHERE code = '" + code+"'"
                    );
            if (result.first()) {
                serv = new Service(code,
                        result.getString("nom"),
                        result.getString("batiment").charAt(0),
                        new DocteurDAO(conn).find(result.getInt("directeur"))
                        
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serv;

    }

    @Override
    public Service update(Service obj) {
        try {

            conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                            "UPDATE service SET nom = '" + obj.getNomService()
                                    +"', batiment = '" + obj.getBatiment()
                                    +"', directeur = '" + obj.getDirecteur().getEmp().getIdEmploye()
                                    + "' WHERE  code = '" + obj.getIdService()+"'");

            obj = this.find(obj.getIdService());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    @Override
    public void delete(Service obj) {
        try {

            conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                            "DELETE FROM service WHERE code = '" + obj.getIdService()+"'"
                    );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Service> getAll(){
      ArrayList<Service> serv = new ArrayList();
        try {
           
            
            ResultSet result = conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM service"
                    );
            while(result.next()) {
                serv.add( new Service(result.getString("code"),
                        result.getString("nom"),
                        result.getString("batiment").charAt(0),
                        new DocteurDAO(conn).find(result.getInt("directeur")))
                        
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serv;

    }
    
}

