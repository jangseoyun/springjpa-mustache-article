package hospitaljpa.mustache.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VisitCreateResponse {
    private Long visitId;
    private String hospitalName;
    private String writer;
    private String disease;
    private float amount;
}
