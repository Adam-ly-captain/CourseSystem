package edu.fjnu501.crms.state;

public enum StudentCourseOfferingState {

    SELECTED(1), NOT_SELECTED(0);

    private int state;

    StudentCourseOfferingState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

}
