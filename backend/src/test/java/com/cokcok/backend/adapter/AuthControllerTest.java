package com.cokcok.backend.adapter;

import com.cokcok.backend.adapter.integration.JwtProvider;
import com.cokcok.backend.adapter.integration.SecurityConfig;
import com.cokcok.backend.application.AuthService;
import com.cokcok.backend.application.request.AuthLoginServiceRequest;
import com.cokcok.backend.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(controllers = AuthController.class)
@Import(SecurityConfig.class)
@DisplayName("인증 컨트롤러 테스트")
class AuthControllerTest {

    @Value("${docs.scheme}")
    private String scheme;

    @Value("${docs.host}")
    private String host;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AuthService authService;

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
    @DisplayName("로그인 성공 시 상태 코드 200을 전달받는다.")
    void serviceLoginPostWithSuccessStatusCodeIs200() throws Exception {

        // arrange
        String email = "cokcok.test@gmail.com";
        String password = "!Password123";
        AuthLoginRequest request = AuthLoginRequest.of(email, password);

        Long expectId = 1L;
        String expectEmail = "cokcok.test@gmail.com";
        String expectPassword = "!Password123";
        String expectNickname = "cokcok-nickname";
        Member expectMember = Member.of(expectId, expectEmail, expectPassword, expectNickname);
        String expectAccessToken = "aospdjfasdjfl4fwncpamcsbgoawihrbb12nsdvkasb999asdhbsdfhb";

        BDDMockito
                .given(authService.login(any(AuthLoginServiceRequest.class)))
                .willReturn(expectMember);

        BDDMockito
                .given(jwtProvider.createToken(any(String.class), any(String.class)))
                .willReturn(expectAccessToken);

        // act
        mockMvc.perform(post("/api/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))

                // assert
                .andExpect(status().isOk())

                // docs
                .andDo(document("auth-login-success",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("email").description("로그인 이메일"),
                                fieldWithPath("password").description("로그인 비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("user.id").type(JsonFieldType.NUMBER).description("유저 식별 정보"),
                                fieldWithPath("user.nickname").type(JsonFieldType.STRING).description("유저 닉네임"),
                                fieldWithPath("accessToken").type(JsonFieldType.STRING).description("인증, 인가 토큰")
                        )));
    }
}