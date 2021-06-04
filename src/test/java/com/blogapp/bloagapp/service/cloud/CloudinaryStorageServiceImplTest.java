package com.blogapp.bloagapp.service.cloud;

import com.blogapp.bloagapp.web.dto.PostDTO;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest
class CloudinaryStorageServiceImplTest {

    @Autowired @Qualifier("cloudinary")
    CloudStorageService cloudStorageServiceImpl;

    @BeforeEach
    void setUp() {
    }

    @Test
    void uploadImageTest() {
        //Define A File

        File file = new File("C:\\Users\\LATITUDE\\Desktop\\executablesDEV\\bloagapp\\src\\main\\resources\\static\\asset\\images\\author-image1.jpg");
        assertThat(file.exists()).isTrue();

        Map<Object, Object> params = new HashMap<>();
        params.put("public_id", "blog_app/myAuthorImage");
        params.put("overwrite","true");

        try{
        cloudStorageServiceImpl.uploadImage(file,params);
        }
        catch(IOException e){
            log.info("Error Occurred --> {}", e.getMessage());
        }
    }


//    @Test
//    void uploadMultiPartFileImageFileTest() {
//        //Define A File
//
//        File file = new File("C:\\Users\\LATITUDE\\Desktop\\executablesDEV\\bloagapp\\src\\main\\resources\\static\\asset\\images\\author-image1.jpg");
//        assertThat(file.exists()).isTrue();
//
//        Map<Object, Object> params = new HashMap<>();
//        params.put("public_id", "blog_app/myAuthorImage");
//        params.put("overwrite","true");
//
//        try{
//            cloudStorageServiceImpl.uploadImage(file,params);
//        }
//        catch(IOException e){
//            log.info("Error Occurred --> {}", e.getMessage());
//        }
//    }

    @Test
    void uploadMultiPartFileImageFileTest() throws IOException {
        //set data with postSTO
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle("title");
        postDTO.setContent("Content");

        //Define a File Path,and also getting a file from the system
        Path path = Paths.get("C:\\\\Users\\\\LATITUDE\\\\Desktop\\\\executablesDEV\\\\bloagapp\\\\src\\\\main\\\\resources\\\\static\\\\asset\\\\images\\\\author-image1.jpg");

        //Hold the Image Temporary with MultipartFile Object
        MultipartFile multipartFile = new MockMultipartFile("author-image1.jpg", "author-image1.jpg","img/jpeg", Files.readAllBytes(path));
        log.info("Multipart Object created --> {}", multipartFile);

        //Assert That the multiPart File Is not Null
        assertThat(multipartFile).isNotNull();

        //Set the multipartFile that holds ur image to the PostDto
        postDTO.setCoverImageUrl(multipartFile);


        log.info("FileName  --> {}", postDTO.getCoverImageUrl().getOriginalFilename());

        //Upload To Cloudinary with the uploadImageService
        cloudStorageServiceImpl.uploadImage(multipartFile, ObjectUtils.asMap(
                "public_id", "blog_app/"+extractFileName(Objects.requireNonNull(postDTO.getCoverImageUrl().getOriginalFilename()))
        ));
        assertThat(postDTO.getCoverImageUrl().getOriginalFilename()).isEqualTo("author-image1.jpg");
    }

    private String extractFileName(String fileName){
        return fileName.split("\\.")[0];
    }

}