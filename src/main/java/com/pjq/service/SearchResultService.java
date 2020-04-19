package com.pjq.service;

import com.pjq.dao.CourseDao;
import com.pjq.dao.PostDao;
import com.pjq.pojo.Course;
import com.pjq.pojo.Post;
import com.pjq.pojo.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("searchResultService")
@Scope("prototype")
public class SearchResultService {

    @Resource
    private CourseDao courseDao;

    @Resource
    private PostDao postDao;

    public Result searchKeyWord(String keyword) {
        Result result = new Result();
        if(keyword.equals("")||keyword==null){
            return result;
        }
        List<Course> courseList = courseDao.findKeyWordCourse(keyword);
        List<Post> postList = postDao.findKeyWordPost(keyword);
        Map resultMap = new HashMap<>();
        resultMap.put("course", courseList);
        resultMap.put("post", postList);
        result.setResult(resultMap);
        return result;
    }


}
