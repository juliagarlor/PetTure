package com.ironhack.edgeservice.service.interfaces;

import com.ironhack.edgeservice.utils.dtos.*;

import java.util.*;

public interface IEdgeService {
    List<PictureDTO> getPicsByUser(String userName);
    PictureDTO newPic(PictureDTO pictureDTO);
    PictureDTO newLick(Long id);
    void removePic(Long id);
}
