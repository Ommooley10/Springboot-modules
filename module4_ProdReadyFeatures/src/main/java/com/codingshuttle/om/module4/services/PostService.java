package com.codingshuttle.om.module4.services;

import com.codingshuttle.om.module4.dto.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);

    PostDTO updatePost(PostDTO inputPost, Long postId);
}
