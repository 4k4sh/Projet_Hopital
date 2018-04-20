package Mod�le;

/**
 *
 * @author Akram, Thomas, Killian
 */

/**
 * 
 * Classe objet permettant de faire le lien avec la table Chambre
 * Les attributs ont été mis en place par rapport à la description de la base de données
 */
public class Employe {
    
    /*
    Variable de la classe Employé
    */
    
    private int numero; //(clé primaire)
    private String nom; //(clé candidate)
    private String prenom; //(clé candidate)
    private String tel; //(clé candidate)
    private String adresse;

    /*Constructeur par defaut*/
    
    public Employe() {
    }
    
    /*Consctructeur d'initialisation*/
    
    public Employe(int numero, String nom, String prenom, String tel, String adresse) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.adresse = adresse;
    }

    

    @Override
    public String toString() {
        return "Employe{" + "numero=" + numero + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", adresse=" + adresse + '}';
    }

    
     
    /**
     * Getter et setter
     */
    
    /**
     * @return the numEmploye
     */
    public int getIdEmploye() {
        return numero;
    }

    /**
     * @param numero the numEmploye to set
     */
    public void setIdEmploye(int numero) {
        this.numero = numero;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    
}
