package edu.fjnu501.crms.domain;

public class Classroom {

    private int classroomId;
    private String classroomName;

    public Classroom() {}

    public Classroom(int classroomId, String classroomName) {
        this.classroomId = classroomId;
        this.classroomName = classroomName;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

}
