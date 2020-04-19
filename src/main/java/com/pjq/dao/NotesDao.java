package com.pjq.dao;

import com.pjq.pojo.Notes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author pjq
 */
@Mapper
public interface NotesDao {
    List<Notes> selectNotes(int courseId);
}
