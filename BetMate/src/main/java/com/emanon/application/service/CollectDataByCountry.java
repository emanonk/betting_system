package com.emanon.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.emanon.application.domain.Country;

/**
 * Created by mmkamm on 30/05/2018.
 */
@Service
public class CollectDataByCountry {

    @Autowired
    TempDataHolderService tempDataHolderService;

    public void visitAndCollect(){

        tempDataHolderService.getCountries();

        for(Country cntr:tempDataHolderService.getCountries()){
            System.out.println(cntr.toString());
        }




    }

}
