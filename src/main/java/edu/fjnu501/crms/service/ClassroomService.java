package edu.fjnu501.crms.service;

import edu.fjnu501.crms.domain.Classroom;
import edu.fjnu501.crms.domain.Page;
import edu.fjnu501.crms.domain.PageSearch;

import java.util.List;

public interface ClassroomService {

    void addClassroom(Classroom classroom) throws IllegalAccessException;

    void updateClassroom(Classroom classroom) throws IllegalAccessException;

    String getClassroomNameByClassroomId(int classroomId);

    void deleteClassroom(String classroomName) throws IllegalAccessException;

    List<Classroom> getAllClassroom();

    void getClassroomByPage(Page page);

    void searchClassroom(PageSearch pageSearch);

}
