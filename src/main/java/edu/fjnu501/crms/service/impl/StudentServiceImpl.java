package edu.fjnu501.crms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu501.crms.domain.CourseOffering;
import edu.fjnu501.crms.domain.StudentCourseOffering;
import edu.fjnu501.crms.domain.StudentCourseOffering2;
import edu.fjnu501.crms.domain.StudentCourseOfferingPage;
import edu.fjnu501.crms.service.CourseOfferingService;
import edu.fjnu501.crms.service.CurriculumService;
import edu.fjnu501.crms.service.StudentService;
import edu.fjnu501.crms.state.StateDesc;
import edu.fjnu501.crms.state.StudentCourseOfferingState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private CourseOfferingService courseOfferingService;

    @Autowired
    private CurriculumService curriculumService;

    @Override
    public void selectCourseOffering(StudentCourseOffering studentCourseOffering) throws IllegalAccessException {
        courseOfferingService.selectStudentCourseOffering(studentCourseOffering);
    }

    @Override
    public List<CourseOffering> getCourseOfferingsByStudentId(int sid) {
        return courseOfferingService.getAllCourseOfferingByStudentId(sid);
    }

    @Override
    public void cancelCourseOffering(StudentCourseOffering studentCourseOffering) throws IllegalAccessException {
        boolean isSelected = false;
        List<CourseOffering> courseOfferingsByStudentId = getCourseOfferingsByStudentId(studentCourseOffering.getSid());
        for (CourseOffering courseOffering : courseOfferingsByStudentId) {
            if (studentCourseOffering.getCourseOfferingId() == courseOffering.getCourseOfferingId()) {
                isSelected = true;
                break;
            }
        }
        if (isSelected) {
            courseOfferingService.cancelCourseOffering(studentCourseOffering);
        } else {
            throw new IllegalAccessException(StateDesc.NOT_SELECT.getDesc());
        }
    }

    @Override
    public void getAllStudentCourseOffering(StudentCourseOfferingPage studentCourseOfferingPage) {
        boolean isSelected = false;
        List<StudentCourseOffering2> courseOffering2List = new ArrayList<>();
        List<CourseOffering> courseOfferingsByStudentId = getCourseOfferingsByStudentId(studentCourseOfferingPage.getSid());
        PageHelper.startPage(studentCourseOfferingPage.getPageNum(), studentCourseOfferingPage.getPageSize());
        List<CourseOffering> allCourseOffering = courseOfferingService.getAllCourseOffering();
        PageInfo<CourseOffering> pageInfo = new PageInfo<>(allCourseOffering);
        for (CourseOffering courseOffering : allCourseOffering) {
            StudentCourseOffering2 studentCourseOffering2 = new StudentCourseOffering2();
            studentCourseOffering2.setCourseOffering(courseOffering);
            for (CourseOffering offering : courseOfferingsByStudentId) {
                if (courseOffering.getCourseOfferingId() == offering.getCourseOfferingId()) {
                    studentCourseOffering2.setState(StudentCourseOfferingState.SELECTED.getState());
                    isSelected = true;
                    break;
                }
            }
            if (!isSelected) {
                studentCourseOffering2.setState(StudentCourseOfferingState.NOT_SELECTED.getState());
            }
            courseOffering2List.add(studentCourseOffering2);
            isSelected = false;
        }
        studentCourseOfferingPage.setTotalPages(pageInfo.getPages());
        studentCourseOfferingPage.setData(courseOffering2List);
    }

    @Override
    public void getCourseOfferingsByStudentIdAndPage(StudentCourseOfferingPage studentCourseOfferingPage) {
        PageHelper.startPage(studentCourseOfferingPage.getPageNum(), studentCourseOfferingPage.getPageSize());
        List<CourseOffering> courseOfferingsByStudentId = getCourseOfferingsByStudentId(studentCourseOfferingPage.getSid());
        PageInfo<CourseOffering> pageInfo = new PageInfo<>(courseOfferingsByStudentId);
        studentCourseOfferingPage.setTotalPages(pageInfo.getPages());
        studentCourseOfferingPage.setData(courseOfferingsByStudentId);
    }

    @Override
    public Object getCurriculum(int sid) throws IllegalAccessException {
        List<CourseOffering> courseOfferingsByStudentId = getCourseOfferingsByStudentId(sid);
        return curriculumService.getNewCurriculum(courseOfferingsByStudentId);
    }

}
