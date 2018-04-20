/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;

import <any?>;
import Modele.Infirmier;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class InfirmierDAO extends DAO<Infirmier>{

    public InfirmierDAO(Connection conn) {
        super(conn);
    }
    
    @Override
    public Infirmier create(Infirmier obj) {
        try {
            
                        
                           PreparedStatement prepare = conn
                        .prepareStatement(
                                "INSERT INTO infirmier (numero,code_service,rotation,salaire) VALUES(?,?,?,?)"
                        );
                prepare.setInt(1, obj.getEmp().getIdEmploye());
                prepare.setString(2, obj.getServ().getIdService());
                prepare.setString(3, obj.getRotation());
                prepare.setDouble(4, obj.getSalaire());
                prepare.executeUpdate();
                obj = this.find(obj.getEmp().getIdEmploye());

            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

        public Infirmier find(int no_docteur, int no_malade){return new Infirmier();} 

    @Override
    public Infirmier find(String code_service, int no_chambre){
    return new Infirmier();
    }
     @Override
    public Infirmier find(String code){return new Infirmier();}
    @Override
    public Infirmier find(int id) {
        Infirmier inf = null;
        try {
           
            
            ResultSet result = conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM infirmier WHERE numero = " + id
                    );
            if (result.first()) {
                       inf = new Infirmier(
                        new EmployeDAO(conn).find(id),
                        new ServiceDAO(conn).find(result.getString("code_service")),
                        result.getString("rotation"),
                        result.getDouble("salaire")
                );
                                  }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inf;

    }

    @Override
    public Infirmier update(Infirmier obj) {
        try {

            conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                            "UPDATE infirmier SET code_service= '" + obj.getServ().getIdService()
                                    + "', rotation = '" + obj.getRotation()
                                    + "',salaire= " + obj.getSalaire()
                                   + "WHERE numero = " + obj.getEmp().getIdEmploye());

            obj = this.find(obj.getEmp().getIdEmploye());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    @Override
    public void delete(Infirmier obj) {
        try {

            conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                            "DELETE FROM infirmier WHERE numero = " + obj.getEmp().getIdEmploye()
                    );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public ArrayList<Infirmier> getAll(){
    ArrayList<Infirmier> inf = new ArrayList();
     try {
           
            
            ResultSet result = conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM infirmier"
                    );
            while(result.next()) {
                       inf.add(new Infirmier(
                        new EmployeDAO(conn).find(result.getInt("numero")),
                        new ServiceDAO(conn).find(result.getString("code_service")),
                        result.getString("rotation"),
                        result.getDouble("salaire"))
                );
                                  }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inf;
            }
    
}
