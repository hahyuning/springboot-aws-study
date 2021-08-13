package org.example.project.springboot.domain.posts;

import org.assertj.core.api.Assertions;
import org.example.project.springboot.domain.posts.Posts;
import org.example.project.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
// jpa 기능 테스트시 사용
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    // 단위 테스트가 끝날 때마다 수행되는 메서드 지정
    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void post_findAll() {
        // given
        String title = "test";
        String content = "test_content";

        // id 값이 있다면 update, 없다면 insert 쿼리 실행
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("hahyuning")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_enroll() {
        // given
        LocalDateTime now = LocalDateTime.of(2021,8, 14, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println("createDate: " + posts.getCreateDate() + ", modifiedDate: " + posts.getModifiedDate());

        assertThat(posts.getCreateDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
