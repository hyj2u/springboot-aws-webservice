package com.web.toyproject.springboot.domain.posts;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }
    @Test
    public void select(){
        String title="제목";
        String content ="본문";
        postsRepository.save(Posts.builder().title(title).content(content).author("hyj2u@neowiz.com").build());

        List<Posts> postsList = postsRepository.findAll();
        Posts posts = postsList.get(0);
        Assertions.assertThat(posts.getTitle()).isEqualTo(title);
        Assertions.assertThat(posts.getContent()).isEqualTo(content);
    }
    @Test
    public void BaseTimeEntityTest(){
        LocalDateTime now = LocalDateTime.of(2020,3,12,0,0,0);
        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());
        List<Posts> postsList = postsRepository.findAll();
        Posts posts =postsList.get(0);
        System.out.println("createDate="+posts.getCreated_Date()+", modifiedDate="+posts.getModified_Date());

        Assertions.assertThat(posts.getCreated_Date()).isAfter(now);
        Assertions.assertThat(posts.getModified_Date()).isAfter(now);
    }
}