package hospitaljpa.mustache.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VisitCreateRequest {
    private Long hospitalId;
    private String disease;
    private float amount;
}
