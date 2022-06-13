package edu.fjnu501.crms.service;

import edu.fjnu501.crms.domain.CourseOffering;
import edu.fjnu501.crms.domain.Page;
import edu.fjnu501.crms.domain.StudentCourseOffering;

import java.util.List;

public interface CourseOfferingService {

    void addCourseOffering(CourseOffering courseOffering) throws IllegalAccessException;

    List<CourseOffering> getAllCourseOfferingByTeacherId(int tid);

    List<CourseOffering> getAllCourseOfferingByClassroom(String classroom);

    void updateCourseOffering(CourseOffering courseOffering) throws IllegalAccessException;

    List<CourseOffering> getOthersCourseOfferingByTeacherId(int tid, int courseOfferingId);

    List<CourseOffering> getOthersCourseOfferingByClassroom(String classroom, int courseOfferingId);

    void getAllCourseOfferingByPage(Page page);

    List<CourseOffering> getAllCourseOfferingByStudentId(int sid);

    void selectStudentCourseOffering(StudentCourseOffering studentCourseOffering) throws IllegalAccessException;

    CourseOffering getCourseOfferingByCourseOfferingId(int courseOfferingId);

    void cancelCourseOffering(StudentCourseOffering studentCourseOffering);

    List<CourseOffering> getAllCourseOffering();

    void updateClassroom(String originClassroomName, String destClassroomName);

}
