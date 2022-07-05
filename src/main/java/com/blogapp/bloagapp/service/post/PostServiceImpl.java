package com.blogapp.bloagapp.service.post;

import com.blogapp.bloagapp.data.models.Comment;
import com.blogapp.bloagapp.data.models.Post;
import com.blogapp.bloagapp.data.repository.PostRepository;
import com.blogapp.bloagapp.service.cloud.CloudStorageService;
import com.blogapp.bloagapp.web.dto.PostDTO;
import com.blogapp.bloagapp.web.exceptions.PostDoesNotFoundException;
import com.blogapp.bloagapp.web.exceptions.PostObjectIsNullException;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository postRepository;

    @Autowired
    CloudStorageService cloudStorageService;

    @Override
    public Post savePost(PostDTO postDTO) throws PostObjectIsNullException {
        if(postDTO == null){
            throw new PostObjectIsNullException();
        }
        Post post = new Post();
        if(postDTO.getCoverImageUrl() != null && !postDTO.getCoverImageUrl().isEmpty()) {
//            Map<Object,Object> params = new HashMap<>();
//            params.put("public_id","blogapp/"+postDTO.getCoverImageUrl().getName());
//            params.put("overwrite",true);
//            log.info("Image Parameter --> {}", params);
         //Upload an image to Cloudinary
            try {
              Map<?,?> uploadResult =
                cloudStorageService.uploadImage(postDTO.getCoverImageUrl(),
                        ObjectUtils.asMap(
                                "public_id","blogapp/"+extractFileName(Objects.requireNonNull(postDTO.getCoverImageUrl().getOriginalFilename()))
                        ));
              post.setCoverImageUrl(String.valueOf(uploadResult.get("url")));
              log.info("Image url --> {}", uploadResult.get("url"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        log.info("Post object after saving --> {}",post);

        try {
            return postRepository.save(post);
        }catch (DataIntegrityViolationException ex){
            log.info("Exception --> {}", ex.getMessage());
            throw ex; // throws it back to the controller
        }
    }
    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }
    @Override
    public Post updatePost(PostDTO postDTO) {
        return null;
    }
    @Override
    public Post findById(Integer id) throws PostDoesNotFoundException {
           if(Objects.equals(id, null)){
               throw  new NullPointerException("Post Id cannot Be Null");
           }
            Optional<Post> post = postRepository.findById(id);
            if(post.isPresent()){
                return post.get();
            }else{
                throw new PostDoesNotFoundException("Post with Id --> {}");
            }
    }

    @Override
    public void deletePostById(Integer id) {

    }

    @Override
    public Post addCommentToPost(Integer id, Comment comment) {
//        Optional<Post> find
        return null;
    }

    @Override
    public List<Post> findPostInDescOrder() {
        return postRepository.findByOrderByDateCreatedDesc();
    }

    private String extractFileName(String fileName){
        return fileName.split("\\.")[0];
    }
    


    //
    //    ModelMapper modelMap = new ModelMapper();
//        modelMap.map(postDTO, post);
//
//        log.info("Post object after mapping --> {}",post);
}
