package edu.fjnu501.crms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu501.crms.domain.Course;
import edu.fjnu501.crms.domain.Page;
import edu.fjnu501.crms.mapper.CourseMapper;
import edu.fjnu501.crms.service.CourseService;
import edu.fjnu501.crms.state.StateDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public void addCourse(Course course) throws IllegalAccessException {
        if (checkCourseName(course.getCourseName())) {
            throw new IllegalAccessException(StateDesc.COURSE_NAME_CONFLICT.getDesc());
        }
        courseMapper.addCourse(course);
    }

    @Override
    public void updateCourse(Course course) throws IllegalAccessException {
        if (checkCourseName(course.getCourseName())) {
            throw new IllegalAccessException(StateDesc.COURSE_NAME_CONFLICT.getDesc());
        }
        courseMapper.updateCourse(course);
    }

    public List<Course> getAllCourse() {
        return courseMapper.getAllCourse();
    }

    @Override
    public void getCourseByPage(Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Course> allCourse = getAllCourse();
        PageInfo<Course> pageInfo = new PageInfo<>(allCourse);
        page.setTotalPages(pageInfo.getPages());
        page.setData(allCourse);
    }

    @Override
    public boolean checkCourseName(String courseName) {
        Course course = courseMapper.selectCourseByCourseName(courseName);
        if (course != null) {
            return true;  // 课程重名
        }
        return false;
    }

    @Override
    public int getCourseIdByCourseName(String courseName) {
        return courseMapper.getCourseIdByCourseName(courseName);
    }

}
