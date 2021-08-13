package org.example.project.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.example.project.springboot.domain.posts.Posts;
import org.example.project.springboot.domain.posts.PostsRepository;
import org.example.project.springboot.web.dto.PostSaveRequestDto;
import org.example.project.springboot.web.dto.PostsListResponseDto;
import org.example.project.springboot.web.dto.PostsResponseDto;
import org.example.project.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// service layer
// 컨트롤러와 dao 중간 영역
// 트랜잭션, 도메인 간 순서 보장

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    // 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도 개선
    // 등록, 수정, 삭제 기능이 전혀 없는 서비스 메소드에서 사용
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }
}
