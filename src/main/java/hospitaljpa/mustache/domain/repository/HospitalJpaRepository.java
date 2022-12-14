package hospitaljpa.mustache.domain.repository;

import hospitaljpa.mustache.domain.entity.Hospital;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalJpaRepository extends JpaRepository<Hospital, Long> {
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);
    List<Hospital> findByRoadNameAddressContaining(String address);
    List<Hospital> findByTotalNumberOfBedsBetween(int start, int end);
    List<Hospital> findByPatientRoomCntBetweenOrderByPatientRoomCntDesc(int a, int b);
    Slice<Hospital> findAllByHospitalNameContaining(String keyword, Pageable pageable);
}
