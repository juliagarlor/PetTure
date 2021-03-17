package com.ironhack.pictureservice.service.interfaces;

import com.ironhack.pictureservice.utils.dtos.*;
import org.springframework.web.multipart.*;

import java.io.*;

public interface IPictureService {
    PictureDTO getPicById(Long id);
    PictureDTO newPic(MultipartFile file) throws IOException;
    void removePic(Long id);
}
