package Mod�le;

import Mod�le.Docteur;

/**
 *
 * @author Akram, Thomas, Killian
 */

/*
*
* Classe objet permettant de faire le lien avec la table Service
* Les attributs ont été mis en place par rapport à la description de la base de données
*/
public class Service {
    
    /*
    Variable de la classe Service
    */
    
    private String code; //(clé primaire)
    private String nom;
    private char batiment;
    private Docteur directeur;
    
    /*Constructeur par defaut*/
    
    public Service() {
    }

    /*Constructeur d'initialisation*/
    
    public Service(String code, String nom, char batiment, Docteur directeur) {
        this.code = code;
        this.nom = nom;
        this.batiment = batiment;
        this.directeur = directeur;
    }

    
    @Override
    public String toString() {
        return "Service{" + "code=" + code + ", nom=" + nom + ", batiment=" + batiment + ", directeur=" + directeur + '}';
    }
    
      /**
     * Getter et setter
     */
        
    /**
     * @return the code
     */
    public String getIdService() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setIdService(String code) {
        this.code = code;
    }

    /**
     * @return the nom
     */
    public String getNomService() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNomService(String nom) {
        this.nom = nom;
    }

    /**
     * @return the batiment
     */
    public char getBatiment() {
        return batiment;
    }

    /**
     * @param batiment the batiment to set
     */
    public void setBatiment(char batiment) {
        this.batiment = batiment;
    }

    /**
     * @return the directeur
     */
    public Docteur getDirecteur() {
        return directeur;
    }

    /**
     * @param directeur the directeur to set
     */
    public void setDirecteur(Docteur directeur) {
        this.directeur = directeur;
    }      
    
}
