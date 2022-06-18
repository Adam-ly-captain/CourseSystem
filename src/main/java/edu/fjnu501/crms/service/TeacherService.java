package edu.fjnu501.crms.service;

import edu.fjnu501.crms.domain.*;

import java.util.List;

public interface TeacherService {

    void getTeacherCurriculum(TeacherCourseOfferingPage teacherCourseOfferingPage);

    void getStudentsInfoByCourseOfferingId(TeacherCourseOfferingPage teacherCourseOffering);

    void getAllTeachersByPage(Page page);

    List<User> getAllTeachers();

    int getTeacherIdByTeacherName(String teacherName);

    Object getTeacherNewCurriculum(int tid) throws IllegalAccessException;

    void getTeacherCurriculum2(TeacherCourseOfferingPage2 teacherCourseOfferingPage2);

}
