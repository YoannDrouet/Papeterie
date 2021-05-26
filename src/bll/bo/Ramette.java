package bll.bo;

/**
 * Classe Ramette héritant de la classe Article
 *
 * <ul>
 *     <li><b>grammage : </b>Le poids de la ramette</li>
 * </ul>
 *
 * @date 25/05/2021
 * @author Yoann Drouet
 */
public class Ramette extends Article{
    private int grammage;

    public Ramette(int idArticle, String reference, String marque, String designation, float prixUnitaire, int qteStock, int grammage) {
        super(idArticle, reference, marque, designation, prixUnitaire, qteStock);
        this.grammage = grammage;
    }

    public Ramette(String reference, String marque, String designation, float prixUnitaire, int qteStock, int grammage) {
        super(reference, marque, designation, prixUnitaire, qteStock);
        this.grammage = grammage;
    }

    @Override
    public String toString() {
        String s = "Ramette " + this.grammage + "g ";
        s += super.toString();
        return s;
    }
}
