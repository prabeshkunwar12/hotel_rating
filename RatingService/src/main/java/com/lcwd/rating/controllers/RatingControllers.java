package com.lcwd.rating.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingControllers {
    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> save(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.save(rating));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> get() {
        return ResponseEntity.ok(ratingService.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> get(@PathVariable String id) {
        return ResponseEntity.ok(ratingService.get(id));
    }

    @GetMapping("/hotel/{id}")
    public ResponseEntity<List<Rating>> getByHotelId(@PathVariable String id) {
        return ResponseEntity.ok(ratingService.getRatingsByHotelId(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Rating>> getByUserId(@PathVariable String id) {
        return ResponseEntity.ok(ratingService.getRatingsByUserId(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        ratingService.delete(id);
        return ResponseEntity.ok("Deleted the Rating: " + id);
    }

    @PutMapping("/update")
    public ResponseEntity<Rating> update(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ratingService.update(rating));
    }
}
