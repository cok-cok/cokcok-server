package com.cokcok.backend.adapter;

import com.cokcok.backend.adapter.integration.JwtProvider;
import com.cokcok.backend.application.MailVerificationService;
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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(controllers = MailVerificationController.class)
@DisplayName("메일 인증 컨트롤러 테스트")
class MailVerificationControllerTest {

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
    private MailVerificationService mailVerificationService;

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
    @DisplayName("인증 메일 전송 성공 시 상태 코드 200을 전달받는다.")
    void sendMailVerificationCodePostWithSuccessStatusCodeIs200() throws Exception {

        // arrange
        String email = "cokcok.test@gmail.com";
        MailVerificationCodeRequest request = new MailVerificationCodeRequest(email);

        // act
        mockMvc.perform(post("/api/mail/auth/verification-code")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))

                // assert
                .andExpect(status().isOk())

                // docs
                .andDo(document("mail-auth-verification-code",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("email").description("인증 메일 전송 주소")
                        ),
                        responseFields(
                                fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("인증 메일 전송 성공 여부")
                        )));
    }
}