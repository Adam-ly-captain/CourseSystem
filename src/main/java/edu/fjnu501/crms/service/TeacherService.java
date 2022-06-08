package edu.fjnu501.crms.service;

import edu.fjnu501.crms.domain.CourseOffering;
import edu.fjnu501.crms.domain.TeacherCourseOfferingPage;
import edu.fjnu501.crms.domain.User;

import java.util.List;

public interface TeacherService {

    void getTeacherCurriculum(TeacherCourseOfferingPage teacherCourseOfferingPage);

    void getStudentsInfoByCourseOfferingId(TeacherCourseOfferingPage teacherCourseOffering);

}
