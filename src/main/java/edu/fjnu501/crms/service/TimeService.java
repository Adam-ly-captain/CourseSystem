package edu.fjnu501.crms.service;

import edu.fjnu501.crms.domain.CourseOffering;
import edu.fjnu501.crms.domain.StudentCourseOffering;

import java.util.List;

public interface TimeService {

    boolean checkTimeConflict(CourseOffering courseOffering);

    boolean checkClassroomConflict(CourseOffering courseOffering);

    boolean checkUpdateTimeConflict(CourseOffering courseOffering);

    boolean checkUpdateClassroomConflict(CourseOffering courseOffering);

    void checkStudentSelectionConflict(StudentCourseOffering studentCourseOffering) throws IllegalAccessException;

}
