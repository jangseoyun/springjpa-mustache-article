package hospitaljpa.mustache.controller;

import hospitaljpa.mustache.domain.entity.Hospital;
import hospitaljpa.mustache.domain.repository.HospitalJpaRepository;
import hospitaljpa.mustache.service.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/hospital")
public class HospitalController {

    private final HospitalService hospitalService;

    /*------------ 리스트 + 페이징 ----------*/
    @GetMapping("")
    public String list(Model model
            , @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("hospital list");
        Page<Hospital> hospitalList = hospitalService.getHospitalList(pageable);
        model.addAttribute("listpaging", hospitalList);
        return "hospital/hospital-list";
    }


}
