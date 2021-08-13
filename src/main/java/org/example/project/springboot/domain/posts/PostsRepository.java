package org.example.project.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// repository layer
// 데이터베이스와 같이 데이터 저장소에 접근하는 영역 (dao)

// 인터페이스 생성 후, JpaRepository<Entity 클래스, pk 타입>을 상속하면 기본적인 crud 메서드 자동 생성
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
