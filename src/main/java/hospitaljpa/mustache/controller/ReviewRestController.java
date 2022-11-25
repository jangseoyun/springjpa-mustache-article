package hospitaljpa.mustache.controller;

import hospitaljpa.mustache.domain.dto.ReviewDto;
import hospitaljpa.mustache.domain.dto.ReviewResponse;
import hospitaljpa.mustache.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hospitals")
public class ReviewRestController {

    private final ReviewService reviewService;

    /*------------ hospital-id review list select ----------*/
    @GetMapping("/{hospitalId}/reviews")
    public ResponseEntity getHospitalReviews(@PathVariable("hospitalId") Long id) {
        log.info("hospital 리뷰 리스트 요청, hospital-id: {}", id);
        List<ReviewDto> hospitalIdReviewList = reviewService.getHospitalIdReviewList(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(hospitalIdReviewList);
    }

    /*------------ review-id review list select ----------*/
    @GetMapping("/review/{id}")
    public ResponseEntity getReviewOne(@PathVariable("id") Long id) {
        log.info("review 리뷰 요청 : {}", id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reviewService.getReviewIdObject(id));
    }

    @GetMapping("/review/{id}/cnt")
    public ResponseEntity<ReviewResponse> getReviewListAndTotalCnt(@PathVariable("id") Long id) {
        log.info("review 리뷰 + count 요청 : {}", id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reviewService.getReviewListAndTotalCnt(id));
    }
}
