package com.example.west2_test4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.text.SimpleDateFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class History {
    int id;
    String name;
    String intro;
    double marks;
    String picture_path;
    boolean collected;
    String writer;
    String last_read_time;
}