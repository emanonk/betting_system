package com.emanon.application.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import com.emanon.application.domain.Country;
import com.emanon.application.service.FolderStructureService;
import com.emanon.application.service.TempDataHolderService;
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
    MyChromeDriver driver;

    @Value("${output.folder.data}")
    private String dataOutputFolder;

    public void index() throws InterruptedException, IOException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("MainController->index");

        folderStructureService.initialiseOutputFolders();


        tempDataHolderService.setCountries(getCoutries());


//
//
//        Field[] fields = Country.class.getDeclaredFields();
//        for(Field fie:fields) {
//            System.out.println(fie.getName());
//        }
//
//        Country hi = new Country(1l, "Greece");
//        String gr = (String) hi.getClass().getMethod("getCountryName").invoke(hi);
//
//        System.out.println("-->"+gr);
//        String url = "http://www.bari91.com/results-and-tables/clubs";
//        print("Fetching %s...", url);
//
//        Document doc = Jsoup.connect(url).get();
//        Elements links = doc.select("a[href]");
//        Elements media = doc.select("[src]");
//        Elements imports = doc.select("link[href]");

//        print("\nMedia: (%d)", media.size());
//        for (Element src : media) {
//            if (src.tagName().equals("img"))
//                print(" * %s: <%s> %sx%s (%s)",
//                    src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
//                    trim(src.attr("alt"), 20));
//            else
//                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
//        }
//
//        print("\nImports: (%d)", imports.size());
//        for (Element link : imports) {
//            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
//        }

//        print("\nLinks: (%d)", links.size());
//        for (Element link : links) {
//            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
//        }


//
//        WebDriver chrome = driver.initChromeDriver();
//
//        chrome.get("http://www.bari91.com/results-and-tables/clubs");
//        Thread.sleep(5000);
//        System.out.println(chrome.getPageSource());
//
//
//        System.out.println("__________________________________________________________");
//        List<WebElement> ele =  chrome.findElements(By.cssSelector("input[class='country_flag']"));
//        for(WebElement elem:ele){
//            System.out.println(elem.getText());
//        }
//
//        driver.killDriver();


    }

    private List<Country> getCoutries() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        List<Country> countries = new ArrayList<>();

        String url = "http://www.bari91.com/results-and-tables/clubs";
        print("Fetching %s...", url);

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            //print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));

            if(link.attr("abs:href").isEmpty()){
               String country =  link.text().replaceAll("[0-9]","");
                country = country.replace("()","");
                //System.out.println(country);
                //System.out.println("Empty link");
            }else if(link.text().isEmpty()){
                //System.out.println("Empty name");

            }else{

                String countryName = link.text();
                String countryUrl = link.attr("abs:href");
                Country test = new Country(null,countryName,countryUrl);
                countries.add(test);
                mapper.writeValue(new File(dataOutputFolder+"/"+countryName+".json"), test);
                System.out.println(countryName+" has been added");
            }

        }


        return countries;

    }

    private void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}
