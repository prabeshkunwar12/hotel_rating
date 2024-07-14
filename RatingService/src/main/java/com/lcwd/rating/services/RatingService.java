package com.lcwd.rating.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lcwd.rating.entities.Rating;

@Service
public interface RatingService {
    Rating save(Rating rating);

    List<Rating> get();

    Rating get(String id);

    List<Rating> getRatingsByHotelId(String hotelId);

    List<Rating> getRatingsByUserId(String userId);

    void delete(String id);

    Rating update(Rating rating);
}
