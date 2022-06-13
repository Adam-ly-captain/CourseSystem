package edu.fjnu501.crms.controller;

import edu.fjnu501.crms.domain.*;
import edu.fjnu501.crms.service.TeacherService;
import edu.fjnu501.crms.state.ResultCodeState;
import edu.fjnu501.crms.state.StateDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/get/curriculum")
    @ResponseBody
    public Result getTeacherCurriculum(@RequestBody TeacherCourseOfferingPage teacherCourseOfferingPage) {
        try {
            teacherService.getTeacherCurriculum(teacherCourseOfferingPage);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.SELECT_FAILED.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.SELECT_SUCCESS.getDesc(), teacherCourseOfferingPage);
    }

    @RequestMapping(value = "/get/new/curriculum/{tid}")
    @ResponseBody
    public Result getTeacherNewCurriculum(@PathVariable("tid") int tid) {
        Object teacherNewCurriculum = null;
        try {
            teacherNewCurriculum = teacherService.getTeacherNewCurriculum(tid);
        } catch (IllegalAccessException e) {
            return new Result(ResultCodeState.INVALID.getState(), StateDesc.WEEKDAY_ERROR.getDesc(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.SELECT_FAILED.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.SELECT_SUCCESS.getDesc(), teacherNewCurriculum);
    }

    @RequestMapping(value = "/get/students")
    @ResponseBody
    public Result getStudentsInfoByCourseOfferingId(@RequestBody TeacherCourseOfferingPage teacherCourseOffering) {
        try {
            teacherService.getStudentsInfoByCourseOfferingId(teacherCourseOffering);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.PAGE_FAILED.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.PAGE_SUCCESS.getDesc(), teacherCourseOffering);
    }

    @RequestMapping(value = "/get/teachers")
    @ResponseBody
    public Result getAllTeachers(@RequestBody Page page) {
        try {
            teacherService.getAllTeachersByPage(page);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.PAGE_FAILED.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.PAGE_SUCCESS.getDesc(), page);
    }

    @RequestMapping(value = "/get/all")
    @ResponseBody
    public Result getAllTeacher() {
        List<User> allTeachers = null;
        try {
            allTeachers = teacherService.getAllTeachers();
        } catch (Exception e) {
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.SELECT_FAILED.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.SEARCH_SUCCESS.getDesc(), allTeachers);
    }

}
