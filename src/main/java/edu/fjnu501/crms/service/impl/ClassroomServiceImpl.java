package edu.fjnu501.crms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu501.crms.domain.Classroom;
import edu.fjnu501.crms.domain.CourseOffering;
import edu.fjnu501.crms.domain.Page;
import edu.fjnu501.crms.domain.PageSearch;
import edu.fjnu501.crms.mapper.ClassroomMapper;
import edu.fjnu501.crms.service.ClassroomService;
import edu.fjnu501.crms.service.CourseOfferingService;
import edu.fjnu501.crms.state.StateDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("classroomService")
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    private ClassroomMapper classroomMapper;

    @Autowired
    private CourseOfferingService courseOfferingService;

    @Override
    public void addClassroom(Classroom classroom) throws IllegalAccessException {
        if (checkClassroom(classroom)) {
            throw new IllegalAccessException(StateDesc.CLASSROOM_REPEATED.getDesc());
        }
        classroomMapper.addClassroom(classroom);
    }

    @Override
    public void updateClassroom(Classroom classroom) throws IllegalAccessException {
        if ("".equals(classroom.getClassroomName()) || classroom.getClassroomName() == null) {
            throw new IllegalAccessException(StateDesc.CLASSROOM_NAME_NULL.getDesc());
        } else if (checkClassroom(classroom)) {
            throw new IllegalAccessException(StateDesc.CLASSROOM_REPEATED.getDesc());
        }
//        String classroomNameByClassroomId = getClassroomNameByClassroomId(classroom.getClassroomId());
//        courseOfferingService.updateClassroom(classroomNameByClassroomId, classroom.getClassroomName());
        classroomMapper.updateClassroom(classroom);
    }

    public void deleteClassroom(String classroomName) throws IllegalAccessException {
        List<CourseOffering> allCourseOfferingByClassroom = courseOfferingService.getAllCourseOfferingByClassroom(classroomName);
        if (allCourseOfferingByClassroom.size() == 0) {
            classroomMapper.deleteClassroom(classroomName);
        } else {
            throw new IllegalAccessException(StateDesc.CLASSROOM_EXIST_DELETE.getDesc());
        }
    }

    @Override
    public List<Classroom> getAllClassroom() {
        return classroomMapper.getAllClassroom();
    }

    @Override
    public void getClassroomByPage(Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Classroom> allClassroom = getAllClassroom();
        PageInfo<Classroom> pageInfo = new PageInfo<>(allClassroom);
        page.setData(allClassroom);
        page.setTotalPages(pageInfo.getPages());
    }

    @Override
    public void searchClassroom(PageSearch pageSearch) {
        PageHelper.startPage(pageSearch.getPageNum(), pageSearch.getPageSize());
        List<Classroom> classrooms = classroomMapper.searchClassroom(pageSearch.getKeyword());
        PageInfo<Classroom> pageInfo = new PageInfo<>(classrooms);
        pageSearch.setData(classrooms);
        pageSearch.setTotalPages(pageInfo.getPages());
    }

    @Override
    public String getClassroomNameByClassroomId(int classroomId) {
        return classroomMapper.getClassroomNameByClassroomId(classroomId);
    }

    public boolean checkClassroom(Classroom classroom) {
        Classroom classroomByClassroomName = classroomMapper.getClassroomByClassroomName(classroom.getClassroomName());
        if (classroomByClassroomName != null) {
            return true;  // 冲突
        }
        return false;
    }

}
