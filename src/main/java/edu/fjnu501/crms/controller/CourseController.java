package edu.fjnu501.crms.controller;

import edu.fjnu501.crms.domain.Course;
import edu.fjnu501.crms.domain.Page;
import edu.fjnu501.crms.domain.Result;
import edu.fjnu501.crms.service.CourseService;
import edu.fjnu501.crms.state.ResultCodeState;
import edu.fjnu501.crms.state.StateDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/add")
    @ResponseBody
    public Result addCourse(@RequestBody Course course) {
        try {
            courseService.addCourse(course);
        } catch (IllegalAccessException e) {
            return new Result(ResultCodeState.INVALID.getState(), e.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.ADD_SUCCESS.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.ADD_SUCCESS.getDesc(), null);
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Result updateCourse(@RequestBody Course course) {
        try {
            courseService.updateCourse(course);
        } catch (IllegalAccessException e) {
            return new Result(ResultCodeState.INVALID.getState(), e.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.UPDATE_FAILED.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.UPDATE_SUCCESS.getDesc(), null);
    }

    @RequestMapping(value = "/get/page")
    @ResponseBody
    public Result getCourseByPage(@RequestBody Page page) {
        try {
            courseService.getCourseByPage(page);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.PAGE_FAILED.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.PAGE_SUCCESS.getDesc(), page);
    }

}
