package edu.fjnu501.crms.service;

import edu.fjnu501.crms.domain.CourseOffering;
import edu.fjnu501.crms.domain.TeacherCourseOfferingPage2;

import java.util.List;

public interface CurriculumService {

    Object getNewCurriculum(List<CourseOffering> allCourseOffering) throws IllegalAccessException;

    Object getNewCurriculum2(List<CourseOffering> allCourseOffering);

    List<CourseOffering> getTeacherCurriculumByTeacherName(String teacherName);

}
