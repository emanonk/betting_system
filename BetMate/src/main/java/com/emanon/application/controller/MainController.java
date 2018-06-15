package com.emanon.application.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.emanon.application.domain.Country;
import com.emanon.application.service.FolderStructureService;
import com.emanon.application.service.LoadCountriesService;
import com.emanon.application.service.ProjectData;
import com.emanon.application.service.TempDataHolderService;
import com.emanon.application.service.dataCollector.StepOneGetData;
import com.emanon.application.thirdpartyservice.MyChromeDriver;

/**
 * Created by mmkamm on 11/05/2018.
 */
@Controller
public class MainController {

    @Autowired
    FolderStructureService folderStructureService;

    @Autowired
    TempDataHolderService tempDataHolderService;

    @Autowired
    LoadCountriesService loadCountriesService;

    @Autowired
    MyChromeDriver driver;

    @Autowired
    ProjectData projectData;

    @Autowired
    StepOneGetData stepOneGetData;

    private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

    public void index() throws InterruptedException, IOException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        LOG.info("Application started..");

        folderStructureService.initialiseOutputFolders();

        List<Country> countries = stepOneGetData.removeExceptions(stepOneGetData.getCountries());



        projectData.countriesProjection(countries);


        //readCountries();

        //tempDataHolderService.setCountries(getCountries());

        //loadCountriesService.loadCountriesFromFile();

        //projectData.countriesProjection();


        LOG.info("Application closes.");

    }





    private void readingCountriesAndCreateObjects(File countryFile){

        ObjectMapper mapper = new ObjectMapper();

        try {

            // Convert JSON string from file to Object
            Country country = mapper.readValue(countryFile, Country.class);
            System.out.println(country);
//
//            // Convert JSON string to Object
//            String jsonInString = "{\"age\":33,\"messages\":[\"msg 1\",\"msg 2\"],\"name\":\"mkyong\"}";
//            User user1 = mapper.readValue(jsonInString, User.class);
//            System.out.println(user1);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
