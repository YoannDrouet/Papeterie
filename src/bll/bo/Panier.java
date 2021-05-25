package bll.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Panier représentant le panier d'article d'un client
 *
 * <ul>
 *     <li><b>montant : </b>Le montant total du panier</li>
 *     <li><b>lignesPanier : </b>Une liste avec toutes les lignes d'article du panier</li>
 * </ul>
 *
 * @date 25/05/2021
 * @author Yoann Drouet
 */
public class Panier {
    private float montant;
    private List<Ligne> lignesPanier;

    public Panier() {
        this.montant = 0;
        lignesPanier = new ArrayList<>();
    }

    public float getMontant() {
        return montant;
    }

    public Ligne getLigne(int index){
        return lignesPanier.get(index);
    }

    public List<Ligne> getLignesPanier() {
        return lignesPanier;
    }

    /**
     * Ajoute une ligne au panier d'article
     * @param article - L'article que le client souhaite acheter
     * @param qte - La quantité de cet article
     */
    public void addLigne(Article article, int qte){
        Ligne ligne = new Ligne(article, qte);
        this.lignesPanier.add(ligne);
    }

    /**
     * Modifie la quantité d'un article qui est déjà dans le panier
     * @param index - L'index auquels se trouve l'article dans la liste
     * @param newQte - La nouvelle quantité de cet article
     */
    public void updateLigne(int index, int newQte){
        Ligne ligne = getLigne(index);
        ligne.setQte(newQte);
        this.lignesPanier.set(index, ligne);
    }

    /**
     * Supprime un article du panier
     * @param index - Index de l'article à supprimer
     */
    public void removeLigne(int index){
        this.lignesPanier.remove(index);
    }

    @Override
    public String toString(){
        int numeroLigne = 1;
        String s;
        s = "Panier:\n\n";

        for (Ligne chaqueLigne : lignesPanier){
            s += "Ligne :" + numeroLigne + " ";
            s += chaqueLigne.toString();
            numeroLigne++;
        }

        s += "\nValeur du panier : " + this.montant + "\n";
        return s;
    }
}
