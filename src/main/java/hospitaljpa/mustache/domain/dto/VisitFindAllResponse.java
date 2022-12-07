package hospitaljpa.mustache.domain.dto;

import hospitaljpa.mustache.domain.entity.Visit;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class VisitFindAllResponse {
    private List<Visit> findAllVisits;
    private int VisitsCount;
}
