package com.lcwd.rating.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.rating.Exceptions.IncompleteDataException;
import com.lcwd.rating.Exceptions.ResourceNotFoundException;
import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.repositorires.RatingRepository;
import com.lcwd.rating.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository repository;

    @Override
    public Rating save(Rating rating) {
        return repository.save(rating);
    }

    @Override
    public List<Rating> get() {
        return repository.findAll();
    }

    @Override
    public Rating get(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rating of given id not found: " + id));
    }

    @Override
    public List<Rating> getRatingsByHotelId(String hotelId) {
        return repository.findByHotelId(hotelId);
    }

    @Override
    public List<Rating> getRatingsByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public Rating update(Rating rating) {
        String id = rating.getId();
        if (id == null)
            throw new IncompleteDataException("Rating Id must be provided to update the Rating");
        delete(id);
        return repository.save(rating);
    }

}
