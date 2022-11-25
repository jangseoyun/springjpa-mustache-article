package hospitaljpa.mustache.controller;

import hospitaljpa.mustache.domain.dto.HospitalResponse;
import hospitaljpa.mustache.domain.dto.ReviewDto;
import hospitaljpa.mustache.service.HospitalService;
import hospitaljpa.mustache.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/hospital")
public class ReviewController {

    private final HospitalService hospitalService;
    private final ReviewService reviewService;

    /*------------ 병원 리뷰 등록폼 ----------*/
    @GetMapping("/review")
    public String reviewForm(@RequestParam(name = "hospital-id") Long id
            , @RequestParam(name = "hospital-name") String name
            , Model model) {
        log.info("hospital 리뷰 작성 폼, hospital-id {}", id);
        List<ReviewDto> hospitalIdReviewList = reviewService.getHospitalIdReviewList(id);
        model.addAttribute("name", name);
        model.addAttribute("id", id);
        model.addAttribute("reviewList", hospitalIdReviewList);
        return "hospital/review-list";
    }

    /*------------ 병원 리뷰 등록 ----------*/
    @PostMapping("/review")
    public String reviewAdd(ReviewDto reviewDto) {
        log.info("리뷰 등록: {}", reviewDto);
        //리뷰 테이블 저장하기
        HospitalResponse saveReview = hospitalService.saveReview(reviewDto);
        log.info("등록된 리뷰 : {}", saveReview);
        return "redirect:/hospital/review?hospital-id=" + saveReview.getId() + "&hospital-name=" + saveReview.getHospitalName();
    }

}
