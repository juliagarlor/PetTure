package com.ironhack.edgeservice.clients;

import com.ironhack.edgeservice.utils.dtos.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@FeignClient("picture-service")
public interface PictureClient {

//    Return all pictures from a user
    @GetMapping("/pics/{userName}")
    List<PictureDTO> getPicsByUser(@PathVariable String userName);

//    Post a new picture
    @PostMapping("/pic")
    PictureDTO newPic(@RequestBody PictureDTO pictureDTO);

//    Update licks
    @PutMapping("/pic/{id}")
    PictureDTO newLick(@PathVariable Long id);

//    Delete picture
    @DeleteMapping("/pic/{id}")
    void removePic(@PathVariable Long id);
}
