package dal;


import dal.jdbc.ArticleDAOJdbcImpl;

public class DAOFactory {

    private static ArticleDAO articleDAO = new ArticleDAOJdbcImpl();

    public static ArticleDAO getArticleDAO(){
        return articleDAO;
    }
}
