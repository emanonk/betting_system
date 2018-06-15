package com.emanon.application.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.emanon.application.domain.Country;

/**
 * Created by mmkamm on 04/06/2018.
 */
@Service
public class ProjectData {

    private static final Logger LOG = LoggerFactory.getLogger(ProjectData.class);

    @Autowired
    TempDataHolderService tempDataHolderService;

    @Autowired
    DataPrinter dataPrinter;

    public void countriesProjection(List<Country> countries){
        LOG.info("-- Printing Countries --");
        int competitions = 0;
        for(Country c:countries){
            competitions++;
            dataPrinter.consolePrinter(c.toString());
        }
        LOG.info("Competintions number:"+competitions);
    }
}
