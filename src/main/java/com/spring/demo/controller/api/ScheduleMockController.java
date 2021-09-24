package com.spring.demo.controller.api;

import com.spring.demo.dto.ScheduleDto;
import com.spring.demo.dto.SchedulesDto;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


@Controller
@RequestMapping("/mock")
public class ScheduleMockController {

    private static final Map<Integer, List<ScheduleDto>> schedules = new HashMap<Integer, List<ScheduleDto>>(){{
        put(1, Arrays.asList(
                new ScheduleDto(LocalDate.now(), LocalTime.of(8,0), LocalTime.of(10,0),"302"),
                new ScheduleDto(LocalDate.now(), LocalTime.of(10,0), LocalTime.of(12,0),"310")
                ));

        put(2,Arrays.asList(
                new ScheduleDto(LocalDate.now(),LocalTime.of(8,0),LocalTime.of(10,0),"202")
            ));
    }};

    @GetMapping("/schedules")
    @ResponseBody
    public SchedulesDto getTeacherSchedule(@RequestParam int teacherId) throws InterruptedException {
        Thread.sleep(3000);
        List<ScheduleDto> scheduleDtos = schedules.get(teacherId);
        return new SchedulesDto(scheduleDtos == null ? new ArrayList<>() : scheduleDtos);
    }

}