/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;
import java.sql.*;
import java.util.*;
import javafx.collections.*;
import javafx.scene.chart.PieChart;
import javax.swing.JOptionPane;


/**
 * Classe gérant la Connexion à la base de donnée
 * @author bonus de Thomton
 */


public class Connexion {

    /**
     * Attributs prives : connexion JDBC, statement, ordre requete et resultat requete
     */
    private Connection conn;       

    /**
     * Constructeur pour une connexion en ligne
     * @param bdd
     * @param loginDatabase
     * @param passwordDatabase
     * @param serveur
     * @param usernameECE
     * @param passwordECE
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    
    // Connexion à la BDD ECE
    // /!\ NE FONCTIONNE PAS
   /* 
    public Connexion( String bdd, String loginDatabase, String passwordDatabase, String serveur, String usernameECE, String passwordECE) throws SQLException, ClassNotFoundException {
        // Chargement driver "com.mysql.jdbc.Driver" nécessaire
        Class.forName("com.mysql.jdbc.Driver");

        // Connexion via le tunnel SSH avec le username et le password ECE
        // /!\ NE FONCTIONNE PAS ! IGNORER
        SSHTunnel ssh = new SSHTunnel(usernameECE, passwordECE, serveur);

        if (ssh.connect()) {

            
            String urlDatabase = "jdbc:mysql://localhost:3305/" + usernameECE;
           //  String urlDatabase = "jdbc:mysql://"+serveur+":3305/"+usernameECE;

            //création d'une connexion JDBC à la base
            conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);

            

        }
    }
    /**
     *  Constructeur pour une connexion en local
     * @param bdd
     * @param loginDatabase
     * @param passwordDatabase
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    
    // Connexion à la BDD locale
     public Connexion( String bdd, String loginDatabase, String passwordDatabase ) throws SQLException, ClassNotFoundException {
        // Chargement driver
        Class.forName("com.mysql.jdbc.Driver");
     
            // URL
            String urlDatabase = "jdbc:mysql://localhost/"+bdd;

            //Creation connexion JDBC
            conn = DriverManager.getConnection(urlDatabase, loginDatabase,passwordDatabase);

            
           
    }

    public Connexion(String text, String text0, String text1, String text2, String text3, String text4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     /**
      * Getter pour la connection
      * @return Connection la connexion
      */
    public Connection getConn() {
        return conn;
    }

     /**
     * Methode qui execute la requete et stocke les données dans un ObservableList
     * @param q requete à  la base de donnee
     * @return ObservableList pour le diagramme
     */
    public ObservableList<PieChart.Data> queryPie(String q)
    {   
        ObservableList<PieChart.Data> items = FXCollections.observableArrayList(); 

                        try {
                        ResultSet result = conn
                                .createStatement(
                                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE
                                ).executeQuery(
                                        q   
                                );
                        ResultSetMetaData resultMeta= result.getMetaData();
                         
                        while (!result.isLast()) {
                           result.next();
                           items.add(new PieChart.Data(result.getString(1)+" : "+result.getString(2), Integer.parseInt(result.getString(2)) ));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
        
        
            return items;
    }   
    
 

    
      /**
     * Methode de requete pour 3 criteres selectionnés (avec ou sans jointure)
     * @param Classe
     * @param lab1
     * @param lab2
     * @param lab3
     * @param txt1
     * @param txt2
     * @param txt3
     * @return Resulset le resultat de la requete
     */
    
    public ResultSet requete3critere(String Classe, String lab1, String lab2, String lab3, String txt1, String txt2, String txt3) {
        String jointure1 = "";
        String jointure2 = "";
        try {
            Integer.parseInt(txt1);
            txt1 = txt1;

        } catch (Exception e) {
            txt1 = isString(txt1,Classe);

        }

        try {
            Integer.parseInt(txt2);
            txt2 = txt2;

        } catch (Exception e) {
            txt2 = isString(txt2,Classe);

        }

        try {
            Integer.parseInt(txt3);
            txt3 = txt3;

        } catch (Exception e) {
            txt3 = isString(txt3,Classe);

        }

        if (Classe.equals("infirmier") || Classe.equals("docteur")) {
            jointure1 = "e.prenom, e.nom, i.";
            Classe = Classe + " i, employe e ";
            jointure2 = " AND e.numero=i.numero";
            lab1 = "i." + lab1;
            lab2 = "i." + lab2;
            lab3 = "i." + lab3;

        }

        if (Classe.equals("hospitalisation")) {
            jointure1 = "e.prenom, e.nom, i.";
            Classe = Classe + " i, malade e";
            jointure2 = " AND e.numero=i.no_malade";
            lab1 = "i." + lab1;
            lab2 = "i." + lab2;
            lab3 = "i." + lab3;
        }

        if (Classe.equals("soigne")) {
            jointure1 = "e.prenom,e.nom,d.prenom,d.nom, i.";
            Classe = Classe + " i, malade e,employe d";
            jointure2 = " AND e.numero=i.no_malade AND i.no_docteur=d.numero";
            lab1 = "i." + lab1;
            lab2 = "i." + lab2;
            lab3 = "i." + lab3;

            }
        ResultSet result = null;
        try {

            result = conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT " + jointure1 + "* FROM " + Classe + " WHERE " + lab1 + " = " + txt1 + jointure2 + " AND " + lab2 + " = " + txt2 + " AND " + lab3 + " = " + txt3
                   
                    );

        } catch (SQLException e) {
           
        }
        return result;
    }
/**
 * Requête 2 critères
 * @param Classe
 * @param lab1
 * @param lab2
 * @param txt1
 * @param txt2
 * @return Resulset le resultat de la requete
 */
    
    public ResultSet requete2critere(String Classe, String lab1, String lab2, String txt1, String txt2) {
        String jointure1 = "";
        String jointure2 = "";
        try {
            Integer.parseInt(txt1);
            txt1 = txt1;

        } catch (Exception e) {
            txt1 = isString(txt1,Classe);

        }

        try {
            Integer.parseInt(txt2);
            txt2 = txt2;

        } catch (Exception e) {
            txt2 = isString(txt2,Classe);

        }

        if (Classe.equals("infirmier") || Classe.equals("docteur")) {
            jointure1 = "e.prenom, e.nom, i.";
            Classe = Classe + " i, employe e ";
            jointure2 = " AND e.numero=i.numero";
            lab1 = "i." + lab1;
            lab2 = "i." + lab2;

        }

        if (Classe.equals("hospitalisation")) {
            jointure1 = "e.prenom, e.nom, i.";
            Classe = Classe + " i, malade e";
            jointure2 = " AND e.numero=i.no_malade";
            lab1 = "i." + lab1;
            lab2 = "i." + lab2;
        }

        if (Classe.equals("soigne")) {
            jointure1 = "e.prenom,e.nom,d.prenom,d.nom, i.";
            Classe = Classe + " i, malade e,employe d";
            jointure2 = " AND e.numero=i.no_malade AND i.no_docteur=d.numero";
            lab1 = "i." + lab1;
            lab2 = "i." + lab2;

            }
        System.out.println("SELECT " + jointure1 + "* FROM " + Classe + " WHERE " + lab1 + " = " + txt1 + jointure2 + " AND " + lab2 + " = " + txt2);

        ResultSet result = null;
        try {

            result = conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT " + jointure1 + "* FROM " + Classe + " WHERE " + lab1 + " = " + txt1 + jointure2 + " AND " + lab2 + " = " + txt2
                    
                    );

        } catch (SQLException e) {
           
        }
        return result;
    }
    /**
     * Requête 1 critère
     * @param Classe
     * @param lab1
     * @param txt1
     * @return Resulset le resultat de la requete
     */

    public ResultSet requete1critere(String Classe, String lab1, String txt1) {
        String jointure1 = "";
        String jointure2 = "";
        try {
            Integer.parseInt(txt1);
            txt1 = txt1;
        } catch (Exception e) {
            txt1 = isString(txt1,Classe);
        }

        if (Classe.equals("infirmier") || Classe.equals("docteur")) {
            jointure1 = "e.prenom, e.nom, i.";
            Classe = Classe + " i, employe e ";
            jointure2 = " AND e.numero=i.numero";
            lab1 = "i." + lab1;
        }

        if (Classe.equals("hospitalisation")) {
            jointure1 = "e.prenom, e.nom, i.";
            Classe = Classe + " i, malade e";
            jointure2 = " AND e.numero=i.no_malade";
            lab1 = "i." + lab1;
        }

        if (Classe.equals("soigne")) {
            jointure1 = "e.prenom,e.nom,d.prenom AS pdocteur,d.nom AS ndocteur, i.";
            Classe = Classe + " i, malade e,employe d";
            jointure2 = " AND e.numero=i.no_malade AND i.no_docteur=d.numero";
            lab1 = "i." + lab1;

            }

        ResultSet result = null;
        try {

            result = conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT " + jointure1 + "* FROM " + Classe + " WHERE " + lab1 + " = " + txt1 + jointure2
                    
                    );

        } catch (SQLException e) {
            System.out.println("SELECT " + jointure1 + "* FROM " + Classe + " WHERE " + lab1 + " = " + txt1 + jointure2);
         
        }
        return result;
    }

    /**
     * Requête sans critère
     * @param Classe
     * @return Resulset le resultat de la requete
     */
    
    public ResultSet requete0critere(String Classe) {
        ResultSet result = null;
        String jointure1=""; String jointure2="";
        if (Classe.equals("infirmier") || Classe.equals("docteur")) {
            jointure1 = "e.prenom, e.nom, i.";
            Classe = Classe + " i, employe e ";
            jointure2 = " WHERE e.numero=i.numero";

        }

        if (Classe.equals("hospitalisation")) {
            jointure1 = "e.prenom, e.nom, i.";
            Classe = Classe + " i, malade e";
            jointure2 = " WHERE e.numero=i.no_malade";
        }

        if (Classe.equals("soigne")) {
            jointure1 = "e.prenom,e.nom,d.prenom,d.nom, i.";
            Classe = Classe + " i, malade e,employe d";
            jointure2 = " WHERE e.numero=i.no_malade AND i.no_docteur=d.numero";

           }
        
        try {

            result = conn
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT "+jointure1+"* FROM " + Classe+jointure2
                    );

        } catch (SQLException e) {
            
        }
        return result;
    }

   
    /**
     * Si une string ajoute un "'"
     * @param txt
     * @return txt la string modifée
     */
    public String isString(String txt,String Classe) {
        boolean okstring = false;
        String truc = "'";

        try {
            //Conversion du label en int
            Integer.parseInt(txt);
        } catch (NumberFormatException e) {
            //Si ça ne marche pas, on a à faire à une string
            
                okstring = true;
            
        }
        if (okstring) {
            txt = "'"+txt + "'";
                }

        return txt;

    }

     
}
