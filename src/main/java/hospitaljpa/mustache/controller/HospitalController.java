package hospitaljpa.mustache.controller;

import hospitaljpa.mustache.domain.dto.HospitalResponse;
import hospitaljpa.mustache.domain.dto.ReviewDto;
import hospitaljpa.mustache.domain.entity.Hospital;
import hospitaljpa.mustache.service.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/hospital")
public class HospitalController {

    private final HospitalService hospitalService;

    /*------------ 리스트 + 페이징 ----------*/
    @GetMapping(value = "")
    public String list(Model model
            , @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("hospital list");
        Slice<Hospital> hospitalList = hospitalService.getHospitalList(pageable);
        model.addAttribute("listPaging", hospitalList);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "hospital/hospital-list";
    }

    /*------------ 키워드 검색 ---------------*/
    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword
            , @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
            , Model model) {
        log.info("hospital search: {}", keyword);
        Slice<HospitalResponse> responseList = hospitalService.searchHospitalName(keyword, pageable);
        model.addAttribute("listPaging", responseList);
        model.addAttribute("previous", responseList.previousOrFirstPageable().getPageNumber());
        model.addAttribute("next", responseList.nextOrLastPageable().getPageNumber());
        model.addAttribute("keyword", keyword);
        return "hospital/search-list";
    }

    /*------------ 해당 페이지 설명폼 ----------*/
    @GetMapping("/readme")
    public String readmeForm() {
        log.info("hospital 웹 페이지 설명폼");
        return "hospital/readme";
    }

    /*------------ 병원 리뷰 등록폼 ----------*/
    @GetMapping("/review")
    public String reviewForm(@RequestParam(name = "hospital-id") Long id
            , @RequestParam(name = "hospital-name") String name
            , Model model) {
        log.info("hospital 리뷰 작성 폼, hospital-id {}", id);
        List<ReviewDto> hospitalIdReviewList = hospitalService.getHospitalIdReviewList(id);
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
