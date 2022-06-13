package edu.fjnu501.crms.mapper;

import edu.fjnu501.crms.domain.Course;

import java.util.List;

public interface CourseMapper {

    void addCourse(Course course);

    void updateCourse(Course course);

    List<Course> getAllCourse();

    Course selectCourseByCourseName(String courseName);

    int getCourseIdByCourseName(String courseName);

}
