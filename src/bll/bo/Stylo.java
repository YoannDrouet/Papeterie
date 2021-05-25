package bll.bo;

/**
 * Classe Stylo héritant de la classe Article
 *
 * <ul>
 *     <li><b>couleur : </b>La couleur du stylo</li>
 * </ul>
 *
 * @date 25/05/2021
 * @author Yoann Drouet
 */
public class Stylo extends Article{
    private String couleur;

    public Stylo(int idArticle, String reference, String marque, String designation, float prixUnitaire, int qteStock, String couleur) {
        super(idArticle, reference, marque, designation, prixUnitaire, qteStock);
        this.couleur = couleur;
    }

    public Stylo(int idArticle, String reference, String designation, float prixUnitaire, int qteStock, String couleur) {
        super(idArticle, reference, designation, prixUnitaire, qteStock);
        this.couleur = couleur;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        String s = "Stylo " + this.couleur +" ";
        s += super.toString();
        return s;
    }
}
