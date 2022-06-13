package edu.fjnu501.crms.mapper;

import edu.fjnu501.crms.domain.Classroom;

import java.util.List;

public interface ClassroomMapper {

    void addClassroom(Classroom classroom);

    Classroom getClassroomByClassroomName(String classroomName);

    void updateClassroom(Classroom classroom);

    String getClassroomNameByClassroomId(int classroomId);

    void deleteClassroom(String classroomName);

    List<Classroom> getAllClassroom();

    List<Classroom> searchClassroom(String keyword);

}
