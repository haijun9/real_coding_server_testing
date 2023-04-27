package com.cnu.real_coding_server.service.week1.practice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.cnu.real_coding_server.service.valid.PostValidService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class PostValidServiceTest {

    @Autowired
    PostValidService postValidService;

    @DisplayName("test about title & content of post")
    @Test
    void testValidPostIncludeSlang() {
        // given 시나리오
        String testTitle = "비속어가 섞인 제목";
        String testContent = "비속어가 섞인 욕";
        List<String> slangList = List.of("비속어", "비속어2");

        boolean validPost = postValidService.isSlangInclude(slangList, testTitle, testContent);
        // then 검증
        assertThat(validPost).isEqualTo(true);
    }

    @DisplayName("test about title of post")
    @Test
    void testValidPostIncludeSlangInTitle() {
        // given 시나리오
        String testSlangTitle = "비속어가 섞인 제목";
        String testNoneSlangTitle = "비1속어가 없는 제목";
        String testNoneSlangContent = "비1속어가 섞인 욕";
        List<String> slangList = List.of("비속어", "비속어2");

        boolean validSlangTitle = postValidService.isSlangInclude(slangList, testSlangTitle, testNoneSlangContent);
        boolean validNoneSlangTitle = postValidService.isSlangInclude(slangList, testNoneSlangTitle, testNoneSlangContent);
        // then 검증
        assertAll("verify object",
                ()->assertThat(validSlangTitle).isEqualTo(true),
                ()->assertThat(validNoneSlangTitle).isEqualTo(false)
        );
    }

    @DisplayName("test about content of post")
    @Test
    void testValidPostIncludeSlangInContent() {
        // given 시나리오
        String testNoneSlangTitle = "비1속어가 없는 제목";
        String testSlangContent = "비속어가 섞인 욕";
        String testNoneSlangContent = "비1속어가 섞인 욕";
        List<String> slangList = List.of("비속어", "비속어2");

        boolean validSlangContent = postValidService.isSlangInclude(slangList, testNoneSlangTitle, testSlangContent);
        boolean validNoneSlangContent = postValidService.isSlangInclude(slangList, testNoneSlangTitle, testNoneSlangContent);
        // then 검증
        assertAll("verify object",
                ()->assertThat(validSlangContent).isEqualTo(true) ,
                ()->assertThat(validNoneSlangContent).isEqualTo(false)
        );
    }
}
