package edu.fjnu501.crms.service;

import edu.fjnu501.crms.domain.CourseOffering;

import java.util.List;

public interface CurriculumService {

    Object getNewCurriculum(List<CourseOffering> allCourseOffering) throws IllegalAccessException;

}
