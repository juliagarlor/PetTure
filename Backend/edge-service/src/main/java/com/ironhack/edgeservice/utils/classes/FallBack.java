package com.ironhack.edgeservice.utils.classes;

import com.ironhack.edgeservice.utils.dtos.*;

import java.util.*;

public class FallBack {

    public List<PictureDTO> listPicFallBack(){
        return new ArrayList<>();
    }

    public PictureDTO picFallBack(){
        PictureDTO output = new PictureDTO();
        output.setPictureName("notFound.jpg");
        output.setUserName("banksy");
        return output;
    }
}
