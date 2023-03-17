package hospitaljpa.mustache.controller;

import hospitaljpa.mustache.domain.dto.HospitalResponse;
import hospitaljpa.mustache.service.HospitalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HospitalRestController.class)
@DisplayName("HospitalRestControllerTest")
class HospitalRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean //@Autowired 아니다 잘 체크할 것 -> hospitalService 실제가 아닌 객체
    HospitalService hospitalService;

    @Test
    @DisplayName("1개의 json형태의 객체가 잘 오는지 확인")
    void jsonResponse() throws Exception {
        HospitalResponse hospitalResponse = HospitalResponse.builder()
                .id(2321L)
                .hospitalName("노소아청소년과의원")
                .patientRoomCnt(0)
                .totalNumberOfBeds(0)
                .businessTypeName("영업중")
                .totalAreaSize(0.0f)
                .businessStatus("의원")
                .build();

        given(hospitalService.getHospital(2321L))
                .willReturn(hospitalResponse);

        Long hospitalId = 2321L;

        String url = String.format("/api/v1/hospitals/%d", hospitalId);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hospitalName").exists())  // $는 루트 $아래에 hospitalName이 있어야 함
                .andExpect(jsonPath("$.hospitalName").value("노소아청소년과의원"))
                .andDo(print()); // http request, response내역을 출력 해라

        verify(hospitalService).getHospital(hospitalId);// getHospital()메소드의 호출이 있었는지 확인

    }
}
