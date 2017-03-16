package ru.kabatov.classes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by Кабатов on 20.02.2017.
 */
public class CopyClass {
    public static void main(String[] args) {
        String[] fileName = new String[4];
        fileName[0]="HFBook.txt";
        fileName[1] = "Internet.txt";
        fileName[2] = "Marduk.txt";
        fileName[3] = "DsO.txt";
        String to = System.getProperty("user.dir")+"/src/main/webapp/WEB-INF/lib/";
        String from = System.getProperty("user.dir")+"/target/TranslateServlet-1.0-SNAPSHOT/WEB-INF/lib/";
        for (String k : fileName){
            File file = new File(to+k);
            file.delete();
            try {
                Files.copy(new File(from + k).toPath(), new File(to + k).toPath());
            }
            catch (IOException e){

            }
        }
    }

}
