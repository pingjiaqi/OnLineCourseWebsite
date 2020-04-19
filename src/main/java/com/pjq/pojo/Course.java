package com.pjq.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Course {
    private int id;
    private String course_name;
    private String teacher;
    private String content;
    private String subject;
    private double price;
    private Date time;
    private String description;
    private String cover_img;
    private int purchased;
    private int star;
}
