package hospitaljpa.mustache.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
    private String businessStatusName;

}
