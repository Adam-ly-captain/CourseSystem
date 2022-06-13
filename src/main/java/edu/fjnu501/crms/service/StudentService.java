package edu.fjnu501.crms.service;

import edu.fjnu501.crms.domain.CourseOffering;
import edu.fjnu501.crms.domain.StudentCourseOffering;
import edu.fjnu501.crms.domain.StudentCourseOfferingPage;

import java.util.List;

public interface StudentService {

    void selectCourseOffering(StudentCourseOffering studentCourseOffering) throws IllegalAccessException;

    List<CourseOffering> getCourseOfferingsByStudentId(int sid);

    void cancelCourseOffering(StudentCourseOffering studentCourseOffering) throws IllegalAccessException;

    void getAllStudentCourseOffering(StudentCourseOfferingPage studentCourseOfferingPage);

    void getCourseOfferingsByStudentIdAndPage(StudentCourseOfferingPage studentCourseOfferingPage);

    Object getCurriculum(int sid) throws IllegalAccessException;

}
