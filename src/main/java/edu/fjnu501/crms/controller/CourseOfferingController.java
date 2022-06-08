package edu.fjnu501.crms.controller;

import edu.fjnu501.crms.domain.CourseOffering;
import edu.fjnu501.crms.domain.Page;
import edu.fjnu501.crms.domain.Result;
import edu.fjnu501.crms.service.CourseOfferingService;
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
@RequestMapping(value = "/courseoffering")
public class CourseOfferingController {

    @Autowired
    private CourseOfferingService courseOfferingService;

    @RequestMapping(value = "/add")
    @ResponseBody
    public Result addCourseOffering(@RequestBody CourseOffering courseOffering) {
        courseOffering.setCurrentNum(0);
        try {
            courseOfferingService.addCourseOffering(courseOffering);
        } catch (IllegalAccessException e) {
            return new Result(ResultCodeState.TIME_CONFLICT.getState(), e.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.ADD_FAILED.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.ADD_SUCCESS.getDesc(), null);
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Result updateCourseOffering(@RequestBody CourseOffering courseOffering) {
        try {
            courseOfferingService.updateCourseOffering(courseOffering);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return new Result(ResultCodeState.TIME_CONFLICT.getState(), e.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.UPDATE_FAILED.getDesc(),  null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.UPDATE_SUCCESS.getDesc(), null);
    }

    @RequestMapping(value = "/get/all/page")
    @ResponseBody
    public Result getAllCourseOfferingByPage(@RequestBody Page page) {
        try {
            courseOfferingService.getAllCourseOfferingByPage(page);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.PAGE_FAILED.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.PAGE_SUCCESS.getDesc(), page);
    }

}
