package hospitaljpa.mustache.domain.dto;

import hospitaljpa.mustache.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String contents;

    public Article toEntity() {
        return new Article(title, contents);
    }

    public Article toEntityAll() {
        return new Article(id, title, contents);
    }

}
