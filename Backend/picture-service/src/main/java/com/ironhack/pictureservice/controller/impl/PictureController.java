package com.ironhack.pictureservice.controller.impl;

import com.ironhack.pictureservice.controller.interfaces.*;
import com.ironhack.pictureservice.service.interfaces.*;
import com.ironhack.pictureservice.utils.dtos.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.PATCH})
public class PictureController implements IPictureController {

    @Autowired
    private IPictureService pictureService;

//    Return a picture by id
    @GetMapping("/pic/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PictureDTO getPicById(@PathVariable Long id) {
        return pictureService.getPicById(id);
    }

//    Post a new picture
    @PostMapping(value = "/pic", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PictureDTO newPic(@RequestPart("myFile") MultipartFile file) throws IOException {
        return pictureService.newPic(file);
    }

//    Delete picture
    @DeleteMapping("/pic/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePic(@PathVariable Long id){
        pictureService.removePic(id);
    }

//    Delete all pictures from a user
    @DeleteMapping("/pics")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePicsByUser(@RequestBody List<Long> picsId){
        pictureService.removePicsByUser(picsId);
    }
}
