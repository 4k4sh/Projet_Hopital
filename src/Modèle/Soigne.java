package Modèle;

import Modèle.Docteur;
import Modèle.Malade;

/**
 *
 * @author Akram, Thomas, Killian
 */

/**
 * 
 * Classe objet permettant de faire le lien avec la table Soigne
 * Les attributs ont Ã©tÃ© mis en place par rapport Ã  la description de la base de donnÃ©es
 */
public class Soigne {
    
    /*
    Variable de la classe Soigne
     */
    

    
    private Docteur no_docteur;
    private Malade no_malade;
    private String jour;
    private String heure;
  
    
    /*Constructeur par defaut*/
    
    public Soigne() {
    }
    
    /*Constructeur d'initialisation*/

    public Soigne(Docteur no_docteur, Malade no_malade, String jour, String heure) {
        this.no_docteur = no_docteur;
        this.no_malade = no_malade;
        this.jour = jour;
        this.heure = heure;
    }

    @Override
    public String toString() {
        return "Soigne{" + "no_docteur=" + no_docteur + ", no_malade=" + no_malade + ", jour=" + jour + ", heure=" + heure + '}';
    }

    /*Getters & Setters*/
    
    public void setJour(String jour) {
        this.jour = jour;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getJour() {
        return jour;
    }

    public String getHeure() {
        return heure;
    }
   
    /**
     * @return the no_docteur
     */
    public Docteur getIdDocteur() {
        return no_docteur;
    }

    /**
     * @param no_docteur the no_docteur to set
     */
    public void setIdDocteur(Docteur no_docteur) {
        this.no_docteur = no_docteur;
    }

    /**
     * @return the no_malade
     */
    public Malade getIdMalade() {
        return no_malade;
    }

    /**
     * @param no_malade the no_malade to set
     */
    public void setIdMalade(Malade no_malade) {
        this.no_malade = no_malade;
    }
   
   
    
    
    
}
