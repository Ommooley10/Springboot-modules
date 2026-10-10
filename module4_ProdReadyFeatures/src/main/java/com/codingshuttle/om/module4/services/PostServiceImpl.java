package com.codingshuttle.om.module4.services;

import com.codingshuttle.om.module4.dto.PostDTO;
import com.codingshuttle.om.module4.entities.PostEntity;
import com.codingshuttle.om.module4.exceptions.ResourceNotFoundException;
import com.codingshuttle.om.module4.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long postId) {
        PostEntity postEntity = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id "+postId));
        return modelMapper.map(postEntity, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO inputPost, Long postId) {
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);

        if(postRepository.existsById(postId)){
            postEntity.setId(postId);
            PostEntity savedPostEntity = postRepository.save(postEntity);
            return modelMapper.map(savedPostEntity, PostDTO.class);
        }

        postEntity.setId(null);
        PostEntity savedPostEntity = postRepository.save(postEntity);
        return modelMapper.map(savedPostEntity, PostDTO.class);
    }
}
