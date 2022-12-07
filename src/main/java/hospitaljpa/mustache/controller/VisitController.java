package hospitaljpa.mustache.controller;

import hospitaljpa.mustache.domain.dto.VisitCreateRequest;
import hospitaljpa.mustache.domain.dto.VisitCreateResponse;
import hospitaljpa.mustache.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/visits")
public class VisitController {
    private final VisitService visitService;

    @PostMapping("")
    public ResponseEntity<VisitCreateResponse> creatGuestBook(@RequestBody VisitCreateRequest visitRequest, Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(visitService.create(visitRequest, username));
    }

}
