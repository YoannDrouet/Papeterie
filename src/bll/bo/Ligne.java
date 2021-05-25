package bll.bo;

/**
 * Classe Ligne représentant un article que le client souhaite achter et sa quantité
 *
 * <ul>
 *     <li><b>article : </b>L'article que le client souhaite acheter</li>
 *     <li><b>qte : </b>La quantité de cet article</li>
 * </ul>
 *
 * @date 25/05/2021
 * @author Yoann Drouet
 */
public class Ligne {
    private Article article;
    protected int qte;

    public Ligne(Article article, int qte) {
        this.qte = qte;
        this.article = article;
    }

    public int getQte() {
        return qte;
    }

    public Article getArticle() {
        return article;
    }

    /**
     * Multiplie le prix unitaire de l'article et la quantité souhaité pour avoir le montant de la ligne
     * @return le prix total de la ligne
     */
    public float getPrix(){
        return this.article.getPrixUnitaire()*this.qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString(){
        String s = "prix : " + this.getPrix();
        s += " " + qte + "x\n";
        if (this.article instanceof Stylo){
            s += ((Stylo)this.article).toString();
        } else {
            s += ((Ramette)this.article).toString();
        }

        return s;
    }
}
