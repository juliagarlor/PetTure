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
import java.util.zip.*;

@Service
public class PictureService implements IPictureService {

    @Autowired
    private PictureRepository pictureRepository;

    public PictureDTO getPicById(Long id) {
        Picture pic = checkId(id);
        PictureDTO output = new PictureDTO(pic.getPictureName(), pic.getType(), decompressBytes(pic.getPic()));
        output.setPicId(pic.getId());
        return output;
    }

    public PictureDTO newPic(MultipartFile file) throws IOException {
        Picture newPic = new Picture(file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
        if (newPic.getPictureName().length() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A picture must have a name");
        } else if(newPic.getType().length() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A picture must have a type");
        }
        PictureDTO output = new PictureDTO(pictureRepository.save(newPic));

        return output;
    }

    public void removePic(Long id) {
        Picture picToRemove = checkId(id);
        pictureRepository.delete(picToRemove);
    }

    public void removePicsByUser(List<Long> picsId) {
        for (Long id : picsId){
            removePic(id);
        }
    }

    public Picture checkId(Long id){
        Optional<Picture> output = pictureRepository.findById(id);

        if (output.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not a picture with such an ID");
        }
        return output.get();
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data) {
        if (data.length == 0){
            return data;
        }
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException ioe) {
        }
        return outputStream.toByteArray();
    }
}
