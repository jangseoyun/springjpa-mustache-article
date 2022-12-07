package hospitaljpa.mustache.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hospitaljpa.mustache.domain.dto.UserJoinRequest;
import hospitaljpa.mustache.domain.dto.UserResponse;
import hospitaljpa.mustache.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestController.class)
@DisplayName("UserRestControllerTest")
class UserRestControllerTest {

    /*@Autowired
    MockMvc mockMvc;
    @MockBean
    UserService userService;
    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("회원 조회가 잘 되는지")
    @Test
    void findById() throws Exception {
        given(userService.findUser(1L))
                .willReturn(new UserResponse("seoyun", "가입이 완료 되었습니다"));

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").exists())
                .andExpect(jsonPath("$.message", "가입이 완료 되었습니다").exists())
                .andDo(print());
    }

    @DisplayName("사용자 회원가입 등록 성공 확인")
    @Test
    void userSave() throws Exception {
        UserJoinRequest userJoinRequest = new UserJoinRequest("seoyun's", "23432");

        given(userService.join(any()))
                .willReturn(new UserResponse("seoyun's", "가입이 완료 되었습니다"));

        String url = String.format("/api/v1/users");
        mockMvc.perform(post(url)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .content(objectMapper.writeValueAsBytes(userJoinRequest))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").exists())
                .andExpect(jsonPath("$.message").exists())
                .andDo(print());

    }*/
}