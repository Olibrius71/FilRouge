package com.example.filrouge.controllers;

import com.example.filrouge.dto.PostDto;
import com.example.filrouge.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<PostDto>> getPosts(Pageable pageable) {
        return ResponseEntity.ok(postService.getPostsWithPagination(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.createPost(request));
    }

    @PutMapping("/like/{id}")
    public ResponseEntity<PostDto> likePost(@PathVariable Integer id) {
        return ResponseEntity.ok(postService.likePost(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}