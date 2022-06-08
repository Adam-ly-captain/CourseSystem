package edu.fjnu501.crms.service.impl;

import edu.fjnu501.crms.domain.CourseOffering;
import edu.fjnu501.crms.domain.StudentCourseOffering;
import edu.fjnu501.crms.service.CourseOfferingService;
import edu.fjnu501.crms.service.TimeService;
import edu.fjnu501.crms.state.StateDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("timeService")
public class TimeServiceImpl implements TimeService {

    @Autowired
    private CourseOfferingService courseOfferingService;

    @Override
    public boolean checkTimeConflict(CourseOffering courseOffering) {
        List<CourseOffering> allCourseOfferingByTeacherId = courseOfferingService.getAllCourseOfferingByTeacherId(courseOffering.getTeacherId());
        if (allCourseOfferingByTeacherId != null && checkTimeConflictCore(allCourseOfferingByTeacherId, courseOffering)) {
            return true;
        }
        return false;
    }

    public boolean checkTimeConflictCore(List<CourseOffering> courseOfferings, CourseOffering courseOffering) {
        for (CourseOffering offering : courseOfferings) {
            if (!(courseOffering.getStartWeek() > offering.getStopWeek() || courseOffering.getStopWeek() < offering.getStartWeek()) && courseOffering.getDayOfWeek() == offering.getDayOfWeek() && courseOffering.getLessonOfDay() == offering.getLessonOfDay()) {
                return true;  // 冲突
            }
        }
        return false;
    }

    public boolean checkClassroomConflict(CourseOffering courseOffering) {
        List<CourseOffering> allCourseOfferingByClassroom = courseOfferingService.getAllCourseOfferingByClassroom(courseOffering.getClassroom());
        if (allCourseOfferingByClassroom != null && checkTimeConflictCore(allCourseOfferingByClassroom, courseOffering)) {
            return true;
        }
        return false;
    }

    public boolean checkUpdateTimeConflict(CourseOffering courseOffering) {
        List<CourseOffering> othersCourseOfferingByTeacherId = courseOfferingService.getOthersCourseOfferingByTeacherId(courseOffering.getTeacherId(), courseOffering.getCourseOfferingId());
        if (othersCourseOfferingByTeacherId != null && checkTimeConflictCore(othersCourseOfferingByTeacherId, courseOffering)) {
            return true;
        }
        return false;
    }

    public boolean checkUpdateClassroomConflict(CourseOffering courseOffering) {
        List<CourseOffering> othersCourseOfferingByClassroom = courseOfferingService.getOthersCourseOfferingByClassroom(courseOffering.getClassroom(), courseOffering.getCourseOfferingId());
        if (othersCourseOfferingByClassroom != null && checkTimeConflictCore(othersCourseOfferingByClassroom, courseOffering)) {
            return true;
        }
        return false;
    }

    @Override
    public void checkStudentSelectionConflict(StudentCourseOffering studentCourseOffering) throws IllegalAccessException {
        List<CourseOffering> allCourseOfferingByStudentId = courseOfferingService.getAllCourseOfferingByStudentId(studentCourseOffering.getSid());
        CourseOffering courseOfferingByCourseOfferingId = courseOfferingService.getCourseOfferingByCourseOfferingId(studentCourseOffering.getCourseOfferingId());
        if (courseOfferingByCourseOfferingId.getMaxNum() <= courseOfferingByCourseOfferingId.getCurrentNum()) {
            throw new IllegalAccessException(StateDesc.COURSE_LIMITED.getDesc());
        } else if (allCourseOfferingByStudentId != null && checkTimeConflictCore(allCourseOfferingByStudentId, courseOfferingByCourseOfferingId)) {
            throw new IllegalAccessException(StateDesc.TIME_CONFLICT.getDesc());
        }
    }

}
