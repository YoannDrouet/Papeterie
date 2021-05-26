package bll.bo;

/**
 * Classe abstraite Article
 *
 * <ul>
 *     <li><b>idArticle : </b>L'identifiant de l'article dans la base de la papeterie</li>
 *     <li><b>reference : </b>La reference de l'article</li>
 *     <li><b>marque : </b>Sa marque</li>
 *     <li><b>designation : </b>Le nom par lequel on le désigne</li>
 *     <li><b>prixUnitaire : </b>Son prix unitaire</li>
 *     <li><b>qteStock : </b>La quantité de cette article qu'il y a en stock</li>
 * </ul>
 *
 * @date 25/05/2021
 * @author Yoann Drouet
 */
public abstract class Article {
    private int idArticle;
    private String reference;
    private String marque;
    private String designation;
    private float prixUnitaire;
    private int qteStock;

    public Article(int idArticle, String reference, String marque, String designation, float prixUnitaire, int qteStock) {
        this.idArticle = idArticle;
        this.reference = reference;
        this.marque = marque;
        this.designation = designation;
        this.prixUnitaire = prixUnitaire;
        this.qteStock = qteStock;
    }

    public Article(String reference, String marque, String designation, float prixUnitaire, int qteStock) {
        this.reference = reference;
        this.marque = marque;
        this.designation = designation;
        this.prixUnitaire = prixUnitaire;
        this.qteStock = qteStock;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public String getReference() {
        return reference;
    }

    public String getMarque() {
        return marque;
    }

    public String getDesignation() {
        return designation;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public int getQteStock() {
        return qteStock;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public void setQteStock(int qteStock) {
        this.qteStock = qteStock;
    }

    @Override
    public String toString(){
        String s = "[Marque : " + this.marque;
        s+= " Reference : " + this.reference;
        s+= " Prix Unitaire : " + this.prixUnitaire;
        s += "]\n";
        return s;
    }
}
