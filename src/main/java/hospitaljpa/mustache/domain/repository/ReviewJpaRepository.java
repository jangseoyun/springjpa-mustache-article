package hospitaljpa.mustache.domain.repository;

import hospitaljpa.mustache.domain.entity.HospitalReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewJpaRepository extends JpaRepository<HospitalReview, Long> {
    List<HospitalReview> findByHospitalId(Long id);
}
