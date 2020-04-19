package com.pjq.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Reply {

    private String reply_name;
    private String reply_content;
    private Date time;
}
