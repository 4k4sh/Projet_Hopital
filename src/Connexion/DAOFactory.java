/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;

import java.sql.Connection;

/**
 * Factory qui permet d'encapsuler l'instanciation de nos objets dans une classe
 * @author Thomar Sharif
 */
public class DAOFactory {
    /**
     * Instancie l'objet demandé 
     * @param conn
     * @return 
     */
     public static DAO getChambreDAO(Connection conn)
     {
         return new ChambreDAO(conn);
     }
     
      /**
     * Instancie l'objet demandé 
     * @param conn
     * @return 
     */
     public static DAO getDocteurDAO(Connection conn)
     {
         return new DocteurDAO(conn);
     }
     
      /**
     * Instancie l'objet demandé 
     * @param conn
     * @return 
     */
     public static DAO getEmployeDAO(Connection conn)
     {
         return new EmployeDAO(conn);
     }
      /**
     * Instancie l'objet demandé 
     * @param conn
     * @return 
     */
     public static DAO getHospitalisationDAO(Connection conn)
     {
         return new HospitalisationDAO(conn);
     }
      /**
     * Instancie l'objet demandé 
     * @param conn
     * @return 
     */
     public static DAO getInfirmierDAO(Connection conn)
     {
         return new InfirmierDAO(conn);
     }
      /**
     * Instancie l'objet demandé 
     * @param conn
     * @return 
     */
     public static DAO getMaladeDAO(Connection conn)
     {
         return new MaladeDAO(conn);
     }
      /**
     * Instancie l'objet demandé 
     * @param conn
     * @return 
     */
     public static DAO getServiceDAO(Connection conn)
     {
         return new ServiceDAO(conn);
     }
     
      /**
     * Instancie l'objet demandé, comme d'habitude
     * @param conn
     * @return 
     */
     public static DAO getSoigneDAO(Connection conn)
     {
         return new SoigneDAO(conn);
     }
     
    
}
   

