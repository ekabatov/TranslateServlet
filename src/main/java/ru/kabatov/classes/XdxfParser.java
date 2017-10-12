package ru.kabatov.classes;

import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
/**
 * Created by Кабатов on 10.10.2017.
 */

public class XdxfParser {
    private String path;
    public XdxfParser(String path) {
        this.path=path;
    }
    public ArrayList<String> xmlExtract() throws IOException{
        File input = new File(path);
        ArrayList<String> wordList = new ArrayList<>();
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(input);

            NodeList nodeList = document.getElementsByTagName("ar");

            for (int i = 0; i < nodeList.getLength(); i++) {
                wordList.add(nodeList.item(i).getNodeValue());
            }

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    return wordList;
    }
}
