package com.ironhack.edgeservice.service.impl;

import com.ironhack.edgeservice.clients.*;
import com.ironhack.edgeservice.service.interfaces.*;
import com.ironhack.edgeservice.utils.classes.*;
import com.ironhack.edgeservice.utils.dtos.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.cloud.circuitbreaker.resilience4j.*;
import org.springframework.cloud.client.circuitbreaker.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class EdgeService implements IEdgeService {

    @Autowired
    private PictureClient pictureClient;

    private final CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();
    private FallBack fallBack = new FallBack();

//    Picture part
    public List<PictureDTO> getPicsByUser(String userName) {
        CircuitBreaker pictureCircuitBreaker = circuitBreakerFactory.create("picture-service");
        return pictureCircuitBreaker.run(() -> pictureClient.getPicsByUser(userName), throwable -> fallBack.listPicFallBack());
    }

    public PictureDTO newPic(PictureDTO pictureDTO) {
        CircuitBreaker pictureCircuitBreaker = circuitBreakerFactory.create("picture-service");
        return pictureCircuitBreaker.run(() -> pictureClient.newPic(pictureDTO), throwable -> fallBack.picFallBack());
    }

    public PictureDTO newLick(Long id) {
        CircuitBreaker pictureCircuitBreaker = circuitBreakerFactory.create("picture-service");
        return pictureCircuitBreaker.run(() -> pictureClient.newLick(id), throwable -> fallBack.picFallBack());
    }

    public void removePic(Long id) {
        pictureClient.removePic(id);
    }
}
