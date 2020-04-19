package com.pjq.vo;

import com.pjq.pojo.Lecture;
import lombok.Data;

import java.util.List;

/**
 * @author 13457
 */
@Data
public class CourseContentAndLectureVo {
    private int allTime;

    private String course_content_list;

    private int lectureNumber;

    private List<Lecture> lectureList;
}
