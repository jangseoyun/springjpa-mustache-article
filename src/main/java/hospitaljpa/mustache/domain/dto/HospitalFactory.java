package hospitaljpa.mustache.domain.dto;

import hospitaljpa.mustache.controller.HospitalResponse;
import hospitaljpa.mustache.domain.entity.Hospital;

public class HospitalFactory {

    public static HospitalResponse toHospitalResponse(Hospital hospital) {
        HospitalResponse hospitalResponse = new HospitalResponse(hospital.getId(), hospital.getHospitalName(), hospital.getPatientRoomCnt()
                , hospital.getTotalNumberOfBeds(), setBusinessStatusName(hospital.getBusinessStatusCode()), hospital.getTotalAreaSize()
                , hospital.getBusinessTypeName());
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
