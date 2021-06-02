package com.blogapp.bloagapp.service.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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


    @Test
    void uploadMultiPartFileImageFileTest() {
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


}