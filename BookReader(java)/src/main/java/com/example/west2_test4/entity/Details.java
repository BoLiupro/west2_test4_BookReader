package com.example.west2_test4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Details {
    int id;
    String name;
    String intro;
    double marks;
    String picture_path;
    boolean collected;
    String writer;
}