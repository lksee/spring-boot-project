package com.playdata.springbootproject.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void saveAndLoad() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        String author = "playdata";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build());

        // when
        Posts posts = postsRepository.findAll().get(0);

        // then
        assertEquals(posts.getTitle(), title);
        assertEquals(posts.getContent(), content);
        assertEquals(posts.getAuthor(), author);
    }

    @Test
    public void auditingEntity() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        String author = "playdata";
        LocalDateTime now = LocalDateTime.now();

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build());

        // when
        Posts posts = postsRepository.findAll().get(0);

        // then: 생성일시, 수정일시가 데이터 삽입 직전 시간보다 나중이어야 한다.
        assertTrue(posts.getCreatedDate().isAfter(now));
        assertTrue(posts.getModifiedDate().isAfter(now));
    }
}