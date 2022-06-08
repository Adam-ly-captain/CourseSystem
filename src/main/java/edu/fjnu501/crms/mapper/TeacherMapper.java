package edu.fjnu501.crms.mapper;

import edu.fjnu501.crms.domain.User;

import java.util.List;

public interface TeacherMapper {

    List<User> getStudentsInfoByCourseOfferingId(int courseOfferingId);

}
