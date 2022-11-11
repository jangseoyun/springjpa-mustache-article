package hospitaljpa.mustache.controller;

import hospitaljpa.mustache.domain.repository.HospitalJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/hospital")
public class HospitalController {

    private final HospitalJpaRepository hospitalJpaRepository;

    /*------------ 리스트 + 페이징 ----------*/
    @GetMapping("")
    public String list() {
        log.info("hospital list");
        return "hospital/hospital-list";
    }


}
