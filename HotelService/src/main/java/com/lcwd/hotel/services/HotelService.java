package com.lcwd.hotel.services;

import java.util.List;

import com.lcwd.hotel.entities.Hotel;

public interface HotelService {
    Hotel save(Hotel hotel);

    List<Hotel> get();

    Hotel get(String id);

    void delete(String id);

    Hotel update(Hotel hotel);
}
