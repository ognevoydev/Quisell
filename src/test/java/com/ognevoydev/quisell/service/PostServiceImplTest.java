package com.ognevoydev.quisell.service;

import com.ognevoydev.quisell.common.exception.HttpStatusException;
import com.ognevoydev.quisell.model.Post;
import com.ognevoydev.quisell.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class PostServiceImplTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Test
    void deletePostById() {
        Post post = new Post();
        post.setTitle("Foo");
        post.setDescription("Foo");
        post.setUsed(false);

        postService.savePost(post);

        assertThrows(HttpStatusException.class, () -> postService.deletePostById(UUID.randomUUID()));

        postService.deletePostById(post.getId());

        assertNotNull(postService.getPostById(post.getId()).getDeletedAt());
    }

    @Test
    void isPostOwner() {
        Post post = new Post();
        post.setTitle("Foo");
        post.setDescription("Foo");
        post.setUsed(false);

        postService.savePost(post);

        assertTrue(postService.isPostOwner(post.getId(), post.getAccountId()));
        assertFalse(postService.isPostOwner(post.getId(), UUID.randomUUID()));
        assertThrows(HttpStatusException.class, () -> postService.isPostOwner(UUID.randomUUID(), post.getAccountId()));
    }
}