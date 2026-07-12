package com.cokcok.backend.adapter.persistence;

import com.cokcok.backend.BackendApplication;
import com.cokcok.backend.application.required.MemberRepository;
import com.cokcok.backend.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mysql.MySQLContainer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = BackendApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@Testcontainers
@DisplayName("사용자 저장소 테스트")
class MemberJpaRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Container
    @ServiceConnection
    static MySQLContainer mysqlContainer = new MySQLContainer("mysql:8.4")
            .withInitScript("schema.sql");

    @Test
    @DisplayName("사용자를 저장소에 저장할 수 있다.")
    void saveWithMember() {

        // arrange
        String email = "cokcok@cokcok.com";
        String password = "cokcok-password";
        String nickname = "cokcok-nickname";
        Member member = Member.create(email, password, nickname);

        // act
        Member savedMember = memberRepository.save(member);

        // assert
        assertThat(savedMember.getId()).isNotNull();
    }
}