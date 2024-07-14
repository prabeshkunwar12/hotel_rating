package com.lcwd.user.service.services.impl;

import java.util.List;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.IncompleteDataException;
import com.lcwd.user.service.exceptions.ResourseNotFoundException;
import com.lcwd.user.service.repositores.UserRepository;
import com.lcwd.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User create(User user) {
        String userId = UUID.randomUUID().toString();
        user.setId(userId);
        return userRepository.save(user);
    }

    @Override
    public List<User> get() {
        return userRepository.findAll();
    }

    @Override
    public User get(String id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourseNotFoundException("User with given id is not found on server !! id : " + id));
        Rating[] ratingResponse = restTemplate
                .getForObject("http://RatingService/ratings/user/" + id, Rating[].class);
        List<Rating> userRating = Arrays.stream(ratingResponse).toList();
        if (userRating != null) {
            userRating = userRating.stream().map(rating -> {
                ResponseEntity<Hotel> response = restTemplate
                        .getForEntity("http://HotelService/hotels/" + rating.getHotelId(), Hotel.class);
                Hotel hotel = response.getBody();
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());
        }
        user.setRatings(userRating);
        return user;
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user) {
        String id = user.getId();
        if (id == null)
            throw new IncompleteDataException("User id is required for update.");
        delete(id);
        return userRepository.save(user);
    }

}
