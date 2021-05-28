package dal;

import bll.bo.Article;
import java.util.List;

public interface ArticleDAO {

    Article selectById(Integer IdArticle);

    List<Article> selectAll();

    void insert(Article a);

    void update (Article a);

    void delete(Integer idArticle);
}
