package hospitaljpa.mustache.domain.dto;

import hospitaljpa.mustache.domain.entity.Article;

public class ArticleFactory {

    public static ArticleResponse toArticleResponse(Article article) {
        return new ArticleResponse(
                article.getId()
                , article.getTitle()
                , article.getContents());
    }

    public static Article toArticle(ArticleAddRequest articleRequest) {
        return new Article(
                articleRequest.getTitle()
                , articleRequest.getContents());
    }

}
