package hospitaljpa.mustache.service;

import hospitaljpa.mustache.domain.dto.HospitalResponse;
import hospitaljpa.mustache.domain.factory.HospitalFactory;
import hospitaljpa.mustache.domain.entity.Hospital;
import hospitaljpa.mustache.domain.repository.HospitalJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
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

    public List<HospitalResponse> searchHospitalName(String keyword, Pageable pageable) {
        Slice<Hospital> findHospitalName = hospitalJpaRepository.findAllByHospitalNameContaining(keyword, pageable);
        List<HospitalResponse> responseList = findHospitalName
                .stream()
                .map(h -> HospitalFactory.toHospitalResponse(h))
                .collect(Collectors.toList());
        return responseList;
    }
}
