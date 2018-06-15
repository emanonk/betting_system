package com.emanon.application.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmkamm on 13/05/2018.
 */
public class Country {
    private Long id;
    private String countryName;
    private String link;

    private List<AlternateName> alternativeNames;
    private List<LeagueByYear> leagues;

    public Country() {
    }

    public Country withName(String countryName) {
        this.countryName = countryName;
        this.alternativeNames.add(new AlternateName().withName(countryName));
        return this;
    }

    public Country withLink(String link) {
        this.link = link;
        return this;
    }

    public Country withNewAlternateName(String alternateName){
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

    public Country withNewLeague(LeagueByYear leagueByYear){
        this.leagues.add(leagueByYear);
        return this;
    }

    public Country createLeagues(){
        this.leagues = new ArrayList();
        return this;
    }

    public Country createAlternateNames(){
        this.alternativeNames = new ArrayList();
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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

    public List<LeagueByYear> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<LeagueByYear> leagues) {
        this.leagues = leagues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Country country = (Country) o;

        if (id != null ? !id.equals(country.id) : country.id != null) {
            return false;
        }
        if (countryName != null ? !countryName.equals(country.countryName) : country.countryName != null) {
            return false;
        }
        return link != null ? link.equals(country.link) : country.link == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Country{" +
            "id=" + id +
            ", countryName='" + countryName + '\'' +
            ", link='" + link + '\'' +
            ", alternativeNames=" + alternativeNames +
            ", leagues=" + leagues +
            '}';
    }
}
