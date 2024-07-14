package com.lcwd.rating.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("ratings")
public class Rating {
    @Id
    private String id;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;
}
