package hospitaljpa.mustache.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleResponse {

    private Long id;
    private String title;
    private String contents;

}
