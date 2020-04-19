package com.pjq.service;

import com.pjq.dao.NotesDao;
import com.pjq.pojo.Notes;
import com.pjq.vo.NotesVo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope("prototype")
public class NotesService {
    @Resource
    NotesDao notesMapper;

    @Resource
    UserService userService;

    public List<NotesVo> showAllNotes(int courseId){
        List<NotesVo> notesVos=new ArrayList<>();
        List<Notes> notes=notesMapper.selectNotes(courseId);
        for(Notes notes1:notes){
            NotesVo notesVo=new NotesVo();
            notesVo.setNote(notes1.getNote());
            notesVo.setTime(notes1.getTime());
            notesVo.setUserName(notes1.getUsername());
            notesVo.setAvatar(userService.userMessage(notes1.getUsername()).getAvatar());
            notesVos.add(notesVo);
        }
        return notesVos;
    }

}
