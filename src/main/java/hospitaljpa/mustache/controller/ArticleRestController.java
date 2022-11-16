package hospitaljpa.mustache.controller;

import hospitaljpa.mustache.domain.dto.ArticleRequest;
import hospitaljpa.mustache.domain.dto.ArticleResponse;
import hospitaljpa.mustache.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/article")
public class ArticleRestController {

    private final ArticleService articleService;

    /*------------ select one ----------*/
    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticleOne(@PathVariable("id") Long id) {
        ArticleResponse articleOne = articleService.getArticleOne(id);
        return ResponseEntity
                .ok()
                .body(articleOne);
    }

    /*------------ save article ----------*/
    @PostMapping("")
    public ResponseEntity<ArticleResponse> save(ArticleRequest articleRequest) {
        ArticleResponse articleResponse = articleService.saveArticle(articleRequest);
        return ResponseEntity
                .ok()
                .body(articleResponse);
    }
}
