package edu.fjnu501.crms.state;

public enum LessonOfDay {

    First(1), Second(3), Third(5), Fourth(7), Fifth(9), Sixth(11),
    FirstLesson("First"), SecondLesson("Second"), ThirdLesson("Third"), FourthLesson("Fourth"), FifthLesson("Fifth"), SixthLesson("Sixth");

    private int lesson;
    private String lessonSchedule;

    LessonOfDay(int lesson) {
        this.lesson = lesson;
    }

    LessonOfDay(String lessonSchedule) {
        this.lessonSchedule = lessonSchedule;
    }

    public int getLesson() {
        return lesson;
    }

    public String getLessonSchedule() {
        return lessonSchedule;
    }

}
