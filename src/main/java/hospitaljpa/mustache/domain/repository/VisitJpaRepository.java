package hospitaljpa.mustache.domain.repository;

import hospitaljpa.mustache.domain.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitJpaRepository extends JpaRepository<Visit, Long> {
}
