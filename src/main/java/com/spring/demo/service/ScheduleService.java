package com.spring.demo.service;

import com.spring.demo.dto.SchedulesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ScheduleService {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @Cacheable(value="schedules", key="#teacherId")
    public SchedulesDto getTeacherSchedule(int teacherId){
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(env.getProperty("webservices.schedule.url"))
                .queryParam("teacherId", Integer.toString(teacherId));

        return restTemplate.getForObject(builder.toUriString(), SchedulesDto.class);
    }
}
