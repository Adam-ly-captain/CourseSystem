package edu.fjnu501.crms.controller;

import edu.fjnu501.crms.domain.Classroom;
import edu.fjnu501.crms.domain.Page;
import edu.fjnu501.crms.domain.PageSearch;
import edu.fjnu501.crms.domain.Result;
import edu.fjnu501.crms.service.ClassroomService;
import edu.fjnu501.crms.state.ResultCodeState;
import edu.fjnu501.crms.state.StateDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/classroom")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @RequestMapping(value = "/add")
    public Result addClassroom(@RequestBody Classroom classroom) {
        try {
            classroomService.addClassroom(classroom);
        } catch (IllegalAccessException e) {
            return new Result(ResultCodeState.INVALID.getState(), StateDesc.CLASSROOM_REPEATED.getDesc(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.ADD_FAILED.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.ADD_SUCCESS.getDesc(), null);
    }

    @RequestMapping(value = "/update")
    public Result updateClassroomInfo(@RequestBody Classroom classroom) {
        try {
            classroomService.updateClassroom(classroom);
        } catch (IllegalAccessException e) {
            return new Result(ResultCodeState.INVALID.getState(), e.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.UPDATE_FAILED.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.UPDATE_SUCCESS.getDesc(), null);
    }

    @RequestMapping(value = "/delete/{classroomName}")
    public Result deleteClassroom(@PathVariable("classroomName") String classroomName) {
        try {
            classroomService.deleteClassroom(classroomName);
        } catch (IllegalAccessException e) {
            return new Result(ResultCodeState.INVALID.getState(), e.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.DELETE_FAILED.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.DELETE_SUCCESS.getDesc(), null);
    }

    @RequestMapping(value = "/get/page")
    public Result getClassroomByPage(@RequestBody Page page) {
        try {
            classroomService.getClassroomByPage(page);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.PAGE_FAILED.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.PAGE_SUCCESS.getDesc(), page);
    }

    @RequestMapping(value = "/search")
    public Result searchClassroom(@RequestBody PageSearch pageSearch) {
        try {
            classroomService.searchClassroom(pageSearch);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.SEARCH_FAILED.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.SEARCH_SUCCESS.getDesc(), pageSearch);
    }

    @RequestMapping(value = "/get/all")
    public Result getAllClassrooms() {
        List<Classroom> allClassroom = null;
        try {
            allClassroom = classroomService.getAllClassroom();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCodeState.FAILED.getState(), StateDesc.SELECT_FAILED.getDesc(), null);
        }
        return new Result(ResultCodeState.SUCCESS.getState(), StateDesc.SELECT_SUCCESS.getDesc(), allClassroom);
    }

}
