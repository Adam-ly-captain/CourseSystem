package edu.fjnu501.crms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu501.crms.domain.CourseOffering;
import edu.fjnu501.crms.domain.Page;
import edu.fjnu501.crms.domain.TeacherCourseOfferingPage;
import edu.fjnu501.crms.domain.User;
import edu.fjnu501.crms.mapper.TeacherMapper;
import edu.fjnu501.crms.service.CourseOfferingService;
import edu.fjnu501.crms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private CourseOfferingService courseOfferingService;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public void getTeacherCurriculum(TeacherCourseOfferingPage teacherCourseOfferingPage) {
        PageHelper.startPage(teacherCourseOfferingPage.getPageNum(), teacherCourseOfferingPage.getPageSize());
        List<CourseOffering> allCourseOfferingByTeacherId = courseOfferingService.getAllCourseOfferingByTeacherId(teacherCourseOfferingPage.getTid());
        PageInfo<CourseOffering> pageInfo = new PageInfo<>(allCourseOfferingByTeacherId);
        teacherCourseOfferingPage.setData(allCourseOfferingByTeacherId);
        teacherCourseOfferingPage.setTotalPages(pageInfo.getPages());
    }

    @Override
    public void getStudentsInfoByCourseOfferingId(TeacherCourseOfferingPage teacherCourseOffering) {
        PageHelper.startPage(teacherCourseOffering.getPageNum(), teacherCourseOffering.getPageSize());
        List<User> studentsInfoByCourseOfferingId = teacherMapper.getStudentsInfoByCourseOfferingId(teacherCourseOffering.getCourseOfferingId());
        PageInfo<User> pageInfo = new PageInfo<>(studentsInfoByCourseOfferingId);
        teacherCourseOffering.setTotalPages(pageInfo.getPages());
        teacherCourseOffering.setData(studentsInfoByCourseOfferingId);
    }

    @Override
    public void getAllTeachersByPage(Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<User> allTeachers = getAllTeachers();
        PageInfo<User> pageInfo = new PageInfo<>(allTeachers);
        page.setTotalPages(pageInfo.getPages());
        page.setData(allTeachers);
    }

    @Override
    public List<User> getAllTeachers() {
        return teacherMapper.getAllTeachers();
    }

    @Override
    public int getTeacherIdByTeacherName(String teacherName) {
        return teacherMapper.getTeacherIdByTeacherName(teacherName);
    }

}
