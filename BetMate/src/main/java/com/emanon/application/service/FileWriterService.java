package com.emanon.application.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Service;

/**
 * Created by mmkamm on 13/05/2018.
 */
@Service
public class FileWriterService {

    public void writer(String filename, String path, String mode, String data) throws IOException {

        try {
            File file = new File(path + "/" + filename + ".csv");
            if (mode.equals("new")) {
                file.delete();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//            Date date = new Date();//System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
            //true = append file
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            //This will add a new line to the file content
            try (PrintWriter pw = new PrintWriter(bw)) {
                //This will add a new line to the file content
                //pw.println("---------------------------------------------------------------------------------------------");
                // Below three statements would add three mentioned Strings to the file in new lines.
                //System.out.println(C.output_folder +"f & p"+C.output_path);
                //pw.println(dateFormat.format(date) + " " + data);
                pw.println(data);
                pw.close();
            }

        } catch (IOException e) {
        }

    }


}
