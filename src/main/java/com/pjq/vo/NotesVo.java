package com.pjq.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author pjq
 */
@Data
public class NotesVo {
    private String userName;
    private String avatar;
    private String note;
    private Date time;
}
