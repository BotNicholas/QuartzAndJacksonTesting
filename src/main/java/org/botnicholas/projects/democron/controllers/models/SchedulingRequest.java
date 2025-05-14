package org.botnicholas.projects.democron.controllers.models;

public class SchedulingRequest {
    private int step;
    private String time;
    private int day;
    private WeekDay weekDay;
    private int month;
    private int year;
    private String timeZone;

//    public SchedulingRequest(int step, String time, int day, WeekDay weekDay, int month, int year, String timeZone) {
//        this.step = step;
//        this.time = time;
//        this.day = day;
//        this.weekDay = weekDay;
//        this.month = month;
//        this.year = year;
//        this.timeZone = timeZone;
//    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public String toString() {
        return "SchedulingRequest{" +
                "step=" + step +
                ", time='" + time + '\'' +
                ", day=" + day +
                ", weekDay=" + weekDay +
                ", month=" + month +
                ", year=" + year +
                ", timeZone='" + timeZone + '\'' +
                '}';
    }
}
