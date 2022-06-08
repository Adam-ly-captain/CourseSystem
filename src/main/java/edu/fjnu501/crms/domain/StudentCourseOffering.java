package edu.fjnu501.crms.domain;

public class StudentCourseOffering {

    private int sid;
    private int courseOfferingId;

    @Override
    public String toString() {
        return "StudentCourseOffering{" +
                "sid=" + sid +
                ", courseOfferingId=" + courseOfferingId +
                '}';
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getCourseOfferingId() {
        return courseOfferingId;
    }

    public void setCourseOfferingId(int courseOfferingId) {
        this.courseOfferingId = courseOfferingId;
    }
}
