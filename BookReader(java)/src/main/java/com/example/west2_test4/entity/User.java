package com.example.west2_test4.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    int id;
    String password;
    int randCode;
    String collection;
    String history;
}


