package com.spring.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleDto {

    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private String classroom;

    public ScheduleDto(){
    }

    public ScheduleDto(LocalDate date, LocalTime start, LocalTime end, String classroom) {
        this.date = date;
        this.start = start;
        this.end = end;
        this.classroom = classroom;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}
