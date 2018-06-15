package com.emanon.application.service.jsonWriters;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;
import com.emanon.application.domain.Country;

/**
 * Created by mmkamm on 13/05/2018.
 */
@Service
public class JsonWriter {

    public void createCountryJson(Country newCountry, String dataOutputFolder) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File(dataOutputFolder+"/"+newCountry.getCountryName()+".json"), newCountry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createJson(Object thisObj, String absolutePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File(absolutePath+".json"), thisObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void write() {
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        //For testing
//        User user = createDummyUser();
//
//        try {
//            //Convert object to JSON string and save into file directly
//            mapper.writeValue(new File("D:\\user.json"), user);
//
//            //Convert object to JSON string
//            String jsonInString = mapper.writeValueAsString(user);
//            System.out.println(jsonInString);
//
//            //Convert object to JSON string and pretty print
//            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
//            System.out.println(jsonInString);
//
//
//        } catch (JsonGenerationException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void read() {
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//
//            // Convert JSON string from file to Object
//            User user = mapper.readValue(new File("G:\\user.json"), User.class);
//            System.out.println(user);
//
//            // Convert JSON string to Object
//            String jsonInString = "{\"age\":33,\"messages\":[\"msg 1\",\"msg 2\"],\"name\":\"mkyong\"}";
//            User user1 = mapper.readValue(jsonInString, User.class);
//            System.out.println(user1);
//
//        } catch (JsonGenerationException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }



}
