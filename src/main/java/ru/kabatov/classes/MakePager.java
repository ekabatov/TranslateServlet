package ru.kabatov.classes;

import com.rmtheis.yandtran.language.Language;
import com.rmtheis.yandtran.translate.Translate;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Кабатов on 20.02.2017.
 */
public class MakePager {
    /**
     * Method return a dictionary in a table in html-format
     *
     * @return
     */
    public String enteredMap(Map<String,String> map) {
        /**
         * Number of rows in the table
         */
        int k = 32;
        StringBuilder sb = new StringBuilder();
        ArrayList<String> keys = new ArrayList<>(map.keySet());
        ArrayList<String> values = new ArrayList<>(map.values());
        sb.append("<table style='border : 1px solid black;font-size: 70%;"+
                "font-family: Verdana, Arial, Helvetica, sans-serif;"+
                "color: #333366;border-collapse: collapse;"+
                "'>");
        int sz = keys.size();
        /**
         * Usually in the table is 32 rows and 4 columns
         * if this is not enough in the table add for a few rows
         */
        if (sz > k*4) {
            int a = sz - k*4 + 4;
            a /= 4;
            k=4+a;
        }
        k=sz<k?sz:k;
        /**
         * Print map
         */
        for (int i = 0; i < k; i++) {
            //sb.append(new String (pair.getValue().getBytes ("cp1251"), "ISO-8859-1"));
            sb.append("<tr>");
            //if (i<k)
            sb.append("<td style='border : 1px solid black'>" + keys.get(i) + " - " + values.get(i));
            if ((i + k) < sz)
                sb.append("<td style='border : 1px solid black'>" + keys.get(i + k) + " - " + values.get(i + k));
            if ((i + 2 * k) < sz)
                sb.append("<td style='border : 1px solid black'>" + keys.get(i + 2 * k) + " - " + values.get(i + 2 * k));
            if ((i + 3 * k) < sz)
                sb.append("<td style='border : 1px solid black'>" + keys.get(i + 3 * k) + " - " + values.get(i + 3 * k));
            if ((i + 4 * k) < sz)
                sb.append("<td style='border : 1px solid black'>" + keys.get(i + 4 * k) + " - " + values.get(i + 4 * k));
           /* if ((i + 5 * k) < sz)
                sb.append("<td style='border : 1px solid black'>" + keys.get(i + 5 * k) + " - " + values.get(i + 5 * k));
            if ((i + 6 * k) < sz)
                sb.append("<td style='border : 1px solid black'>" + keys.get(i + 6 * k) + " - " + values.get(i + 6 * k));
            if ((i + 7 * k) < sz)
                sb.append("<td style='border : 1px solid black'>" + keys.get(i + 7 * k) + " - " + values.get(i + 7 * k));
            if ((i + 8 * k) < sz)
                sb.append("<td style='border : 1px solid black'>" + keys.get(i + 8 * k) + " - " + values.get(i + 8 * k));
            */
        }
        sb.append("</table>");
        return sb.toString();
    }
    /**
     * This method saves dictionary in map to the "HFBook.txt"
     * This file is in path "target/TranslateServlet-1.0-SNAPSHOT/WEB-INF/lib/HFBook.txt"
     * The first parameter - number of page on which stopped
     * Further method saves map to the file
     */
    public void sendTxt(String s, int numberPage, Map<String,String> map) {
        try {
            new File(s).delete();
            File file = new File(s);
            BufferedWriter writer = new BufferedWriter(new FileWriter(s));
            writer.write(numberPage+"\n");
            for (Map.Entry<String, String> pair : map.entrySet()) {
                writer.write(pair.getKey() + " - " + pair.getValue() + "\n");
            }
            writer.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }
    /**
     * This method translates input word and save to map
     * Method uses yandex-translator-api
     */
    public String translateTxt(String inputWord) {

        try {
            String trWord;
            Translate.setKey("trnsl.1.1.20170214T072009Z.a9935935c1ac9d55.08392e1f25f321ed68c0b9f4dc9b3a60b67a30d4");
            String translatedText = Translate.execute(inputWord, Language.ENGLISH, Language.RUSSIAN);
            trWord = translatedText;
            inputWord = "";
            return trWord;
        } catch (Exception e) {
            return null;
        }
    }
    public int firstStart(String s, Map<String, String> map){
        int numberPage=0;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(s));
                numberPage=Integer.parseInt(reader.readLine());
                String s1="";
                while ((s1 = reader.readLine()) != null) {
                    //s1 = new String(s1.getBytes("UTF8"), "cp1251");
                    String[] buf = s1.split(" - ");
                    map.put(buf[0], buf[1]);
                }
                reader.close();
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            }
            finally {
                return numberPage;
            }
    }
}
