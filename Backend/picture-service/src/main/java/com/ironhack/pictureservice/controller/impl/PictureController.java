package com.ironhack.pictureservice.controller.impl;

import com.ironhack.pictureservice.controller.interfaces.*;
import com.ironhack.pictureservice.service.interfaces.*;
import com.ironhack.pictureservice.utils.dtos.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
public class PictureController implements IPictureController {

    @Autowired
    private IPictureService pictureService;

//    Return all pictures from a user
    @GetMapping("/pics/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public List<PictureDTO> getPicsByUser(@PathVariable String userName){
        return pictureService.getPicsByUser(userName);
    }

//    Return a picture by id
    @GetMapping("/pic/{id}")
    public PictureDTO getPicById(@PathVariable Long id) {
        return pictureService.getPicById(id);
    }

    //    Post a new picture
    @PostMapping("/pic")
    @ResponseStatus(HttpStatus.OK)
    public PictureDTO newPic(@RequestBody @Valid PictureDTO pictureDTO){
        return pictureService.newPic(pictureDTO);
    }

//    Update licks
    @PutMapping("/pic/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PictureDTO newLick(@PathVariable Long id){
        return pictureService.newLick(id);
    }

//    Delete picture
    @DeleteMapping("/pic/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePic(@PathVariable Long id){
        pictureService.removePic(id);
    }
}
