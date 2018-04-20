/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;

import Modele.Hospitalisation;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Thomas (j'avais pas d'idées)
 */
public class HospitalisationDAO extends DAO<Hospitalisation>{

    public HospitalisationDAO(Connection conn) {
        super(conn);
    }
    
    
     @Override
    public Hospitalisation create(Hospitalisation obj) {
        try {
                        PreparedStatement prepare = conn
                        .prepareStatement(
                                "INSERT INTO hospitalisation (no_malade,code_service,no_chambre,lit) VALUES(?,?,?,?)"
                        );
                prepare.setInt(1, obj.getMalade().getIdMalade());
                prepare.setString(2, obj.getServ().getIdService());
                prepare.setInt(3, obj.getChambre().getIdChambre());
                prepare.setInt(4, obj.getLit());
                prepare.executeUpdate();
                obj = this.find(obj.getMalade().getIdMalade());

            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

     @Override
     public Hospitalisation find(int no_docteur, int no_malade){return new Hospitalisation();} 

     @Override
    public Hospitalisation find(String code){return new Hospitalisation();}
    
     @Override
     public Hospitalisation find(int id) {
        Hospitalisation hospi = null;
        try {
           
            
            ResultSet result = conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM hospitalisation WHERE no_malade = " +id
                    );
            if (result.first()) {
                hospi = new Hospitalisation(
                        new MaladeDAO(conn).find(id),
                        new ServiceDAO(conn).find(result.getString("code_service")),
                        new ChambreDAO(conn).find(result.getString("code_service"),result.getInt("no_chambre")),
                        result.getInt("lit")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hospi;
    }
     @Override
     public Hospitalisation find(String code_service, int no_hospitalisation){
    return new Hospitalisation();
    }

    @Override
    public Hospitalisation update(Hospitalisation obj) {
        try {

            conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                            "UPDATE hospitalisation SET code_service = '" + obj.getServ().getIdService()
                                    +"', no_chambre="+obj.getChambre().getIdChambre()
                                    +", lit="+obj.getLit()
                                    + " WHERE no_malade = " + obj.getMalade().getIdMalade());

            obj = this.find(obj.getMalade().getIdMalade());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    @Override
    public void delete(Hospitalisation obj) {
        try {

            conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                            "DELETE FROM hospitalisation WHERE no_malade = " + obj.getMalade().getIdMalade()
                    );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  
    @Override
    public ArrayList<Hospitalisation>getAll(){
      ArrayList<Hospitalisation> hospi = new ArrayList();
        try {
           
            
            ResultSet result = conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM hospitalisation"
                    );
            while(result.next()) {
                hospi.add(new Hospitalisation(
                        new MaladeDAO(conn).find(result.getInt("no_malade")),
                        new ServiceDAO(conn).find(result.getString("code_service")),
                        new ChambreDAO(conn).find(result.getString("code_service"),result.getInt("no_chambre")),
                        result.getInt("lit"))
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hospi;
    }
}
