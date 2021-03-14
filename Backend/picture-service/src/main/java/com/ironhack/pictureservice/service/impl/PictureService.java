package com.ironhack.pictureservice.service.impl;

import com.ironhack.pictureservice.model.*;
import com.ironhack.pictureservice.repository.*;
import com.ironhack.pictureservice.service.interfaces.*;
import com.ironhack.pictureservice.utils.dtos.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;
import org.springframework.web.server.*;

import java.io.*;
import java.util.*;

@Service
public class PictureService implements IPictureService {

    @Autowired
    private PictureRepository pictureRepository;

    public PictureDTO getPicById(Long id) {
        Picture pic = checkId(id);
        return new PictureDTO(pic);
    }

    public PictureDTO newPic(MultipartFile file) throws IOException {
//        System.out.println("holi");
//        System.out.println(file.getOriginalFilename());
        Picture newPic = new Picture(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        PictureDTO output = new PictureDTO(pictureRepository.save(newPic));

        return output;
    }

    public void removePic(Long id) {
        Picture picToRemove = checkId(id);

        pictureRepository.delete(picToRemove);
    }

    public Picture checkId(Long id){
        Optional<Picture> output = pictureRepository.findById(id);

        if (output.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not a picture with such an ID");
        }
        return output.get();
    }
}
