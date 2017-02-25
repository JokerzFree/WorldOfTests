package com.itibo.project.world_of_tests.service;


import com.itibo.project.world_of_tests.model.Post;

import java.util.List;


public interface PostService {
    List<Post> findAll();
    Post findOnePostById(Long id);
    void save(Post post);
    void deletePostById(Long id);
}
