package edu.fjnu501.crms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu501.crms.domain.CourseOffering;
import edu.fjnu501.crms.domain.Page;
import edu.fjnu501.crms.domain.StudentCourseOffering;
import edu.fjnu501.crms.mapper.CourseOfferingMapper;
import edu.fjnu501.crms.mapper.TeacherMapper;
import edu.fjnu501.crms.service.CourseOfferingService;
import edu.fjnu501.crms.service.CourseService;
import edu.fjnu501.crms.service.TeacherService;
import edu.fjnu501.crms.service.TimeService;
import edu.fjnu501.crms.state.StateDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseOfferingService")
public class CourseOfferingServiceImpl implements CourseOfferingService {

    @Autowired
    private CourseOfferingMapper courseOfferingMapper;

    @Autowired
    private TimeService timeService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Override
    public void addCourseOffering(CourseOffering courseOffering) throws IllegalAccessException {
        int teacherIdByTeacherName = teacherService.getTeacherIdByTeacherName(courseOffering.getTeacherName());
        int courseIdByCourseName = courseService.getCourseIdByCourseName(courseOffering.getCourseName());
        courseOffering.setTeacherId(teacherIdByTeacherName);
        courseOffering.setCourseId(courseIdByCourseName);
        teacherTimeCheck(courseOffering);
        courseOfferingMapper.addCourseOffering(courseOffering);
    }

    private void teacherTimeCheck(CourseOffering courseOffering) throws IllegalAccessException {
        if (timeService.checkTimeConflict(courseOffering)) {
            throw new IllegalAccessException(StateDesc.TIME_CONFLICT.getDesc());
        } else if (timeService.checkClassroomConflict(courseOffering)) {
            throw new IllegalAccessException(StateDesc.CLASSROOM_CONFLICT.getDesc());
        }
    }

    public List<CourseOffering> getAllCourseOfferingByTeacherId(int tid) {
        return courseOfferingMapper.getAllCourseOfferingByTeacherId(tid);
    }

    @Override
    public List<CourseOffering> getAllCourseOfferingByClassroom(String classroom) {
        return courseOfferingMapper.getAllCourseOfferingByClassroom(classroom);
    }

    @Override
    public void updateCourseOffering(CourseOffering courseOffering) throws IllegalAccessException {
        updateTeacherTimeCheck(courseOffering);
        courseOfferingMapper.updateCourseOffering(courseOffering);
    }

    private void updateTeacherTimeCheck(CourseOffering courseOffering) throws IllegalAccessException {
        if (timeService.checkUpdateTimeConflict(courseOffering)) {
            throw new IllegalAccessException(StateDesc.TIME_CONFLICT.getDesc());
        } else if (timeService.checkUpdateClassroomConflict(courseOffering)) {
            throw new IllegalAccessException(StateDesc.CLASSROOM_CONFLICT.getDesc());
        }
    }

    @Override
    public List<CourseOffering> getOthersCourseOfferingByTeacherId(int tid, int courseOfferingId) {
        return courseOfferingMapper.getOthersCourseOfferingByTeacherId(tid, courseOfferingId);
    }

    @Override
    public List<CourseOffering> getOthersCourseOfferingByClassroom(String classroom, int courseOfferingId) {
        return courseOfferingMapper.getOthersCourseOfferingByClassroom(classroom, courseOfferingId);
    }

    @Override
    public void getAllCourseOfferingByPage(Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<CourseOffering> allCourseOffering = getAllCourseOffering();
        PageInfo<CourseOffering> pageInfo = new PageInfo<>(allCourseOffering);
        page.setData(allCourseOffering);
        page.setTotalPages(pageInfo.getPages());
    }

    @Override
    public List<CourseOffering> getAllCourseOfferingByStudentId(int sid) {
        return courseOfferingMapper.getAllCourseOfferingByStudentId(sid);
    }

    @Override
    public void selectStudentCourseOffering(StudentCourseOffering studentCourseOffering) throws IllegalAccessException {
        int courseOfferingId = studentCourseOffering.getCourseOfferingId();
        timeService.checkStudentSelectionConflict(studentCourseOffering);
        courseOfferingMapper.addCurrentNum(courseOfferingId);
        courseOfferingMapper.addStudentCourseOffering(courseOfferingId, studentCourseOffering.getSid());
    }

    @Override
    public CourseOffering getCourseOfferingByCourseOfferingId(int courseOfferingId) {
        return courseOfferingMapper.getCourseOfferingByCourseOfferingId(courseOfferingId);
    }

    @Override
    public void cancelCourseOffering(StudentCourseOffering studentCourseOffering) {
        courseOfferingMapper.reduceCurrentNum(studentCourseOffering.getCourseOfferingId());
        courseOfferingMapper.deleteStudentCourseOffering(studentCourseOffering);
    }

    public List<CourseOffering> getAllCourseOffering() {
        return courseOfferingMapper.getAllCourseOffering();
    }

}
