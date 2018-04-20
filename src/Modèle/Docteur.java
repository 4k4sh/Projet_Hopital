package Mod�le;

import Mod�le.Employe;

/**
 *
 * @author Akram, Thomas, Killian
 */

/**
 * 
 * classe objet permettant de faire le lien avec la table Docteur
 * Les attributs ont été mis en place par rapport à la description de la base de données
 */

public class Docteur {
    
     /*
    Variable de la classe Docteur
    */
    
    private Employe emp; //pour le numéro -> "hérite d'employé" (clé secondaire)
    private String spe; //spécialité

    /*Constructeur par défaut*/
    
    public Docteur() {
    }
    
    /*Constructeur d'initialisation*/
    
    public Docteur(Employe emp, String spe) {
        this.emp = emp;
        this.spe = spe;
    }

    /*@return information */

    @Override
    public String toString() {
        return "Docteur{" + "emp=" + emp + ", spe=" + spe + '}';
    }

    
    /*Getters & Setters*/
    
    /**
     * @return the emp (getter)
     */
    public Employe getEmp() {
        return emp;
    }

    /**
     * @param emp the emp to set
     */
    public void setEmp(Employe emp) {
        this.emp = emp;
    }

    /**
     * @return the spe
     */
    public String getSpe() {
        return spe;
    }

    /**
     * @param spe the spe to set
     */
    public void setSpe(String spe) {
        this.spe = spe;
    }
     /**
     * Getter et setter
     */
    
    
    
}
