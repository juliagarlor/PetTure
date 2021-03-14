package com.ironhack.pictureservice.controller.interfaces;

import com.ironhack.pictureservice.utils.dtos.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;

public interface IPictureController {
    PictureDTO getPicById(Long id);
    PictureDTO newPic(MultipartFile file) throws IOException;
    void removePic(Long id);
}
