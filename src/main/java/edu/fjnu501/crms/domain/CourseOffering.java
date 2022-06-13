package edu.fjnu501.crms.domain;

public class CourseOffering {

    private int courseOfferingId;
    private String classroom;
    private int teacherId;
    private int dayOfWeek;
    private int lessonOfDay;
    private int courseId;
    private String courseOfferingName;
    private int startWeek;
    private int stopWeek;
    private int maxNum;
    private int currentNum;

    private String courseName;
    private String teacherName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getCourseOfferingId() {
        return courseOfferingId;
    }

    public void setCourseOfferingId(int courseOfferingId) {
        this.courseOfferingId = courseOfferingId;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getLessonOfDay() {
        return lessonOfDay;
    }

    public void setLessonOfDay(int lessonOfDay) {
        this.lessonOfDay = lessonOfDay;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseOfferingName() {
        return courseOfferingName;
    }

    public void setCourseOfferingName(String courseOfferingName) {
        this.courseOfferingName = courseOfferingName;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getStopWeek() {
        return stopWeek;
    }

    public void setStopWeek(int stopWeek) {
        this.stopWeek = stopWeek;
    }

}
