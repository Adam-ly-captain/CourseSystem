package edu.fjnu501.crms.service.impl;

import edu.fjnu501.crms.domain.CourseOffering;
import edu.fjnu501.crms.service.CurriculumService;
import edu.fjnu501.crms.state.LessonOfDay;
import edu.fjnu501.crms.state.StateDesc;
import edu.fjnu501.crms.state.WeekDay;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("curriculumService")
public class CurriculumServiceImpl implements CurriculumService {

    @Override
    public Object getNewCurriculum(List<CourseOffering> allCourseOffering) throws IllegalAccessException {
        HashMap<String, Object> weekDay = new HashMap<>();
        initWeekDay(weekDay);
        ArrayList<HashMap<String, Object>> curriculum = new ArrayList<>();
        initCurriculum(curriculum, weekDay);
        for (CourseOffering courseOffering : allCourseOffering) {
            if (courseOffering.getDayOfWeek() > 7) {
                throw new IllegalAccessException(StateDesc.WEEKDAY_ERROR.getDesc());
            }
            setLessonOfDay(curriculum, courseOffering, courseOffering.getDayOfWeek() - 1);
        }
        return weekDay;
    }

    private void initCurriculum(ArrayList<HashMap<String, Object>> curriculum, HashMap<String, Object> weekDay) {
        for (int i = 1; i <= 7; i++) {  // 初始化每一天的课表
            HashMap<String, Object> lesson = new HashMap<>();
            initLesson(lesson);
            curriculum.add(lesson);
            if (i == WeekDay.Monday.getDayOfWeek()) {
                weekDay.put(WeekDay.MONDAY_DESC.getDayOfWeekDesc(), lesson);
            } else if (i == WeekDay.Tuesday.getDayOfWeek()) {
                weekDay.put(WeekDay.TUESDAY_DESC.getDayOfWeekDesc(), lesson);
            } else if (i == WeekDay.Wednesday.getDayOfWeek()) {
                weekDay.put(WeekDay.WEDNESDAY_DESC.getDayOfWeekDesc(), lesson);
            } else if (i == WeekDay.Thursday.getDayOfWeek()) {
                weekDay.put(WeekDay.THURSDAY_DESC.getDayOfWeekDesc(), lesson);
            } else if (i == WeekDay.Friday.getDayOfWeek()) {
                weekDay.put(WeekDay.FRIDAY_DESC.getDayOfWeekDesc(), lesson);
            } else if (i == WeekDay.Saturday.getDayOfWeek()) {
                weekDay.put(WeekDay.SATURDAY_DESC.getDayOfWeekDesc(), lesson);
            } else if (i == WeekDay.Sunday.getDayOfWeek()) {
                weekDay.put(WeekDay.SUNDAY_DESC.getDayOfWeekDesc(), lesson);
            }
        }
    }

    private void initWeekDay(HashMap<String, Object> weekDay) {
        weekDay.put(WeekDay.MONDAY_DESC.getDayOfWeekDesc(), null);
        weekDay.put(WeekDay.TUESDAY_DESC.getDayOfWeekDesc(), null);
        weekDay.put(WeekDay.WEDNESDAY_DESC.getDayOfWeekDesc(), null);
        weekDay.put(WeekDay.THURSDAY_DESC.getDayOfWeekDesc(), null);
        weekDay.put(WeekDay.FRIDAY_DESC.getDayOfWeekDesc(), null);
        weekDay.put(WeekDay.SATURDAY_DESC.getDayOfWeekDesc(), null);
        weekDay.put(WeekDay.SUNDAY_DESC.getDayOfWeekDesc(), null);
    }

    private void initLesson(HashMap<String, Object> lesson) {
        lesson.put(LessonOfDay.FirstLesson.getLessonSchedule(), null);
        lesson.put(LessonOfDay.SecondLesson.getLessonSchedule(), null);
        lesson.put(LessonOfDay.ThirdLesson.getLessonSchedule(), null);
        lesson.put(LessonOfDay.FourthLesson.getLessonSchedule(), null);
        lesson.put(LessonOfDay.FifthLesson.getLessonSchedule(), null);
        lesson.put(LessonOfDay.SixthLesson.getLessonSchedule(), null);
    }

    private void setLessonOfDay(ArrayList<HashMap<String, Object>> curriculum, CourseOffering courseOffering, int weekOfDay) {
        if (courseOffering.getLessonOfDay() == LessonOfDay.First.getLesson()) {
            curriculum.get(weekOfDay).put(LessonOfDay.FirstLesson.getLessonSchedule(), courseOffering);
        } else if (courseOffering.getLessonOfDay() == LessonOfDay.Second.getLesson()) {
            curriculum.get(weekOfDay).put(LessonOfDay.SecondLesson.getLessonSchedule(), courseOffering);
        } else if (courseOffering.getLessonOfDay() == LessonOfDay.Third.getLesson()) {
            curriculum.get(weekOfDay).put(LessonOfDay.ThirdLesson.getLessonSchedule(), courseOffering);
        } else if (courseOffering.getLessonOfDay() == LessonOfDay.Fourth.getLesson()) {
            curriculum.get(weekOfDay).put(LessonOfDay.FourthLesson.getLessonSchedule(), courseOffering);
        } else if (courseOffering.getLessonOfDay() == LessonOfDay.Fifth.getLesson()) {
            curriculum.get(weekOfDay).put(LessonOfDay.FifthLesson.getLessonSchedule(), courseOffering);
        } else if (courseOffering.getLessonOfDay() == LessonOfDay.Sixth.getLesson()) {
            curriculum.get(weekOfDay).put(LessonOfDay.SixthLesson.getLessonSchedule(), courseOffering);
        }
    }

}
