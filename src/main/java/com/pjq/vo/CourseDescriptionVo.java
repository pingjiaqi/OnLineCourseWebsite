package com.pjq.vo;

import lombok.Data;

import java.util.List;

@Data
public class CourseDescriptionVo {
    private int id;
    private int courseId;
    private String courseName;
    private String coverImg;
    private List<String> learn;
    private List<String> demand;
    private String description;
    private List<String> audience;
    private String teacher;
    private String category;
    private int purchased;
    private String teacherImg;

}


