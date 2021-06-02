package com.blogapp.bloagapp.service.cloud;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service("cloudinary")
public class CloudinaryStorageServiceImpl implements CloudStorageService {


    Cloudinary cloudinary;

    @Autowired
    public CloudinaryStorageServiceImpl(Cloudinary cloudinary){
        this.cloudinary = cloudinary;
    }

    @Override
    public Map<?, ?> uploadImage(File file, Map<?, ?> imageProperties) throws IOException {
        return (Map<?, ?>) cloudinary.uploader().upload(file,imageProperties);
    }


    @Override
    public Map<?, ?> uploadImage(MultipartFile file, Map<?, ?> imageProperties) throws IOException {
        return cloudinary.uploader().upload(file.getBytes(),imageProperties);
    }


}
