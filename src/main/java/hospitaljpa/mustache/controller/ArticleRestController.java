package hospitaljpa.mustache.controller;

import hospitaljpa.mustache.domain.dto.ArticleResponse;
import hospitaljpa.mustache.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/article")
public class ArticleRestController {

    private final ArticleService articleService;

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticleOne(@PathVariable("id") Long id) {
        ArticleResponse articleOne = articleService.getArticleOne(id);
        return ResponseEntity
                .ok()
                .body(articleOne);
    }
}
