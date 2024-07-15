package com.lcwd.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lcwd.user.service.entities.Hotel;

@FeignClient(name = "HotelService")
public interface HotelService {
    @GetMapping("/hotels/{id}")
    ResponseEntity<Hotel> get(@PathVariable String id);

}
