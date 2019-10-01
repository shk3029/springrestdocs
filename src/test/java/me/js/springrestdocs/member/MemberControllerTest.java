package me.js.springrestdocs.member;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberControllerTest {
    @Rule // Spring Rest Docs는 다양한 프레임워크를 지원함 (Junit 기반 테스트로 진행하므로 해당 객체로 테스트 진행)
    public JUnitRestDocumentation jUnitRestDocumentation = new JUnitRestDocumentation();

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    private RestDocumentationResultHandler document;

    @Before
    public void setUp() {
        // 테스트 클래스의 이름, 메서드 이름 기반으로 디렉토리 경로를 설정해서 snippets를 생성
        this.document = document(
          "{class-name}/{method-name}",
                    preprocessResponse(prettyPrint())
        );
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.jUnitRestDocumentation))
                .alwaysDo(document) // 모든 mockMvc 테스트에 대한 snippets 생성
                .build();
    }

    @Test
    public void member_test() throws Exception {
        mockMvc.perform(post("/members").accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andDo(document.document(
                    responseFields(
                            fieldWithPath("id").description("아이디"),
                            fieldWithPath("name").description("이름")
                    )
                )
        );
    }
}










