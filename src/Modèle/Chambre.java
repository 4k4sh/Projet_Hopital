package Mod�le;

import Mod�le.Infirmier;
import Mod�le.Service;

/**
 *
 * @author Akram, Thomas, Killian  
 */

/**
 * 
 * Classe objet permettant de faire le lien avec la table Chambre
 * Les attributs ont été mis en place par rapport à la description de la base de données
 */
public class Chambre {
    
    /*
    Variable de la classe Chambre
    */
            
    private int no_chambre; //clé primaire
    private Service code_service; //"hérite de service" (clé secondaire)
    private Infirmier surveillant; 
    private int nb_lits;

    /*Constructeur par defaut*/
     
    public Chambre() {
    }

    /*Constructeur d'initialisation*/
    
    public Chambre(int no_chambre, Service code_service, Infirmier surveillant, int nb_lits) {
        this.no_chambre = no_chambre;
        this.code_service = code_service;
        this.surveillant = surveillant;
        this.nb_lits = nb_lits;
    }

    
    /*@return information */
    @Override
    public String toString() {
        return "Chambre{" + "no_chambre=" + no_chambre + ", code_service=" + code_service + ", surveillant=" + surveillant + ", nb_lits=" + nb_lits + '}';
    }

    /**
     * @return the no_chambre
     */
    public int getIdChambre() {
        return no_chambre;
    }

    /**
     * @param no_chambre the no_chambre to set
     */
    public void setIdChambre(int no_chambre) {
        this.no_chambre = no_chambre;
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
     * @return the surveillant
     */
    public Infirmier getInf() {
        return surveillant;
    }

    /**
     * @param surveillant the surveillant to set
     */
    public void setInf(Infirmier surveillant) {
        this.surveillant = surveillant;
    }

    /**
     * @return the nb_lits
     */
    public int getNbLits() {
        return nb_lits;
    }

    /**
     * @param nb_lits the nb_lits to set
     */
    public void setNbLits(int nb_lits) {
        this.nb_lits = nb_lits;
    }

   
    
}