package ru.kabatov.classes;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Кабатов on 11.10.2017.
 */
public class XdxfParserTest {
    @Test
    @Ignore
    public void xmlExtract() throws Exception {
        //String path = ClassLoader.getSystemClassLoader().getResource("dict.xml").getPath();
        //String path = ClassLoader.getSystemResource("dict.xml").getPath();
        String path = System.getProperty("user.dir")+"/src/main/resources/dict.xml";

        XdxfParser xdxfParser = new XdxfParser(path);
        ArrayList<String> fg=xdxfParser.xmlExtract();
        for (int i=0;i<100;i++) {
            System.out.println(fg.get(i));
        }
    }

}