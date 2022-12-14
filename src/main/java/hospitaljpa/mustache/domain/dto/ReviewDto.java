package hospitaljpa.mustache.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private Long hospitalId;
    private String title;
    private String comment;
    private String userName;
}
