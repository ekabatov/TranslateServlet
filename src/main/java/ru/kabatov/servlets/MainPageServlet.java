package ru.kabatov.servlets;


import ru.kabatov.classes.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Йотун on 18.02.2017.
 */
public class MainPageServlet extends HttpServlet {
    private String s;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        s = getServletContext().getRealPath("/WEB-INF/lib/BooksList.txt");
        req.setAttribute("books", BooksList());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/MainPage.jsp");
        dispatcher.forward(req, resp);
    }

    private ArrayList<Book> BooksList() {
        ArrayList<Book> books = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(s));
            String s1 = "";
            while (!(s1 = reader.readLine()).equals("")) {
                String[] buf = s1.split(" - ");
                books.add(new Book(buf[0], buf[1]));
            }
            reader.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } finally {
            return books;
        }
    }
}
