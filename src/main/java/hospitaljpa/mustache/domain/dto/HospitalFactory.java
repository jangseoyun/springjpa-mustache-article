package hospitaljpa.mustache.domain.dto;

import hospitaljpa.mustache.domain.entity.Hospital;

public class HospitalFactory {

    public static HospitalResponse toHospitalResponse(Hospital hospital) {
        HospitalResponse hospitalResponse = HospitalResponse.builder()
                .id(hospital.getId())
                .hospitalName(hospital.getHospitalName())
                .patientRoomCnt(hospital.getPatientRoomCnt())
                .totalNumberOfBeds(hospital.getTotalNumberOfBeds())
                .businessStatus(setBusinessStatusName(hospital.getBusinessStatusCode()))
                .businessTypeName(hospital.getBusinessTypeName())
                .build();
        return hospitalResponse;
    }

    private static String setBusinessStatusName(int businessStatusCode) {
        if (businessStatusCode == 13) {
            return "영업중";
        } else if (businessStatusCode == 3) {
            return "폐업";
        } else {
            return String.valueOf(businessStatusCode);
        }
    }
}
