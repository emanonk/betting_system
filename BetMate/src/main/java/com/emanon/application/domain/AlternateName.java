package com.emanon.application.domain;

/**
 * Created by mmkamm on 21/05/2018.
 */
public class AlternateName {
    private Long id;
    private String name;

    public AlternateName() {
    }

    public AlternateName withName(String name) {
        this.name = name;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
