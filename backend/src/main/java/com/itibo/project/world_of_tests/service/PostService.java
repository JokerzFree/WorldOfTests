package com.itibo.project.world_of_tests.service;


import com.itibo.project.world_of_tests.model.Post;

import java.util.List;


public interface PostService {
    /**
     * Find all Posts (News) in table
     *
     * @return list of Posts
     */
    List<Post> findAll();

    /**
     * Find one Post by unique id
     *
     * @param id unique number of Post
     * @return Post Object
     */
    Post findOnePostById(Long id);

    /**
     * Save Post to table
     *
     * @param post which would be saved
     */
    void save(Post post);

    /**
     * Delete Post by unique id
     *
     * @param id unique number of Post
     */
    void deletePostById(Long id);
}
