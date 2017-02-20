package ru.kabatov.classes;

/**
 * Created by Йотун on 18.02.2017.
 */
public class Book {
    private String name;
    private String nameFile;

    public Book(String name, String nameFile) {
        this.name = name;
        this.nameFile = nameFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
}
