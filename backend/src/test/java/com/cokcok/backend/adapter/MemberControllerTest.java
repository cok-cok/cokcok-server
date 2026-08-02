package com.cokcok.backend.adapter;

import com.cokcok.backend.adapter.integration.JwtProvider;
import com.cokcok.backend.application.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(controllers = MemberController.class)
@DisplayName("회원 컨트롤러 테스트")
class MemberControllerTest {

    @Value("${docs.scheme}")
    private String scheme;

    @Value("${docs.host}")
    private String host;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockitoBean
    private MemberService memberService;

    @MockitoBean
    private JwtProvider jwtProvider;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation)
                        .uris().withScheme(scheme)
                        .withHost(host))
                .build();
    }

    @Test
    @DisplayName("닉네임 중복 검사 성공 시 상태 코드 200을 전달받는다.")
    void nicknameDuplicationGetWithSuccessStatusCodeIs200() throws Exception {

        // arrange, act
        mockMvc.perform(get("/api/members/check-nickname")
                        .param("nickname", "cokcok-nickname")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))

                // assert
                .andExpect(status().isOk())

                // docs
                .andDo(document("members-check-nickname-success",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        queryParameters(
                                parameterWithName("nickname").description("중복 검사 닉네임")
                        ),
                        responseFields(
                                fieldWithPath("available").description("닉네임 사용 가능 여부")
                        )));

    }
}