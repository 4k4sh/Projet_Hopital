/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;

import Modele.Chambre;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Thomzer
 */
public class ChambreDAO extends DAO<Chambre> {

    public ChambreDAO(Connection conn) {
        super(conn);
    }

    // Création d'une chambre
    @Override
    public Chambre create(Chambre obj) {
        try {
            PreparedStatement prepare = conn //Préparation de la requête
                    .prepareStatement(
                            "INSERT INTO chambre (code_service,no_chambre,surveillant,nb_lits) VALUES(?,?,?,?)"
                    );
            // Remplissage des différentes valeurs
            prepare.setString(1, obj.getServ().getIdService()); // premier '?'
            prepare.setInt(2, obj.getIdChambre()); // deuxieme '?'
            prepare.setInt(3, obj.getInf().getEmp().getIdEmploye()); // troisieme '?'
            prepare.setInt(4, obj.getNbLits()); // quatrieme '?'
            prepare.executeUpdate(); //Execution de la requête
            obj = this.find(obj.getServ().getIdService(), obj.getIdChambre());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Chambre find(String codeSoigne) {
        return new Chambre();
    }

    @Override
    public Chambre find(int idSoigne) {
        return new Chambre();
    }

    @Override
    public Chambre find(int no_docteur, int no_maladeSoigne) {
        return new Chambre();
    }

    @Override
    public Chambre find(String code_service, int no_chambre) {
        Chambre ch = null;
        try {

            ResultSet result = conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM chambre WHERE code_service = '" + code_service
                            + "' AND no_chambre=" + no_chambre
                    );
            if (result.first()) { //Si on trouve une correspondance
                ch = new Chambre(
                        result.getInt("no_chambre"),
                        new ServiceDAO(conn).find(result.getString("code_service")),
                        new InfirmierDAO(conn).find(result.getInt("surveillant")),
                        result.getInt("nb_lits")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ch;
    }

    @Override
    public Chambre update(Chambre obj) {
        try {
// On update tout sauf la/les clés
            conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                            "UPDATE chambre SET surveillant = " + obj.getInf().getEmp().getIdEmploye()
                            + ", nb_lits=" + obj.getNbLits()
                            + " WHERE code_service ='" + obj.getServ().getIdService()
                            + "' AND no_chambre=" + obj.getIdChambre());

            obj = this.find(obj.getServ().getIdService(), obj.getIdChambre());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    @Override
    public void delete(Chambre obj) {
        try {

            conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeUpdate(
                    "DELETE FROM chambre WHERE code_service = '" + obj.getServ().getIdService()
                    + "' AND no_chambre=" + obj.getIdChambre()
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public ArrayList<Chambre> getAll()
    {
    ArrayList<Chambre> ch = new ArrayList();
    
        try {

            ResultSet result = conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM chambre;");
            while(result.next()) { //si on trouve une correspondance
                ch.add(new Chambre(
                        result.getInt("no_chambre"),
                        new ServiceDAO(conn).find(result.getString("code_service")),
                        new InfirmierDAO(conn).find(result.getInt("surveillant")),
                        result.getInt("nb_lits")
                ));
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ch;
    }



}

