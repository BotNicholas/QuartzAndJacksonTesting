package org.botnicholas.projects.democron.controllers.models;

public enum WeekDay {
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(7),
    SUNDAY(1);

    private int weekDayId;

    WeekDay(int weekDayId) {
        this.weekDayId = weekDayId;
    }

    public int getWeekDayId() {
        return weekDayId;
    }
}
