package edu.fjnu501.crms.state;

public enum WeekDay {

    Monday(1), Tuesday(2), Wednesday(3), Thursday(4), Friday(5), Saturday(6), Sunday(7),
    MONDAY_DESC("Monday"), TUESDAY_DESC("Tuesday"), WEDNESDAY_DESC("Wednesday"), THURSDAY_DESC("Thursday"), FRIDAY_DESC("Friday"), SATURDAY_DESC("Saturday"), SUNDAY_DESC("Sunday");

    private int dayOfWeek;
    private String dayOfWeekDesc;

    WeekDay(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    WeekDay(String dayOfWeekDesc) {
        this.dayOfWeekDesc = dayOfWeekDesc;
    }

    public String getDayOfWeekDesc() {
        return dayOfWeekDesc;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

}
