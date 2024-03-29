package hospitaljpa.mustache.controller;


import hospitaljpa.mustache.domain.dto.ArticleDto;
import hospitaljpa.mustache.domain.dto.CommentDto;
import hospitaljpa.mustache.domain.entity.Article;
import hospitaljpa.mustache.domain.entity.ArticleComment;
import hospitaljpa.mustache.domain.factory.CommentFactory;
import hospitaljpa.mustache.domain.repository.ArticleJpaRepository;
import hospitaljpa.mustache.domain.repository.CommentJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(value = "/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleJpaRepository articleRepository;
    private final CommentJpaRepository commentJpaRepository;

    //---------main---------------
    @GetMapping("")
    public String index() {
        return "redirect:/articles/list";
    }

    //---------insert form---------------
    @GetMapping(value = "/new")
    public String addForm() {
        return "articles/new";
    }

    //---------insert---------------
    @PostMapping("")
    public String add(ArticleDto articleDto) {
        log.info(articleDto.toString());
        Article article = articleDto.toEntity();
        Article saveOne = articleRepository.save(article);
        log.info("saveOne:{}", saveOne);
        return String.format("redirect:/articles/%d", saveOne.getId());
    }

    //---------show-select One---------------
    @GetMapping("/{id}")
    public String getContentsOne(@PathVariable("id") Long id, Model model) {
        log.info("id:{}", id);

        Optional<Article> optArticle = articleRepository.findById(id);
        List<ArticleComment> commentList = commentJpaRepository.getCommentId(id);
        log.info("comment list: {}", commentList);//todo : comment

        if (optArticle.isEmpty()) {
            return "error";
        }

        model.addAttribute("article", optArticle.get());
        model.addAttribute("commentList", commentList);
        return "articles/show";
    }

    //---------Select All---------------
    @GetMapping("/list")
    public String getListAll(Model model) {
        List<Article> articleList = articleRepository.findAll();
        model.addAttribute("articleList", articleList);
        return "articles/list";
    }

    //---------delete one---------------
    @GetMapping("/{id}/delete")
    public String deleteOne(@PathVariable("id") Long id) {
        log.info("delete 요청 : {}", id);
        articleRepository.deleteById(id);
        return "redirect:/articles/list";
    }

    //---------update form---------------
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {
        log.info("update-form 요청 : {}", id);
        Optional<Article> findOne = articleRepository.findById(id);
        log.info("{}", findOne);
        if (findOne.isEmpty()) {
            return "error";
        }

        model.addAttribute("getContents", findOne.get());
        return "articles/edit";
    }

    //---------update--------------------
    @PostMapping("/update")
    public String edit(ArticleDto articleDto, Model model) {
        log.info("article dto : {}", articleDto);
        Article article = articleDto.toEntityAll();
        articleRepository.save(article);
        return "redirect:/articles/list";
    }

    //---------comment  등록--------------------
    @PostMapping("/comment")
    public String comment(CommentDto commentDto) {
        log.info("comment:{}", commentDto);
        Article article = articleRepository.findById(commentDto.getId()).get();
        ArticleComment articleComment = CommentFactory.toCommentEntity(article, commentDto.getComment());
        commentJpaRepository.save(articleComment);
        return "redirect:/articles/" + commentDto.getId();
    }
}
