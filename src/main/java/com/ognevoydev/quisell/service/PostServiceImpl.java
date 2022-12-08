package com.ognevoydev.quisell.service;

import com.ognevoydev.quisell.common.exception.NotFoundException;
import com.ognevoydev.quisell.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;
import com.ognevoydev.quisell.repository.PostRepository;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    @Override
    public Post getPostById(UUID postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(postId, Post.class));
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void savePost(Post post) {
        UUID postId = UUID.randomUUID();
        post.setId(postId);
        postRepository.save(post);
        }

}
