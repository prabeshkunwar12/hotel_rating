package com.lcwd.hotel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.save(hotel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> get(@PathVariable String id) {
        return ResponseEntity.ok(hotelService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> get() {
        return ResponseEntity.ok(hotelService.get());
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        hotelService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/update")
    public ResponseEntity<Hotel> delete(@RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelService.update(hotel));
    }
}
