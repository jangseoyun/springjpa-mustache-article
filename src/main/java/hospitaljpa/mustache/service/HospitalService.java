package hospitaljpa.mustache.service;

import hospitaljpa.mustache.domain.entity.Hospital;
import hospitaljpa.mustache.domain.repository.HospitalJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HospitalService {

    private final HospitalJpaRepository hospitalJpaRepository;

    public Page<Hospital> getHospitalList(Pageable pageable) {
        return hospitalJpaRepository.findAll(pageable);
    }
}
