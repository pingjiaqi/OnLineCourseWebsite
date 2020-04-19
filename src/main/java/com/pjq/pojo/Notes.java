package com.pjq.pojo;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author pjq
 */
@Data
public class Notes {
    private int id;
    private String username;
    private int course_id;
    private String note;
    private Date time;
}
