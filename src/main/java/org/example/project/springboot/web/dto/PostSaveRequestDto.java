package org.example.project.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.project.springboot.domain.posts.Posts;

// dto
// 계층 간에 데이터 교환을 위한 객체 영역
// 뷰 템플릿 엔진에서 사용될 객체나 repository layer에서 결과로 넘겨준 객체 등
// entity 클래스와 controller에서 쓸 dto는 분리해서 사용해야 함

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
