package edu.fjnu501.crms.mapper;

import edu.fjnu501.crms.domain.CourseOffering;
import edu.fjnu501.crms.domain.StudentCourseOffering;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseOfferingMapper {

    void addCourseOffering(CourseOffering courseOffering);

    List<CourseOffering> getAllCourseOfferingByTeacherId(int tid);

    List<CourseOffering> getAllCourseOfferingByClassroom(String classroom);

    void updateCourseOffering(CourseOffering courseOffering);

    List<CourseOffering> getOthersCourseOfferingByTeacherId(@Param("tid") int tid, @Param("courseOfferingId") int courseOfferingId);

    List<CourseOffering> getOthersCourseOfferingByClassroom(@Param("classroom") String classroom, @Param("courseOfferingId") int courseOfferingId);

    List<CourseOffering> getAllCourseOffering();

    List<CourseOffering> getAllCourseOfferingByStudentId(int sid);

    void addStudentCourseOffering(@Param("courseOfferingId") int courseOfferingId, @Param("sid") int sid);

    CourseOffering getCourseOfferingByCourseOfferingId(int courseOfferingId);

    void addCurrentNum(int courseOfferingId);

    void reduceCurrentNum(int courseOfferingId);

    void deleteStudentCourseOffering(StudentCourseOffering studentCourseOffering);

}
