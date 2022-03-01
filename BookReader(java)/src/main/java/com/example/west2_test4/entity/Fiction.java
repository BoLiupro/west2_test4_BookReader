package com.example.west2_test4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.sql.Blob;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Fiction {
    int id;
    String name;
    String intro;
    double marks;
    String text;
    String picture_path;
    String writer;
}
