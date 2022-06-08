package edu.fjnu501.crms.controller;

import edu.fjnu501.crms.domain.CourseOffering;
import edu.fjnu501.crms.domain.Result;
import edu.fjnu501.crms.domain.TeacherCourseOfferingPage;
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

}
