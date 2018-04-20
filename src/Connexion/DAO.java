/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Classe Abstraite gérant le pattern DAO
 * qui relie la BDD SQL au modèle Java
 * @author Thomislav Baritoslav
 * @param <T> 
 */

public abstract class DAO<T> {

    protected Connection conn = null;

    /**
     * Contructeur de la classe DAO
     * @param conn 
     */
    public DAO(Connection conn) {
        this.conn = conn;
    }   
    
    /**
     * Permet de récupérer un objet via son ID
     *
     * @param id
     * @return obj
     */
    public abstract T find(int id);

    /**
     * Permet de recuper un obj avec id en String
     *
     * @param code
     * @return obj
     */
    public abstract T find(String code);

    /**
     * Permet de recuperer un objet avec son id en String et int
     *
     * @param code_service
     * @param no_chambre
      * @return obj
     */
    public abstract T find(String code_service, int no_chambre);

    /**
     * Permet de recuperer un objet avec son id en deux int
     *
     * @param no_docteur
     * @param no_malade
      * @return obj
     */
    public abstract T find(int no_docteur, int no_malade);

    /**
     * Permet de créer une entrée dans la base de données par rapport à un objet
     *
     * @param obj
     * @return obj
     */
    public abstract T create(T obj);

    /**
     * Permet de mettre à jour les données d'une entrée dans la base
     *
     * @param obj
     * @return obj
     */
    public abstract T update(T obj);

    /**
     * Permet la suppression d'une entrée de la base
     *
     * @param obj
     */
    public abstract void delete(T obj);
    
    public abstract ArrayList<T> getAll();

}

