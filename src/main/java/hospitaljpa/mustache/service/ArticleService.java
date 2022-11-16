package hospitaljpa.mustache.service;

import hospitaljpa.mustache.domain.dto.ArticleFactory;
import hospitaljpa.mustache.domain.dto.ArticleResponse;
import hospitaljpa.mustache.domain.entity.Article;
import hospitaljpa.mustache.domain.repository.ArticleJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleJpaRepository articleJpaRepository;

    /*------- select one --------------*/
    public ArticleResponse getArticleOne(Long id) {
        Optional<Article> getArticle = articleJpaRepository.findById(id);
        log.info("service getArticle: {}", getArticle.get());
        return ArticleFactory.toArticleResponse(getArticle.get());
    }
}
