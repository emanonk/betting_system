package com.emanon.application.domain;

import java.util.List;

import sun.util.calendar.CalendarDate;

/**
 * Created by mmkamm on 13/05/2018.
 */
public class League {

    private Long id;
    private String name;
    private List<String> alternativeNames;

    private Integer year;

    private CalendarDate startingDate;//double check with spark
    private CalendarDate endingDate;//double check with spark

    private List<Team> teams;

}
