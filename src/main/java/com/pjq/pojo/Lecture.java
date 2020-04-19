package com.pjq.pojo;

import lombok.Data;

/**
 * @author pjq
 */
@Data
public class Lecture {
    private int id;
    private int course_id;
    private int content_list_id;
    private String lesson;
    private String url;
    private int times;
    private int likes;
}
