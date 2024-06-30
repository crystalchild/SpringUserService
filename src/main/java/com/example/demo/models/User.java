package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Data
@AllArgsConstructor
public class User {
    UUID id;
    String userName;
    Optional<Date> createdDate;
}
