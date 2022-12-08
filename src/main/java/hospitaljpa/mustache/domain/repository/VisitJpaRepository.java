package hospitaljpa.mustache.domain.repository;

import hospitaljpa.mustache.domain.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitJpaRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByUsersId(Long userId);
    List<Visit> findByHospitalId(Long HospitalId);
}
