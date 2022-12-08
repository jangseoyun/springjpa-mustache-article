package hospitaljpa.mustache.controller;

import hospitaljpa.mustache.domain.dto.*;
import hospitaljpa.mustache.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/visits")
public class VisitRestController {
    private final VisitService visitService;

    /**
     * 방문 기록 등록
     * @param visitRequest
     * @param authentication
     * @return - visitId, hospitalName, writer, disease, amount
     */
    @PostMapping("")
    public ResponseEntity<VisitCreateResponse> creatGuestBook(@RequestBody VisitCreateRequest visitRequest, Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(visitService.create(visitRequest, username));
    }


    /**
     * 전체 조회
     * @return 방문 기록 전체 조회 결과, 전체 방명록 개수
     */
    @GetMapping("")
    public ResponseEntity<VisitFindAllResponse> findAllVisits() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(visitService.findAll());
    }


    /**
     * 특정 유저 방문 기록 조회
     * @param userId
     * @return 작성한 리스트, 카운트 반환
     */
    @GetMapping("/user/{user-id}")
    public ResponseEntity<FindByUserVisitResponse> findUserVisits(@PathVariable("user-id") Long userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(visitService.findByVisitsUser(userId));
    }

    /**
     * 특정 병원 방문 기록 조회
     * @param hospitalId
     * @return 해당 병원 방문 기록 리스트, 카운트 반환
     */
    @GetMapping("/hospital/{hospital-id}")
    public ResponseEntity<FindByHospitalVisitResponse> findHospitalVisits(@PathVariable("hospital-id") Long hospitalId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(visitService.findBuHospitalVisits(hospitalId));
    }

}
