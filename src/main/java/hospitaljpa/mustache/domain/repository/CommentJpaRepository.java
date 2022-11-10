package hospitaljpa.mustache.domain.repository;

import hospitaljpa.mustache.domain.entity.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentJpaRepository extends JpaRepository<ArticleComment, Long> {
    @Query("select ac from ArticleComment ac where ac.id = :id")
    List<ArticleComment> getCommentId(@Param("id") Long id);
}
