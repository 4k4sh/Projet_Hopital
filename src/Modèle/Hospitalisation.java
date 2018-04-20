package Mod�le;

import Mod�le.Chambre;
import Mod�le.Malade;
import Mod�le.Service;

/**
 *
 * @author Akram, Thomas, Killian
 */

/**
 * 
 * Classe objet permettant de faire le lien avec la table Hospitalisation
 * Les attributs ont été mis en place par rapport à la description de la base de données
 * 
 */
public class Hospitalisation {
    
    /*
    Variable de la classe Hospitalisation
    */
    
    private Malade no_malade; //provient de la classe "Malade" (clé primaire)
    private Service code_service; //clé candidate
    private Chambre no_chambre; //clé candidate
    private int lit; 

    /*Constructeur par defaut*/
    
    public Hospitalisation() {
    }
    
    /*Constructeur d'initialisation*/

    public Hospitalisation(Malade no_malade, Service code_service, Chambre no_chambre, int lit) {
        this.no_malade = no_malade;
        this.code_service = code_service;
        this.no_chambre = no_chambre;
        this.lit = lit;
    }

    @Override
    public String toString() {
        return "Hospitalisation{" + "no_malade=" + no_malade + ", code_service=" + code_service + ", no_chambre=" + no_chambre + ", lit=" + lit + '}';
    }

    /* Getters & Setters*/
    
    /**
     * @return the no_malade
     */
    public Malade getMalade() {
        return no_malade;
    }

    /**
     * @param no_malade the no_malade to set
     */
    public void setMalade(Malade no_malade) {
        this.no_malade = no_malade;
    }

    /**
     * @return the code_service
     */
    public Service getServ() {
        return code_service;
    }

    /**
     * @param code_service the code_service to set
     */
    public void setServ(Service code_service) {
        this.code_service = code_service;
    }

    /**
     * @return the no_chambre
     */
    public Chambre getChambre() {
        return no_chambre;
    }

    /**
     * @param no_chambre the no_chambre to set
     */
    public void setChambre(Chambre no_chambre) {
        this.no_chambre = no_chambre;
    }

    /**
     * @return the lit
     */
    public int getLit() {
        return lit;
    }

    /**
     * @param lit the lit to set
     */
    public void setLit(int lit) {
        this.lit = lit;
    }

     /**
     * Getter et setter
     */
    
   
    
}
