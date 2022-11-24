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

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HospitalService {

    private final HospitalJpaRepository hospitalJpaRepository;
    private final ReviewJpaRepository reviewJpaRepository;

    public Slice<Hospital> getHospitalList(Pageable pageable) {
        return hospitalJpaRepository.findAll(pageable);
    }

    public HospitalResponse getHospital(Long id) {
        Optional<Hospital> findOne = hospitalJpaRepository.findById(id);
        HospitalResponse hospitalResponse = HospitalFactory.toHospitalResponse(findOne.get());
        return hospitalResponse;
    }

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

    @Transactional
    public Long saveReview(ReviewDto reviewDto) {
        //호스피탈 호출
        Optional<Hospital> getHospital = hospitalJpaRepository.findById(reviewDto.getHospitalId());
        HospitalReview createEntity = ReviewFactory.toReviewEntity(reviewDto, getHospital.get());
        Long reviewId = reviewJpaRepository.save(createEntity).getId();//reviewId
        log.info("review 저장 성공: {}", reviewId);
        return reviewId;
    }

    public List<ReviewDto> getHospitalIdReviewList(Long hospitalId) {
        List<HospitalReview> findHospitalAndId = reviewJpaRepository.findByHospitalId(hospitalId);
        log.info("findHospitalId-ReviewList:{}", findHospitalAndId);
        return findHospitalAndId.stream()
                .map(hospitalReview -> ReviewFactory.toReviewDto(hospitalReview))
                .collect(Collectors.toList());
    }

    public ReviewDto getReviewIdObject(Long reviewId) {
        Optional<HospitalReview> getReviewIdObejct = reviewJpaRepository.findById(reviewId);
        return ReviewFactory.toReviewDto(getReviewIdObejct.get());
    }
}
