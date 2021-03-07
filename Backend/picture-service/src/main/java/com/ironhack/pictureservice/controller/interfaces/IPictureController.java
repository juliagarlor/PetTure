package com.ironhack.pictureservice.controller.interfaces;

import com.ironhack.pictureservice.utils.dtos.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

public interface IPictureController {
    List<PictureDTO> getPicsByUser(String userName);
    PictureDTO newPic(PictureDTO pictureDTO);
    PictureDTO newLick(Long id);
    void removePic(Long id);
}
