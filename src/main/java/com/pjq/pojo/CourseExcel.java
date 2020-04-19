package com.pjq.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author pjq
 */
@Data
public class CourseExcel implements Serializable, Comparable {
    private String courseName;
    private String content;
    private String subject;
    private int price;
    private String contentName;
    private int sequence;
    private String lesson;
    private int time;
    private String description;
    private String videoUrl;

    @Override
    public int compareTo(Object o) {
        CourseExcel courseExcel= (CourseExcel) o;
        return this.getSequence()-courseExcel.getSequence();
    }
}
