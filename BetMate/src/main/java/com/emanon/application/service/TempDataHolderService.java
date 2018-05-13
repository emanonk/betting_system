package com.emanon.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.emanon.application.domain.Country;

/**
 * Created by mmkamm on 13/05/2018.
 */
@Service
public class TempDataHolderService {

    private List<Country> countries = new ArrayList<>();


    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
