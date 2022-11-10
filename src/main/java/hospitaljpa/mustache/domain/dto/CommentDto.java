package hospitaljpa.mustache.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentDto {

    private Long id;
    private String comment;

}
