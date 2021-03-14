package com.ironhack.pictureservice.service.interfaces;

import com.ironhack.pictureservice.utils.dtos.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;

public interface IPictureService {
    PictureDTO getPicById(Long id);
    PictureDTO newPic(MultipartFile file) throws IOException;
    void removePic(Long id);
}
