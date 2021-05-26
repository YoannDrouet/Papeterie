package dal.jdbc;

import bll.bo.Article;
import bll.bo.Ramette;
import bll.bo.Stylo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOJdbcImpl {

    private final String URL ="jdbc:sqlite:identifier.sqlite";

    /**
     * Méthode qui permet de rechercher un article dans la table Article en fonction de son identifiant.
     * @param idArticle de l'article à rechercher
     * @return L'article recherché
     */
    public Article selectByld(int idArticle){
        Article retour = null;

        //Ecriture de la requête SQL permettant de selectionner l'article recherché
        String sql = "SELECT * FROM Articles WHERE idArticle = " +idArticle;

        try {
            //Permet de se connecter à la base de données
            Connection connection = DriverManager.getConnection(this.URL);

            Statement unStm = connection.createStatement();
            //Affecte à la variable retour l'article trouvé
            retour = (Article) unStm.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return retour;
    }

    /**
     * Permet d'aller chercher tous les articles contenus dans la table Article
     * @return Une liste avec tout les articles de la table
     */
    public List<Article> selectAll(){
        List<Article> retour = new ArrayList<>();

        //Ecriture de la requête permettant de récuperer tous les articles de la base
        String sql = "SELECT * FROM Articles";

        try {
            //Permet de se connecter à la base de données
            Connection connection = DriverManager.getConnection(this.URL);
            //Créer un état permettant d'intéraagir avec la base
            Statement unStm = connection.createStatement();
            //Stock le résultat de la requête
            ResultSet rs = unStm.executeQuery(sql);

            //On ajoute un par un chaque article à la liste d'article que l'on souhaite retourner
            while (rs.next()){
                //Vérifie si l'Article est un stylo ou une ramette
                Article a;
                if (rs.getString("couleur") != null){
                    //Si c'est un stylo utilise le constructeur de la classe Stylo
                    a = new Stylo(rs.getInt("idArticle"),
                                rs.getString("reference"),
                                rs.getString("marque"),
                                rs.getString("designation"),
                                rs.getFloat("prixUnitaire"),
                                rs.getInt("qteStock"),
                                rs.getString("couleur"));
                }else {
                    //Si c'est une ramette utilise le constructeur de la classe Ramette
                    a = new Ramette(rs.getInt("idArticle"),
                            rs.getString("reference"),
                            rs.getString("marque"),
                            rs.getString("designation"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getInt("grammage"));
                }
                //ajoute l'article à la liste que l'on souhaite renvoyer
                retour.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return retour;
    }

    public void update(Article a){
        if (a instanceof Stylo){

        } else{

        }
    }

    public void insert(Article a){
        String sql;

        if (a instanceof Stylo){
            sql = "INSERT INTO Articles(" +
                    a.getReference() ")";
        } else{

        }

        try {
            //Permet de se connecter à la base de données
            Connection connection = DriverManager.getConnection(this.URL);
            //Créer un état permettant d'intéraagir avec la base
            Statement unStm = connection.createStatement();
            //Stock le résultat de la requête
            unStm.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * Permet de supprimer l'article qui est mis en paramètre de la table article
     * @param a l'article à supprimer
     */
    public void delete(Article a){
        //La requête sql permettant de supprimer l'Article
        String sql = "DELETE * FROM article WHERE idArticle = " + a.getIdArticle();

        try {
            //Permet de se connecter à la base de données
            Connection connection = DriverManager.getConnection(this.URL);
            //Créer un état permettant d'intéraagir avec la base
            Statement unStm = connection.createStatement();
            //Stock le résultat de la requête
            unStm.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
