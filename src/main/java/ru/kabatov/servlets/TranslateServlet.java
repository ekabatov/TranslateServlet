package ru.kabatov.servlets;

import com.rmtheis.yandtran.language.Language;
import com.rmtheis.yandtran.translate.Translate;
import ru.kabatov.classes.MakePager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Кабатов on 14.02.2017.
 * This servlet make a table often used words
 * in the read book
 */

/**
 * Link to GitHub repo:
 * https://github.com/rmtheis/yandex-translator-java-api
 */
public class TranslateServlet extends HttpServlet {
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
        resp.setContentType("text/html; charset=windows-1251");
        PrintWriter writer = resp.getWriter();
        req.setCharacterEncoding("windows-1251");

        /**
         * Tune start parameters
         * In this bloc if servlet read the dictionary from file
         * and write to map
         */
        if (req.getParameter("firstStart")!=null) {
            firstStart = Boolean.parseBoolean(req.getParameter("firstStart"));

        }
        if (firstStart) {
            firstStart = false;
            String s2 = req.getParameter("id");
            s = getServletContext().getRealPath("/WEB-INF/lib/"+s2);
            map.clear();
            numberPage = mk.firstStart(s, map);
        } else {

        }
        writer.append("<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "     <title>Google Translate</title>" +
                "<style type=\"text/css\">" +
                "   TABLE {" +
                "    font-size: 70%;" +
                "    font-family: Verdana, Arial, Helvetica, sans-serif;" +
                "    color: #333366;" +
                "    border-collapse: collapse;" +
                "   }" +
                "   P {"+
                "    font-size: 70%;}" +
                "  </style>" +
                "<style>"+
                "#txt{"+
                "   height:10px;}"+
                "#btn{"+
                "   height:17px;"+
                "   font-size:90%;}"+
                "</style>"+
                "</head>" +
                "<body>" +
                "     <form action='" + req.getContextPath()+"/BookTranslate?id="+
                req.getParameter("id")+"&firstStart="+"false"+
                "' method='post'>" +
                "         <p>Word : <input type='text' name='word' id ='txt'>" +
                "         <input type='submit' value='Translate' id ='btn' name='buttonTrans'>" +
                "         Translate : <input type='text' name='trWord' id ='txt' value='" + trWord + "'>" +
                "         Page : <input type='text' name='nmPage' id ='txt' value='" + numberPage + "'>"+
                "         <input type='submit' value='Save Txt' id ='btn' name='sendToTxt'></p>" +
                "" +
                mk.enteredMap(map) +
                //"         <input type='submit' value='Clear Txt' name='clearTxt'>"+
                "</form>"+
                "</body>" +
                "</html>");
        writer.flush();
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
        doGet(req, resp);
    }






    /*private void cleartxt(){
        try {
            new File(s).delete();
            File file = new File(s);
            BufferedWriter writer = new BufferedWriter(new FileWriter(s));
            writer.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }*/


}
