package org.example.project.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 메인 클래스
// 스프링 부트의 자동 설정, 스프링 빈 읽기와 생성 자동 설정
// 프로젝트의 최상단에 위치

// JPA Auditing 활성화
@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // 내장 WAS 실행 -> 언제 어디서나 같은 환경에서 스프링 부트 배포 가능
        // 톰캣을 설치할 필요가 없음
        SpringApplication.run(Application.class, args);
    }
}
