package hospitaljpa.mustache.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class ReviewRequest {
    private Long id;
    private String title;
    private String comment;
    private String userId;
}
