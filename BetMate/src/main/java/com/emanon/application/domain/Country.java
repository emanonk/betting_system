package com.emanon.application.domain;

/**
 * Created by mmkamm on 13/05/2018.
 */
public class Country {
    private Long id;
    private String countryName;
    private String link;

    private List<League> leagues;

    public Country(Long id, String countryName, String link) {
        this.id = id;
        this.countryName = countryName;
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return countryName;
    }

    public void setName(String countryName) {
        this.countryName = countryName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Country{" +
            "id=" + id +
            ", countryName='" + countryName + '\'' +
            ", link='" + link + '\'' +
            '}';
    }
}
