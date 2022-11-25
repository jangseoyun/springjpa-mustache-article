package hospitaljpa.mustache.service;

import hospitaljpa.mustache.domain.dto.ReviewDto;
import hospitaljpa.mustache.domain.entity.HospitalReview;
import hospitaljpa.mustache.domain.factory.ReviewFactory;
import hospitaljpa.mustache.domain.repository.ReviewJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewJpaRepository reviewJpaRepository;

    /*------------ hospital-id review list select ----------*/
    public List<ReviewDto> getHospitalIdReviewList(Long hospitalId) {
        List<HospitalReview> findHospitalAndId = reviewJpaRepository.findByHospitalId(hospitalId);
        log.info("findHospitalId-ReviewList:{}", findHospitalAndId);
        return findHospitalAndId.stream()
                .map(hospitalReview -> ReviewFactory.toReviewDto(hospitalReview))
                .collect(Collectors.toList());
    }

    /*------------ review-id review list select ----------*/
    public ReviewDto getReviewIdObject(Long reviewId) {
        HospitalReview getReviewIdObejct = reviewJpaRepository
                .findById(reviewId)
                .orElseThrow(() -> new RuntimeException("해당 id가 없습니다"));

        return ReviewFactory.toReviewDto(getReviewIdObejct);
    }
}
