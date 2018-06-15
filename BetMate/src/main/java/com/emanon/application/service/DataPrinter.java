package com.emanon.application.service;

import org.springframework.stereotype.Service;

/**
 * Created by mmkamm on 04/06/2018.
 */
@Service
public class DataPrinter {

    public void consolePrinter(String text){
        System.out.println(text);
    }
}
