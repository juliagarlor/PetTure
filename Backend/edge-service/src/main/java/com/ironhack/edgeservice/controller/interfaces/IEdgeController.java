package com.ironhack.edgeservice.controller.interfaces;

import com.ironhack.edgeservice.utils.dtos.*;

import java.util.*;

public interface IEdgeController {
    List<PictureDTO> getPicsByUser(String userName);
    PictureDTO newPic(PictureDTO pictureDTO);
    PictureDTO newLick(Long id);
    void removePic(Long id);
}
