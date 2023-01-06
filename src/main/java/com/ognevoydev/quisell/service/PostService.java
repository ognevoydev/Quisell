package com.ognevoydev.quisell.service;

import com.ognevoydev.quisell.model.Post;
import com.ognevoydev.quisell.model.PostDTO;

import java.util.List;
import java.util.UUID;

public interface PostService {

    Post getPostById(UUID postId);
    List<Post> getAllPosts();
    void savePost(Post post);
    void deletePostById(UUID postId);
    boolean isPostOwner(UUID postId, UUID accountId);
    void updatePostById(UUID postId, PostDTO post);

}
