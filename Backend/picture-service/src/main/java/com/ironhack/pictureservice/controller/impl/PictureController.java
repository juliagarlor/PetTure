package com.ironhack.pictureservice.controller.impl;

import com.ironhack.pictureservice.controller.interfaces.*;
import com.ironhack.pictureservice.service.interfaces.*;
import com.ironhack.pictureservice.utils.dtos.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import javax.validation.*;
import java.io.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.PATCH})
public class PictureController implements IPictureController {

    @Autowired
    private IPictureService pictureService;

//    Return a picture by id
    @GetMapping("/pic/{id}")
    public PictureDTO getPicById(@PathVariable Long id) {
        return pictureService.getPicById(id);
    }

//    Post a new picture
    @PostMapping("/pic")
    @ResponseStatus(HttpStatus.OK)
    public PictureDTO newPic(@RequestParam("myFile") MultipartFile file) throws IOException {
        System.out.println("holi1");
        return pictureService.newPic(file);
    }

//    Delete picture
    @DeleteMapping("/pic/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePic(@PathVariable Long id){
        pictureService.removePic(id);
    }
}
