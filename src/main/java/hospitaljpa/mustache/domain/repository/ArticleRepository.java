package hospitaljpa.mustache.domain.repository;

import hospitaljpa.mustache.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
