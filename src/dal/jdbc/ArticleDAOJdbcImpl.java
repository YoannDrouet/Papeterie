package dal.jdbc;

import bll.bo.Article;
import bll.bo.Ramette;
import bll.bo.Stylo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOJdbcImpl {

    private final String URL ="jdbc:sqlite:identifier.sqlite";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM Articles WHERE idArticle = ?;";
    private final String SQL_SELECT_ALL = "SELECT * FROM Articles;";
    private final String SQL_UPDATE = "UPDATE Articles SET reference=?, marque=?, designation=?, prixUnitaire=?, " +
            "qteStock=?, grammage=?, couleur=? WHERE idArticle=?;";
    private final String SQL_INSERT = "INSERT INTO Articles (reference, marque, designation, prixUnitaire, qteStock, " +
            "grammage, couleur, type) VALUES (?,?,?,?,?,?,?,?);";
    private final String SQL_DELETE = "DELETE FROM Articles WHERE idArticle = ?;";

    /**
     * Méthode qui permet de rechercher un article dans la table Article en fonction de son identifiant.
     * @param idArticle de l'article à rechercher
     * @return L'article recherché
     */
    public Article selectById(Integer idArticle){
        Article retour = null;

        try (Connection connection = DriverManager.getConnection(this.URL)) {

            PreparedStatement unStm = connection.prepareStatement(SQL_SELECT_BY_ID);
            unStm.setInt(1, idArticle);
            //Affecte à la variable retour l'article trouvé
            ResultSet rs = unStm.executeQuery();

            //Vérifie si l'article que l'on a rechercher est un stylo ou une ramette
            if (rs.getString("couleur") != null){
                //Si c'est un stylo on crée un stylo
                //Appel d'une méthode commune qui permet de créer un stylo avec les paramètre d'un ReseltSet
                retour = communConstructeurStylo(rs);
            } else {
                //Si c'est une ramette on crée une ramette
                //Appel d'une méthode commune qui permet de créer un ramette avec les paramètre d'un ReseltSet
                retour = communConstructeurRamette(rs);
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

        try (Connection connection = DriverManager.getConnection(this.URL)) {
            //Créer un état permettant d'intéraagir avec la base
            PreparedStatement unStm = connection.prepareStatement(SQL_SELECT_ALL);
            //Stock le résultat de la requête
            ResultSet rs = unStm.executeQuery();

            //On ajoute un par un chaque article à la liste d'article que l'on souhaite retourner
            while (rs.next()){
                //Vérifie si l'Article est un stylo ou une ramette
                Article a;
                if (rs.getString("couleur") != null){
                    //Si c'est un stylo utilise le constructeur de la classe Stylo
                    //Appel d'une méthode commune qui permet de créer un stylo avec les paramètre d'un ReseltSet
                    a = communConstructeurStylo(rs);
                }else {
                    //Si c'est une ramette utilise le constructeur de la classe Ramette
                    //Appel d'une méthode commune qui permet de créer un ramette avec les paramètre d'un ReseltSet
                    a = communConstructeurRamette(rs);
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

        try (Connection connection = DriverManager.getConnection(this.URL)) {
            //Créer un état permettant d'intéragir avec la base
            //Appel d'une méthode pour remplir la partie commune du PreparedStatement
            PreparedStatement unStm = communPreparedStatement(a, SQL_UPDATE, connection);

            if (a instanceof Stylo){
                unStm.setString(7, ((Stylo) a).getCouleur());
            } else{
                unStm.setInt(6, ((Ramette) a).getGrammage());
            }
            unStm.setInt(8, a.getIdArticle());

            unStm.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Permet d'insérer un article dans la table Article de la base de données
     * @param a L'article à ajouter
     */
    public void insert(Article a){

        try (Connection connection = DriverManager.getConnection(this.URL)){
            //Créer un état permettant d'intéraagir avec la base
            //Appel d'une méthode pour remplir la partie commune du PreparedStatement
            PreparedStatement unStm = communPreparedStatement(a, SQL_INSERT, connection);

            //On vérifie si l'article en paramètre est un stylo où un ramette
            if (a instanceof Stylo){
                //Rajoute la partie de requête spécifique aux stylos
                unStm.setString(7, ((Stylo) a).getCouleur());
                unStm.setString(8, "Stylo");
            } else{
                //Rajoute la partie de requête spécifique aux ramettes
                unStm.setInt(6, ((Ramette) a).getGrammage());
                unStm.setString(8, "Ramette");
            }

            unStm.executeUpdate();
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

        try (Connection connection = DriverManager.getConnection(this.URL)){
            //Créer un état permettant d'intéraagir avec la base
            PreparedStatement unStm = connection.prepareStatement(SQL_DELETE);
            unStm.setInt(1, idArticle);
            //Lance la requête
            unStm.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Permet de remplir la partie commune à tout les articles des PreparedStatement,
     * dans les méthodes insert() et update()
     * @param a L'article que l'on souhaite insérer ou modifier
     * @param requêtePreparee La requête sql à remplir
     * @param connection La connexion à la base de donnée
     * @return Un PreparedStatement dont le début est déjà rempli
     * @throws SQLException
     */
    private PreparedStatement communPreparedStatement(Article a, String requêtePreparee, Connection connection) throws SQLException {
        PreparedStatement unStm = connection.prepareStatement(requêtePreparee);

        unStm.setString(1, a.getReference());
        unStm.setString(2, a.getMarque());
        unStm.setString(3, a.getDesignation());
        unStm.setFloat(4, a.getPrixUnitaire());
        unStm.setInt(5, a.getQteStock());
        return unStm;
    }

    /**
     * Permet de créer une méthode commune qui utilise le constructeur de stylo à partir d'un Rseultset
     * @param rs Le ResultSet contenant les données pour construire le styole
     * @return Le stylo qui à été créé
     * @throws SQLException
     */
    private Article communConstructeurStylo(ResultSet rs) throws SQLException {
        Article retour = new Stylo(rs.getInt("idArticle"),
                rs.getString("reference"),
                rs.getString("marque"),
                rs.getString("designation"),
                rs.getFloat("prixUnitaire"),
                rs.getInt("qteStock"),
                rs.getString("couleur"));
        return retour;
    }

    /**
     * Permet de créer une méthode commune qui utilise le constructeur de ramette à partir d'un Rseultset
     * @param rs Le ResultSet contenant les données pour construire le styole
     * @return La ramette qui à été créée
     * @throws SQLException
     */
    private Article communConstructeurRamette(ResultSet rs) throws SQLException{
        Article retour = new Ramette(rs.getInt("idArticle"),
                rs.getString("reference"),
                rs.getString("marque"),
                rs.getString("designation"),
                rs.getFloat("prixUnitaire"),
                rs.getInt("qteStock"),
                rs.getInt("grammage"));

        return retour;
    }
}
