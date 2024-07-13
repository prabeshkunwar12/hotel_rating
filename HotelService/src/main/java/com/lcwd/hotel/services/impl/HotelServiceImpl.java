package com.lcwd.hotel.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.exceptions.IncompleteDataException;
import com.lcwd.hotel.exceptions.ResourceNotFoundException;
import com.lcwd.hotel.repository.HotelRepository;
import com.lcwd.hotel.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository repository;

    @Override
    public Hotel save(Hotel hotel) {
        hotel.setId(UUID.randomUUID().toString());
        return repository.save(hotel);
    }

    @Override
    public List<Hotel> get() {
        return repository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with given id not found"));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public Hotel update(Hotel hotel) {
        String id = hotel.getId();
        if (id == null)
            throw new IncompleteDataException("Id is mandatory for the update");
        delete(id);
        return repository.save(hotel);
    }

}
