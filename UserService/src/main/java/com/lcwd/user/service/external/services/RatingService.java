package com.lcwd.user.service.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.lcwd.user.service.entities.Rating;

@FeignClient(name = "RatingService")
public interface RatingService {
    @GetMapping("/ratings/{id}")
    ResponseEntity<Rating> getById(@PathVariable String id);

    @GetMapping("/ratings")
    ResponseEntity<List<Rating>> getAll();

    @GetMapping("/ratings/user/{id}")
    ResponseEntity<List<Rating>> getByUserId(@PathVariable String id);

    @PostMapping("/ratings")
    ResponseEntity<Rating> create(Rating rating);

    @PutMapping("/ratings/update")
    ResponseEntity<Rating> update(Rating rating);

    @DeleteMapping("/ratings/{id}")
    void delete(@PathVariable String id);
}
