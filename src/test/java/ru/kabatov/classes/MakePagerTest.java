package ru.kabatov.classes;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Кабатов on 11.10.2017.
 */
public class MakePagerTest {
    @Test
    public void translateTxt() throws Exception {
        MakePager makePager = new MakePager();
        String s = makePager.translateTxt("exposure");
        System.out.println(s);
    }

}