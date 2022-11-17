package hospitaljpa.mustache.domain.factory;

import hospitaljpa.mustache.domain.entity.Article;
import hospitaljpa.mustache.domain.entity.ArticleComment;

public class CommentFactory {
    public static ArticleComment toCommentEntity(Article article, String comment) {
        return new ArticleComment(article, comment);
    }
}
