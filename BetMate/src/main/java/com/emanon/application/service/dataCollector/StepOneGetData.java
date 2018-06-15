package com.emanon.application.service.dataCollector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.emanon.application.domain.Country;
import com.emanon.application.domain.LeagueByYear;
import com.emanon.application.thirdpartyservice.JsoupSpiderService;

/**
 * Created by mmkamm on 31/05/2018.
 */
@Service
public class StepOneGetData {

    @Autowired
    JsoupSpiderService jsoupSpiderService;

    @Value("${output.folder.data}")
    private String dataOutputFolder;

    //Given that is a live website, this rules can change any time.
    private List<String> urlExceptions = new ArrayList<>(Arrays.asList(
        "http://www.bari91.com/competition/FIFA_World_Cup_Russia_2018_-_Group_stage/2018",
        "http://www.bari91.com/competition/UEFA,_Super_Cup/2018",
        "http://www.bari91.com/competition/Total_CAF_Champions_League_-_Group_Stage/2018",
        "http://www.bari91.com/competition/AFC_Champions_League_-_Final_stage/2018",
        "http://www.bari91.com/competition/Scotiabank_CONCACAF_Champions_League/2018",
        "http://www.bari91.com/competition/OFC_Champions_League_2017_-_Final_Stage/2018",
        "http://www.bari91.com/competition/Copa_Bridgestone_Libertadores_-_Group_stage/2018",
        "http://www.bari91.com/competition/FIFA_Club_World_Cup_2017/2017",
        "http://www.bari91.com/competition/African_Cup_of_Nations_2019_-_Preliminary_round_Group_stage/2019",
        "http://www.bari91.com/competition/AFC_Asian_Cup_2019_-_Qualifying_Round_Part_2/2019",
        "http://www.bari91.com/competition/AFF_Suzuki_Cup_Championship__Myanmar_and_Philippines_-_Final_stage/2016",
        "http://www.bari91.com/competition/UEFA_European_Championship_Italy_and_San_Marino_2019_-_Preliminary_round_Group_Stage/2019",
        "http://www.bari91.com/competition/UEFA_European_Championship_France_2016_-_Final_stage/2016",
        "http://www.bari91.com/competition/Copa_Centroamericana_Panama_2017/2017",
        "http://www.bari91.com/competition/CONCACAF_Gold_Cup_USA_2017_-_Final_stage/2017",
        "http://www.bari91.com/competition/Caribbean_Cup_Martinique_2017_-_Final_stage/2017",
        "http://www.bari91.com/competition/OFC_Oceania_Nations_Cup_-_Final/2004",
        "http://www.bari91.com/competition/Copa America Centenario USA 2016 - Final stage/2016",
        "http://www.bari91.com/competition/FIFA_U-20_World_Cup_Egypt_2009_-_Final_stage/2009",
        "http://www.bari91.com/competition/FIFA_Confederations_Cup_2017_-_Final_Stage/2017",
        "http://www.bari91.com/competition/Rio_de_Janeiro_2016_-_Final_stage/2016",
        "http://www.bari91.com/competition/UEFA_Nations_League/2018-2019"
    ));

    private String WEB_LINK = "http://www.bari91.com/results-and-tables/clubs";
    private String BARI_COMPETITION_URL_PREFIX = "http://www.bari91.com/competition/";

    private static final Logger LOG = LoggerFactory.getLogger(StepOneGetData.class);

    public List<Country> getCountries() throws IOException {

        List<Country> countries = new ArrayList();

        Set<Integer> countryHashKeyList = new HashSet();

        Elements links = jsoupSpiderService.getLinks(WEB_LINK);

        int competitions = 0;

        for (Element link : links) {

            String urlInScope = link.attr("abs:href");

            if(urlInScope.startsWith(BARI_COMPETITION_URL_PREFIX)){
                competitions++;
                LOG.info("----------------------------");
                LOG.info("name:"+jsoupSpiderService.trim(link.text(),35));
                LOG.info("url:"+link.attr("abs:href"));

                if(link.attr("abs:href").isEmpty()){
                    String country =  link.text().replaceAll("[0-9]","");
                    country = country.replace("()","");
                    LOG.info("->"+country+ "has an empty link");
                }else if(link.text().isEmpty()){
                    LOG.info("->href has an empty name");
                }else {

                    String countryName = link.text();

                    Country countryInScope = createCountryInstance(countryName,urlInScope);

                    if(isUnique(countryHashKeyList,countryInScope)){
                        countryHashKeyList.add(countryInScope.hashCode());
                        countries.add(countryInScope);
                        LOG.info("->" + countryName + " has added.");
                    }
                }
            }
        }
        LOG.info("Competintions number:"+competitions);
        return countries;
    }

    private Country createCountryInstance(String countryName, String url){
        //Country country = new Country().createAlternateNames().withName(countryName).withLink(url);

        String leagueBuilder = url.substring(34);

        LOG.info(countryName);
        LOG.info(url);
        LOG.info(leagueBuilder);

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


        LOG.info(alterCountryName);
        LOG.info(leagueName);
        LOG.info(yearStr);


        LeagueByYear leagueByYear = new LeagueByYear()
            .createAlternateNames()
            .createTeams()
            .withName(leagueName)
            .withYearStr(yearStr);

        Country country = new Country()
            .createAlternateNames()
            .createLeagues()
            .withName(countryName)
            .withLink(url)
            .withNewAlternateName(alterCountryName)
            .withNewLeague(leagueByYear);

        return country;
    }

    private boolean isUnique(Set<Integer> countriesHashKey, Country country){

        int hashKeyInScope = country.hashCode();

        if(countriesHashKey.contains(hashKeyInScope)){
            LOG.info("->" + country.getCountryName() + " already exists");
            return false;
        }
        return true;
    }

    public List<Country> removeExceptions(List<Country> countries){

        Iterator<Country> i = countries.iterator();
        while (i.hasNext()) {
            Country country = i.next();

            String url = country.getLink();
            if(urlExceptions.contains(url)){
                i.remove();
            }
        }
        return countries;
    }



}
