package org.botnicholas.projects.democron.controllers.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SoneObjectA {
    private String time;
    private int day;
    private String zone;

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

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
