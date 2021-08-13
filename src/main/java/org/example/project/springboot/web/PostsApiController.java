package org.example.project.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.project.springboot.domain.posts.Posts;
import org.example.project.springboot.service.posts.PostsService;
import org.example.project.springboot.web.dto.PostSaveRequestDto;
import org.example.project.springboot.web.dto.PostsResponseDto;
import org.example.project.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

// web layer
// 컨트롤러와 jsp 등의 뷰 템플릿 영역
// 필터, 인터셉터, 컨트롤러 어드바이스 등 외부 요청과 응답에 대한 전반적인 영역

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
