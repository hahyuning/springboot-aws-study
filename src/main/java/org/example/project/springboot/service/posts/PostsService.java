package org.example.project.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.example.project.springboot.domain.posts.PostsRepository;
import org.example.project.springboot.web.dto.PostSaveRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
