package com.emanon.application.domain;

import java.util.ArrayList;
import java.util.List;

import sun.util.calendar.CalendarDate;

/**
 * Created by mmkamm on 13/05/2018.
 */
public class LeagueByYear {

    private Long id;

    private Country country;
    private String name;
    private List<AlternateName> alternativeNames;

    private String link;

    private String yearStr;
    private Integer year;

    private CalendarDate startingDate;
    private CalendarDate endingDate;

    private List<Match> matches;
    private List<Team> teams;

    public LeagueByYear() {
    }

    public LeagueByYear withCountry (Country country){
        this.country = country;
        return this;
    }

    public LeagueByYear createAlternateNames(){
        this.alternativeNames = new ArrayList();
        return this;
    }

    public LeagueByYear createTeams(){
        this.teams = new ArrayList();
        return this;
    }

    public LeagueByYear withName(String leagueName){
        this.name = leagueName;
        this.alternativeNames.add(new AlternateName().withName(leagueName));
        return this;
    }

    public LeagueByYear withNewAlternateName(String alternateName){
        boolean nameExists= false;
        for(AlternateName altName:this.alternativeNames){
            if(altName.equals(alternateName)){
                nameExists= true;
            }
        }
        if(!nameExists){
            this.alternativeNames.add(new AlternateName().withName(alternateName));
        }
        return this;
    }

    public LeagueByYear withYearStr(String yearStr){
        this.yearStr = yearStr;
        return this;
    }

    public LeagueByYear withLink(String link){
        this.link = link;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<AlternateName> getAlternativeNames() {
        return alternativeNames;
    }

    public void setAlternativeNames(List<AlternateName> alternativeNames) {
        this.alternativeNames = alternativeNames;
    }

    public String getYearStr() {
        return yearStr;
    }

    public void setYearStr(String yearStr) {
        this.yearStr = yearStr;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public CalendarDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(CalendarDate startingDate) {
        this.startingDate = startingDate;
    }

    public CalendarDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(CalendarDate endingDate) {
        this.endingDate = endingDate;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return "LeagueByYear{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", alternativeNames=" + alternativeNames +
            ", link='" + link + '\'' +
            ", yearStr='" + yearStr + '\'' +
            ", year=" + year +
            ", startingDate=" + startingDate +
            ", endingDate=" + endingDate +
            ", teams=" + teams +
            '}';
    }
}
