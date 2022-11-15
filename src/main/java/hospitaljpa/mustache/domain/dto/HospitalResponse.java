package hospitaljpa.mustache.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
@AllArgsConstructor
public class HospitalResponse {

    private Long id;
    private String hospitalName;
    private Integer patientRoomCnt;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private Float totalAreaSize;
    private String businessStatus;

}
