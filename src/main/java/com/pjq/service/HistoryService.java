package com.pjq.service;

import com.pjq.dao.HistoryDao;
import com.pjq.pojo.History;
import com.pjq.pojo.Lecture;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService {

    @Resource
    private HistoryDao historyMapper;

    @Resource
    private LectureService lectureService;

    public void history(String userName,String lectureUrl){
        Lecture lecture=lectureService.findLecture(lectureUrl);
        List<History> histories=showAllHistory(userName);
        List<Integer> lectureIds=new ArrayList<>();
        for(History history:histories){
            lectureIds.add(history.getLecture_id());
        }
        if(!lectureIds.contains(lecture.getId())){
            historyMapper.insertIntoHistory(userName,lecture.getId());
        }
    }

    public List<History> showAllHistory(String userName){
        return historyMapper.selectAllHistory(userName);
    }
}
