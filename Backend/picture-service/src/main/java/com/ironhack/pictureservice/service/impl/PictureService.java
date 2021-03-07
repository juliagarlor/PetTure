package com.ironhack.pictureservice.service.impl;

import com.ironhack.pictureservice.model.*;
import com.ironhack.pictureservice.repository.*;
import com.ironhack.pictureservice.service.interfaces.*;
import com.ironhack.pictureservice.utils.dtos.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import java.util.*;

@Service
public class PictureService implements IPictureService {

    @Autowired
    private PictureRepository pictureRepository;

    public List<PictureDTO> getPicsByUser(String userName) {
        List<Picture> pictures = pictureRepository.findByUserName(userName);
        List<PictureDTO> output = new ArrayList<>();

        for(Picture pic : pictures){
            output.add(new PictureDTO(pic));
        }

        return output;
    }

    public PictureDTO newPic(PictureDTO pictureDTO) {
        Picture newPic = new Picture(pictureDTO);
        pictureRepository.save(newPic);

        return new PictureDTO(newPic);
    }

    public PictureDTO newLick(Long id) {
        Picture picToUpdate = checkId(id);

        int licks = picToUpdate.getLicks();
        picToUpdate.setLicks(licks + 1);
        pictureRepository.save(picToUpdate);

        return new PictureDTO(picToUpdate);
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
