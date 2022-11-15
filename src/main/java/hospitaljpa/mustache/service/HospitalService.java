package hospitaljpa.mustache.service;

import hospitaljpa.mustache.controller.HospitalResponse;
import hospitaljpa.mustache.domain.dto.HospitalFactory;
import hospitaljpa.mustache.domain.entity.Hospital;
import hospitaljpa.mustache.domain.repository.HospitalJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HospitalService {

    private final HospitalJpaRepository hospitalJpaRepository;

    public Slice<Hospital> getHospitalList(Pageable pageable) {
        return hospitalJpaRepository.findAll(pageable);
    }

    public HospitalResponse getHospital(Long id) {
        Optional<Hospital> findOne = hospitalJpaRepository.findById(id);
        HospitalResponse hospitalResponse = HospitalFactory.toHospitalResponse(findOne.get());
        return hospitalResponse;
    }
}
