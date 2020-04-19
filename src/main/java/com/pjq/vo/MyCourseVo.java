package com.pjq.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MyCourseVo {
    private int courseId;
    private String courseName;
    private Double progress;
    private Date addTime;
    private String Teacher;
    private String imgUrl;
}
