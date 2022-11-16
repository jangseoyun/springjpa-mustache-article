package hospitaljpa.mustache.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hospitaljpa.mustache.domain.dto.ArticleRequest;
import hospitaljpa.mustache.domain.dto.ArticleResponse;
import hospitaljpa.mustache.service.ArticleService;
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

@WebMvcTest(ArticleRestController.class)
@DisplayName("ArticleRestControllerTest")
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ArticleService articleService;


    @DisplayName("한개의 게시물을 잘 가지고 오는지")
    @Test
    void getArticleOne() throws Exception {

        ArticleResponse articleResponse = new ArticleResponse(
                1L
                , "안녕하세요"
                , "감사합니다"
        );

        //given
        given(articleService.getArticleOne(1L))
                .willReturn(articleResponse);


        //when
        Long id = 1L;
        String url = String.format("/api/v1/article/%d", id);

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", "안녕하세요").exists())
                .andDo(print());

        verify(articleService).getArticleOne(id);// getHospital()메소드의 호출이 있었는지 확인
    }

    @DisplayName("article 등록 체크")
    @Test
    void articleSave() throws Exception {
        objectMapper.writeValueAsBytes(new ArticleRequest("제목이닷", "내용이다앗"));
    }
}