package hospitaljpa.mustache.service;

import hospitaljpa.mustache.domain.dto.HospitalResponse;
import hospitaljpa.mustache.domain.dto.ReviewDto;
import hospitaljpa.mustache.domain.entity.Hospital;
import hospitaljpa.mustache.domain.entity.HospitalReview;
import hospitaljpa.mustache.domain.factory.HospitalFactory;
import hospitaljpa.mustache.domain.factory.ReviewFactory;
import hospitaljpa.mustache.domain.repository.HospitalJpaRepository;
import hospitaljpa.mustache.domain.repository.ReviewJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HospitalService {

    private final HospitalJpaRepository hospitalJpaRepository;
    private final ReviewJpaRepository reviewJpaRepository;

    /*------------ 리스트 + 페이징 ----------*/
    public Slice<Hospital> getHospitalList(Pageable pageable) {
        return hospitalJpaRepository.findAll(pageable);
    }

    /*------------ select one ----------*/
    public HospitalResponse getHospital(Long id) {
        Optional<Hospital> findOne = hospitalJpaRepository.findById(id);
        HospitalResponse hospitalResponse = HospitalFactory.toHospitalResponse(findOne.get());
        return hospitalResponse;
    }

    /*------------ 키워드 검색 ---------------*/
    public Slice<HospitalResponse> searchHospitalName(String keyword, Pageable pageable) {
        Slice<Hospital> findHospitalName = hospitalJpaRepository.findAllByHospitalNameContaining(keyword, pageable);
        log.info("count: {}", findHospitalName.getSize());
        Slice<HospitalResponse> responseSlice = findHospitalName.map(new Function<Hospital, HospitalResponse>() {
            @Override
            public HospitalResponse apply(Hospital hospital) {
                return HospitalFactory.toHospitalResponse(hospital);
            }
        });

        return responseSlice;
    }

    /*------------ 병원 리뷰 등록 ----------*/
    @Transactional
    public HospitalResponse saveReview(ReviewDto reviewDto) {
        //호스피탈 호출
        Optional<Hospital> getHospital = hospitalJpaRepository.findById(reviewDto.getHospitalId());
        HospitalReview createEntity = ReviewFactory.toReviewEntity(reviewDto, getHospital.get());
        Hospital hospital = reviewJpaRepository.save(createEntity).getHospital();//reviewId
        HospitalResponse hospitalResponse = HospitalFactory.toHospitalResponse(hospital);
        log.info("review 저장 성공: {}", hospitalResponse);
        return hospitalResponse;
    }

}
