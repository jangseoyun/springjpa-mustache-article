package hospitaljpa.mustache.domain.repository;

import hospitaljpa.mustache.domain.entity.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<ArticleComment, Long> {
}
