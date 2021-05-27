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
    public Article selectById(Integer idArticle){
        Article retour = null;
        //Ecriture de la requête SQL permettant de selectionner l'article recherché
        String sql = "SELECT * FROM Articles WHERE idArticle = " + idArticle + ";";

        try (Connection connection = DriverManager.getConnection(this.URL)) {

            Statement unStm = connection.createStatement();
            //Affecte à la variable retour l'article trouvé
            ResultSet rs = unStm.executeQuery(sql);

            //Vérifie si l'article que l'on a rechercher est un stylo ou une ramette
            if (rs.getString("couleur") != null){
                //Si c'est un stylo on crée un stylo
                retour = new Stylo(rs.getInt("idArticle"),
                        rs.getString("reference"),
                        rs.getString("marque"),
                        rs.getString("designation"),
                        rs.getFloat("prixUnitaire"),
                        rs.getInt("qteStock"),
                        rs.getString("couleur"));
            } else {
                //Si c'est une ramette on crée une ramette
                retour = new Ramette(rs.getInt("idArticle"),
                        rs.getString("reference"),
                        rs.getString("marque"),
                        rs.getString("designation"),
                        rs.getFloat("prixUnitaire"),
                        rs.getInt("qteStock"),
                        rs.getInt("grammage"));
            }
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
        String sql = "SELECT * FROM Articles;";

        try (Connection connection = DriverManager.getConnection(this.URL)) {
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

    /**
     * Requête permettant de modifier un article dans la table Article.
     * Prends l'article situé en paramètre et remplace l'article de la table qui a son id
     * @param a Article à modifier
     */
    public void update(Article a){
        //Requête de base pour insérer, partie commune aux stylos et au ramettes
        String sql = "UPDATE Articles SET " +
                "reference = '" + a.getReference() + "'," +
                "marque = '" + a.getMarque() + "'," +
                "designation = '" + a.getDesignation() + "'," +
                "prixUnitaire = " + a.getPrixUnitaire() + "," +
                "qteStock = " + a.getQteStock() + ",";

        //On vérifie si l'article en paramètre est un stylo où un ramette
        if (a instanceof Stylo){
            //On ajoute à la requête la partie spécifique aux stylos
            sql += "couleur = '" + ((Stylo) a).getCouleur() + "' ";
        } else{
            //On ajoute à la requête la partie spécifique aux ramettes
            sql += "grammage = " + ((Ramette) a).getGrammage() + " ";
        }

        //Précise qu'on ne veut modifier que l'article ayant cette id
        sql += "WHERE idArticle = " + a.getIdArticle() + ";";

        try (Connection connection = DriverManager.getConnection(this.URL)) {
            //Créer un état permettant d'intéraagir avec la base
            Statement unStm = connection.createStatement();
            //Stock le résultat de la requête
            unStm.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Permet d'insérer un article dans la table Article de la base de données
     * @param a L'article à ajouter
     */
    public void insert(Article a){
        //Requête de base pour insérer, partie commune aux stylos et au ramettes
        String sql = "INSERT INTO Articles(" +
                "reference," +
                "marque," +
                "designation," +
                "prixUnitaire," +
                "qteStock," +
                "grammage," +
                "couleur," +
                "type) VALUES ('" +
                a.getReference() + "','" +
                a.getMarque() + "','" +
                a.getDesignation() + "',"+
                a.getPrixUnitaire() + "," +
                a.getQteStock() + ",";

        //On vérifie si l'article en paramètre est un stylo où un ramette
        if (a instanceof Stylo){
            //Rajoute la partie de requête spécifique aux stylos
            Integer grammage = null;
            sql += grammage + ",'" +
                    ((Stylo) a).getCouleur() + "'," +
                    "'Stylo');";
        } else{
            //Rajoute la partie de requête spécifique aux ramettes
            String couleur = null;
            sql += ((Ramette) a).getGrammage() + "," +
                    couleur + "," +
                    "'Ramette');";
        }

        try (Connection connection = DriverManager.getConnection(this.URL)){
            //Créer un état permettant d'intéraagir avec la base
            Statement unStm = connection.createStatement();
            //Stock le résultat de la requête
            unStm.executeUpdate(sql);
            //Ajoute une Id à l'article que l'on vient d'intégrer dans la base
            a.setIdArticle(unStm.getGeneratedKeys().getInt(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * Permet de supprimer l'article qui est mis en paramètre de la table article
     * @param idArticle L'id de l''article à supprimer
     */
    public void delete(Integer idArticle){
        //La requête sql permettant de supprimer l'Article
        String sql = "DELETE FROM Articles WHERE idArticle = " + idArticle +";";

        try (Connection connection = DriverManager.getConnection(this.URL)){
            //Créer un état permettant d'intéraagir avec la base
            Statement unStm = connection.createStatement();
            //Stock le résultat de la requête
            unStm.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
