package com.pjq.pojo;


import lombok.Data;

import java.util.Date;

@Data
public class MyCourse {
    private String username;
    private int course_id;
    private Double progress;
    private Date add_time;
}
