package com.ironhack.edgeservice.clients;

import com.ironhack.edgeservice.utils.dtos.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import java.util.*;

@FeignClient("picture-service")
public interface PictureClient {

//    Return a picture by id
    @GetMapping("/pic/{id}")
    PictureDTO getPicById(@PathVariable Long id);

//    Post a new picture
    @PostMapping(value = "/pic", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    PictureDTO newPic(@RequestPart("myFile") MultipartFile file);

//    Delete picture
    @DeleteMapping("/pic/{id}")
    void removePic(@PathVariable Long id);
}
