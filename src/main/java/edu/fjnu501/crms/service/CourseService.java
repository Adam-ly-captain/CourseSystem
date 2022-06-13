package edu.fjnu501.crms.service;

import edu.fjnu501.crms.domain.Course;
import edu.fjnu501.crms.domain.Page;

public interface CourseService {

    void addCourse(Course course) throws IllegalAccessException;

    void updateCourse(Course course) throws IllegalAccessException;

    void getCourseByPage(Page page);

    boolean checkCourseName(String courseName);

    int getCourseIdByCourseName(String courseName);

}
