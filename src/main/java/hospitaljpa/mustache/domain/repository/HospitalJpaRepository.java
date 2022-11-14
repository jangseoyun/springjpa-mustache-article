package hospitaljpa.mustache.domain.repository;

import hospitaljpa.mustache.domain.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalJpaRepository extends JpaRepository<Hospital, Long> {
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);
    List<Hospital> findByRoadNameAddressContaining(String address);

    List<Hospital> findByTotalNumberOfBedsBetween(int start, int end);
}
