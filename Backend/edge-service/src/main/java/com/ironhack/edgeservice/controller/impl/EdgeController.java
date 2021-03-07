package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.controller.interfaces.*;
import com.ironhack.edgeservice.service.interfaces.*;
import com.ironhack.edgeservice.utils.dtos.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
public class EdgeController implements IEdgeController {

    @Autowired
    private IEdgeService edgeService;

//    Picture part:
//    Return all pictures from a user
    @GetMapping("/pics/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public List<PictureDTO> getPicsByUser(@PathVariable String userName){
        return edgeService.getPicsByUser(userName);
    }

    //    Post a new picture
    @PostMapping("/pic")
    @ResponseStatus(HttpStatus.OK)
    public PictureDTO newPic(@RequestBody @Valid PictureDTO pictureDTO){
        return edgeService.newPic(pictureDTO);
    }

    //    Update licks
    @PutMapping("/pic/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PictureDTO newLick(@PathVariable Long id){
        return edgeService.newLick(id);
    }

    //    Delete picture
    @DeleteMapping("/pic/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePic(@PathVariable Long id){
        edgeService.removePic(id);
    }
}
