package com.example.filrouge.services;

import com.example.filrouge.dto.PostDto;
import com.example.filrouge.entities.Post;
import com.example.filrouge.entities.User;
import com.example.filrouge.repositories.PostRepository;
import com.example.filrouge.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<PostDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Page<PostDto> getPostsWithPagination(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    public PostDto getPostById(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        return mapToResponse(post);
    }

    @Transactional
    public PostDto createPost(PostDto request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));

        Post post = new Post();
        post.setContent(request.getContent());
        post.setUser(user);
        post.setId((int) (Math.random() * 999999999) + 1);

        Post savedPost = postRepository.save(post);
        return mapToResponse(savedPost);
    }

    @Transactional
    public PostDto likePost(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));

        post.setLikesCount(post.getLikesCount() + 1);
        Post updatedPost = postRepository.save(post);

        return mapToResponse(updatedPost);
    }

    @Transactional
    public void deletePost(Integer id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post not found with id: " + id);
        }
        postRepository.deleteById(id);
    }

    private PostDto mapToResponse(Post post) {
        PostDto response = new PostDto();
        response.setId(post.getId());
        response.setContent(post.getContent());
        response.setLikesCount(post.getLikesCount());
        response.setUserId(post.getUser().getId());
        return response;
    }
}