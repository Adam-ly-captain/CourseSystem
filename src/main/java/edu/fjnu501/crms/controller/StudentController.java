package edu.fjnu501.crms.controller;

import edu.fjnu501.crms.domain.CourseOffering;
import edu.fjnu501.crms.domain.Result;
import edu.fjnu501.crms.domain.StudentCourseOffering;
import edu.fjnu501.crms.domain.StudentCourseOfferingPage;
import edu.fjnu501.crms.service.StudentService;
import edu.fjnu501.crms.state.ResultCodeState;
import edu.fjnu501.crms.state.StateDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/select")
    @ResponseBody
    public Result selectCourseOffering(@RequestBody StudentCourseOffering studentCourseOffering) {
        try {
            studentService.selectCourseOffering(studentCourseOffering);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return new Result(ResultCodeState.TIME_CONFLICT.getState(), e.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.ADD_FAILED.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.ADD_SUCCESS.getDesc(), null);
    }

    @RequestMapping(value = "/cancel")
    @ResponseBody
    public Result cancelCourseOffering(@RequestBody StudentCourseOffering studentCourseOffering) {
        try {
            studentService.cancelCourseOffering(studentCourseOffering);
        } catch (IllegalAccessException e) {
            return new Result(ResultCodeState.INVALID.getState(), e.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.UPDATE_FAILED.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.UPDATE_SUCCESS.getDesc(), null);
    }

    @RequestMapping(value = "/get/curriculum")
    @ResponseBody
    public Result getCourseOfferingsByStudentId(@RequestBody StudentCourseOfferingPage studentCourseOfferingPage) {
        try {
            studentService.getCourseOfferingsByStudentIdAndPage(studentCourseOfferingPage);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.SELECT_FAILED.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.SELECT_SUCCESS.getDesc(), studentCourseOfferingPage);
    }

    @RequestMapping(value = "/show/courseofferings")
    @ResponseBody
    public Result getAllCourseOffering(@RequestBody StudentCourseOfferingPage studentCourseOfferingPage) {
        try {
            studentService.getAllStudentCourseOffering(studentCourseOfferingPage);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.PAGE_FAILED.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.PAGE_SUCCESS.getDesc(), studentCourseOfferingPage);
    }

    @RequestMapping(value = "/get/new/curriculum/{sid}")
    @ResponseBody
    public Result getCurriculum(@PathVariable("sid") int sid) {
        Object curriculum = null;
        try {
            curriculum = studentService.getCurriculum(sid);
        } catch (IllegalAccessException e) {
            return new Result(ResultCodeState.INVALID.getState(), StateDesc.WEEKDAY_ERROR.getDesc(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.SELECT_FAILED.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.SELECT_SUCCESS.getDesc(), curriculum);
    }

}
