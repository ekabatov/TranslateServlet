package ru.kabatov.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import ru.kabatov.classes.MakePager;
import ru.kabatov.servlets.TranslateServlet;
/**
 * Created by Кабатов on 20.02.2017.
 */
public class MakePageServlet extends HttpServlet{
    /**
     * Word for translate
     */
    private String inputWord = "";
    /**
     * Translate word
     */
    private String trWord = "";
    /**
     * Number of read book's page on which stopped
     */
    private int numberPage;
    /**
     * Map of the dictionary
     */
    private static Map<String, String> map = new TreeMap<>();
    /**
     * This variable keep path to the HFBook.txt(dictionary)
     * library.txt is in folder "WEB_INF/lib"
     */
    private String s;
    /**
     * Variable for the tune started parameters
     */
    private boolean firstStart = true;

    MakePager mk = new MakePager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * Tune start parameters
         * In this bloc if servlet read the dictionary from file
         * and write to map
         */
        if (req.getParameter("firstStart")!=null) {
            firstStart = Boolean.parseBoolean(req.getParameter("firstStart"));
        }
        if (firstStart) {
            String s2 = req.getParameter("id");
            s = getServletContext().getRealPath("/WEB-INF/lib/"+s2);
            map.clear();
            numberPage = mk.firstStart(s, map);
            firstStart=false;
        }
        req.setAttribute("numberPage",numberPage);
        req.setAttribute("trWord",trWord);
        req.setAttribute("table", mk.enteredMap(map));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/TranslatePage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * It read word for translate
         */
        inputWord = req.getParameter("word");
        /**
         * If button "buttonTrans" is pressed
         * method "doPost" uses a method translateText() for translate
         */
        if (req.getParameter("buttonTrans") != null) {

            trWord=mk.translateTxt(inputWord);
            map.put(inputWord, trWord);
            inputWord="";
        }
        /**
         * If button "sendToTxt" is pressed
         * method "doPost" uses a method sendTxt() for save dictionary
         */
        if (req.getParameter("sendToTxt") != null) {
            numberPage = !req.getParameter("nmPage").equals("")?Integer.parseInt(req.getParameter("nmPage")):0;
            mk.sendTxt(s,numberPage,map);
        }
        if (req.getParameter("addToTxt") != null) {

            trWord=req.getParameter("trWord");
            trWord = new String(trWord.getBytes("windows-1252"), "windows-1251");
            map.put(inputWord, trWord);
            inputWord="";
        }
        doGet(req, resp);
    }
}
