package hospitaljpa.mustache.controller;

import hospitaljpa.mustache.domain.dto.HospitalResponse;
import hospitaljpa.mustache.domain.dto.ReviewDto;
import hospitaljpa.mustache.service.HospitalService;
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
