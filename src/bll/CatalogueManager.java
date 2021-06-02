package bll;

import bll.bo.Article;
import bll.bo.Ramette;
import dal.ArticleDAO;
import dal.DAOFactory;

import java.util.List;

public class CatalogueManager {
    private static CatalogueManager instance;
    private static ArticleDAO articleDAO = DAOFactory.getArticleDAO();

    private CatalogueManager() {
    }

    public static CatalogueManager getInstance() {
        if (instance == null){
            instance = new CatalogueManager();
        }
        return instance;
    }

    public void addArticle(Article a) throws BLLException {
        if (this.verifArticle(a)){
            articleDAO.insert(a);
        }
    }

    public void updateArticle(Article a) throws BLLException {
        if (this.verifArticle(a)){
            articleDAO.update(a);
        }
    }

    public void removeArticle(int idArticle) throws BLLException {
        articleDAO.delete(idArticle);
    }

    public Article getArticle(int idArticle){
        return articleDAO.selectById(idArticle);
    }

    public List<Article> getCatalogue(){

        return articleDAO.selectAll();
    }

    public boolean verifArticle(Article a) throws BLLException {
        if (a.getReference() == null || a.getDesignation() == null || a.getMarque() == null
                || (Float)a.getPrixUnitaire() == null || (Integer)a.getQteStock() == null){
            throw new BLLException("Veuillez renseigner toutes les valeurs");
        }
        if (a instanceof Ramette && ((Ramette) a).getGrammage() <=0){
            throw new BLLException("Le grammage d'une ramette doit avoir une valeur positive");
        }
        if (a.getQteStock() <0 || a.getPrixUnitaire() <= 0){
            throw new BLLException("Entrez une valeur positive pour le prix unitaire et la quantité");
        }
        return true;
    }
}
