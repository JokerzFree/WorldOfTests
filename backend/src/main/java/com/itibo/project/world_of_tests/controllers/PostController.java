package com.itibo.project.world_of_tests.controllers;

import com.itibo.project.world_of_tests.entity.PostEntity;
import com.itibo.project.world_of_tests.helpers.CurrentUser;
import com.itibo.project.world_of_tests.model.Post;
import com.itibo.project.world_of_tests.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    private final PostService postService;
    private final CurrentUser currentUser;

    @Autowired
    public PostController(PostService postService, CurrentUser currentUser){
        this.postService = postService;
        this.currentUser = currentUser;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllPosts() {
        List<Post> allPosts =  postService.findAll();
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.findOnePostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addNewPost(@RequestBody PostEntity postEntity) {
        Post post = toPost(postEntity);
        postService.save(post);
        return new ResponseEntity<>(postEntity.getTitle(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePostById(@PathVariable Long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    private Post toPost(PostEntity postEntity) {
        Post post = new Post();
        post.setTitle(postEntity.getTitle());
        post.setSubtitle(postEntity.getSubtitle());
        post.setContent(postEntity.getContent());
        post.setDate(LocalDate.now().toString());
        post.setAuthor(currentUser.getCurrentUser().getId());
        return post;
    }



}
