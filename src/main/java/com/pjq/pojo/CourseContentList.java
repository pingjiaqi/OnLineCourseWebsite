package com.pjq.pojo;

import lombok.Data;
/**
 * @author 13457
 */

@Data
public class CourseContentList implements Comparable{
    private int id;

    private int course_id;

    private String course_content_list;

    private int sequence;


    @Override
    public int compareTo(Object o) {
        CourseContentList courseContentList= (CourseContentList) o;
        return this.sequence-courseContentList.sequence;
    }
}
