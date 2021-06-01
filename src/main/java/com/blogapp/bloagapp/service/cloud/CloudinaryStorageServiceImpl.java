package com.blogapp.bloagapp.service.cloud;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Map<Object, Object> uploadImage(File file, Map<Object, Object> imageProperties) throws IOException {
       Map<Object, Object>  result = cloudinary.uploader().upload(file,imageProperties);
        return result;
    }


}
