package hospitaljpa.mustache.controller;

import hospitaljpa.mustache.domain.dto.ArticleResponse;
import hospitaljpa.mustache.domain.dto.HospitalResponse;
import hospitaljpa.mustache.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hospitals")
public class HospitalRestController {

    private final HospitalService hospitalService;

    /*------------ select one ----------*/
    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Long id) {
        HospitalResponse hospital = hospitalService.getHospital(id);
        return ResponseEntity
                .ok()
                .body(hospital);
    }


}
