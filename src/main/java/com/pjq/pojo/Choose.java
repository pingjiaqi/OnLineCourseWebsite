package com.pjq.pojo;

import lombok.Data;

@Data
public class Choose implements Comparable {
    private String topic;
    private String A;
    private String B;
    private String C;
    private String D;
    private String answer;
    private int content_list_id;
    private int course_id;

    @Override
    public int compareTo(Object o) {
        Choose choose= (Choose) o;
        return this.content_list_id-choose.getContent_list_id();
    }
}
