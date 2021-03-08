package com.ironhack.pictureservice.service.interfaces;

import com.ironhack.pictureservice.utils.dtos.*;

import java.util.*;

public interface IPictureService {
    List<PictureDTO> getPicsByUser(String userName);
    PictureDTO getPicById(Long id);
    PictureDTO newPic(PictureDTO pictureDTO);
    PictureDTO newLick(Long id);
    void removePic(Long id);
}
