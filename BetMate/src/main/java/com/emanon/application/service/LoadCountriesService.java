package com.emanon.application.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.emanon.application.domain.Country;
import com.emanon.application.domain.LeagueByYear;

/**
 * Created by mmkamm on 30/05/2018.
 */
@Service
public class LoadCountriesService {

    private static final Logger LOG = LoggerFactory.getLogger(LoadCountriesService.class);

    @Autowired
    TempDataHolderService tempDataHolderService;

    @Value("${output.folder.data}")
    private String dataOutputFolder;



    public void loadCountriesFromFile(){

        List<Country> countries = new ArrayList();
        ObjectMapper mapper = new ObjectMapper();
        Integer total =0;
        try {
            File folder = new File("/Users/mmkamm/mygit/betting_system/BetMate/Bet_Mate_Output1/Data/Countries");
            LOG.info("Reading Data from json files");
            for(File countryFile:folder.listFiles()){
                if(!countryFile.getAbsolutePath().contains(".DS_Store")){
                    LOG.info("Reading file:"+countryFile.getAbsolutePath());
                    total++;
                    Country country = mapper.readValue(countryFile, Country.class);
                    countries.add(country);
                }
        }


        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOG.info("Number of countries loaded:" + total );
        tempDataHolderService.setCountries(countries);
    }

    public void readCountries() throws IOException {

        List<Country> countries = new ArrayList();
        File folder = new File("/Users/mmkamm/mygit/betting_system/BetMate/Bet_Mate_Output/Data/Countries_backup");
        Integer total =0;
        for(File countryFile:folder.listFiles()){
            total++;
            countries.add(readingCountriesAndCreateObjects(readCoutriesAndMap(countryFile)));
        }
        System.out.println("total:"+total);
        System.out.println("totalnum:");

        tempDataHolderService.setCountries(countries);
    }

    private HashMap<String,Object> readCoutriesAndMap(File countryFile) throws IOException {
        HashMap<String,Object> result = new ObjectMapper().readValue(countryFile, HashMap.class);
        return result;
    }

    private Country readingCountriesAndCreateObjects(HashMap<String,Object>  countryFile) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String countryName = (String) countryFile.get("countryName");
        String link = (String) countryFile.get("link");

        String leagueBuilder = link.substring(34);

        System.out.println(countryName);
        System.out.println(link);
        System.out.println(leagueBuilder);

        StringTokenizer st = new StringTokenizer(leagueBuilder,",/");

        String alterCountryName = null;
        String leagueName = null;
        String yearStr = null;

        if(st.hasMoreTokens()){
            alterCountryName = st.nextToken();
        }
        if(st.hasMoreTokens()){
            leagueName = st.nextToken();
        }
        if(st.hasMoreTokens()){
            yearStr = st.nextToken();
        }


        while (st.hasMoreTokens()) {

            System.out.println(st.nextToken());
        }
        // printing next token
//        String alterCountryName = st.nextToken();
//        String leagueName = st.nextToken();
//        String yearStr = st.nextToken();

        System.out.println(alterCountryName);
        System.out.println(leagueName);
        System.out.println(yearStr);


        LeagueByYear leagueByYear = new LeagueByYear()
            .createAlternateNames()
            .createTeams()
            .withName(leagueName)
            .withYearStr(yearStr);

        Country test = new Country()
            .createAlternateNames()
            .createLeagues()
            .withName(countryName)
            .withLink(link)
            .withNewAlternateName(alterCountryName)
            .withNewLeague(leagueByYear);

        mapper.writeValue(new File(dataOutputFolder+"/"+countryName+".json"), test);
        System.out.println("--------------------------------");
        //Country country = new Country(null,countryFile.get("countryName"),countryFile.get("link"));
return test;

    }
}
