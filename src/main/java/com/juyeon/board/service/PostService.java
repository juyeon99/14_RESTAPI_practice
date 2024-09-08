package com.juyeon.board.service;

import org.modelmapper.ModelMapper;
import com.juyeon.board.domain.dto.PostDTO;
import com.juyeon.board.domain.entity.Post;
import com.juyeon.board.global.PostNotFoundException;
import com.juyeon.board.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
//    private final ModelMapper modelMapper;

    @Transactional
    public void registerPost(PostDTO newPost) {
        Post post = Post.builder()
                .postId(newPost.getPostId())
                .title(newPost.getTitle())
                .content(newPost.getContent())
                .build();

        postRepository.save(post);
    }

    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostByPostId(long postId) throws PostNotFoundException {
        Post post =  postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("존재하지 않는 게시글입니다."));

        return post;
    }

    // 게시글 수정
    @Transactional
    public void updatePost(long postId, PostDTO postDTO) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));

        // 수정
//        modelMapper.map(postDTO, post);

        Post updatedPost = Post.builder()
                .postId(post.getPostId())
                .title(postDTO.getTitle() != null ? postDTO.getTitle() : post.getTitle())
                .content(postDTO.getContent() != null ? postDTO.getContent() : post.getContent())
                .comments(post.getComments())
                .build();

        // 저장
        postRepository.save(updatedPost);
    }

    public boolean deletePost(long postNo) {
        try {
            if (postRepository.existsById(postNo)) {
                postRepository.deleteById(postNo);
                return true; // 게시글 삭제 성공
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}