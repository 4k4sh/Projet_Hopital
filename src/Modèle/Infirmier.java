package Mod�le;

import Mod�le.Employe;
import Mod�le.Service;

/**
 *
 * @author Akram, Thomas, Killian
 */


/**
 * 
 * Classe objet permettant de faire le lien avec la table Infirmier
 * Les attributs ont été mis en place par rapport à la description de la base de données
 */


public class Infirmier {
    
    /*
    Variable de la classe Infirmier
    */
    
    private Employe numero; //(clé secondaire)
    private Service code_service; //(clé secondaire)
    private String rotation;
    private double salaire;

    /*Constructeur par defaut*/
    
    public Infirmier() {
    }
    
    /*Constructeur par defaut*/

    
    public Infirmier(Employe numero, Service code_service, String rotation, double salaire) {
        this.numero = numero;
        this.code_service = code_service;
        this.rotation = rotation;
        this.salaire = salaire;
    }

    
    @Override
    public String toString() {
        return "Infirmier{" + "numero=" + numero + ", code_service=" + code_service + ", rotation=" + rotation + ", salaire=" + salaire + '}';
    }

    /*Getters & Setters*/
    
    /**
     * @return the numero
     */
    public Employe getEmp() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setEmp(Employe numero) {
        this.numero = numero;
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
     * @return the rotation
     */
    public String getRotation() {
        return rotation;
    }

    /**
     * @param rotation the rotation to set
     */
    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    /**
     * @return the salaire
     */
    public double getSalaire() {
        return salaire;
    }

    /**
     * @param salaire the salaire to set
     */
    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }


      
    
}
