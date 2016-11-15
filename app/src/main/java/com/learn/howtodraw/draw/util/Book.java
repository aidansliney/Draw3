package com.learn.howtodraw.draw.util;

import java.lang.reflect.Array;

/**
 * Created by aidansliney on 15/11/2016.
 */

public class Book {

    private String bookname;
    private int level;
    private String title;
    private String cover;
    private String banner;

    private String tutorial;   // should be array of tutorials

        // constructor
        public Book(String bookname, int level) {
            this.bookname = bookname;
            this.level = level;
        }

        // getter
        public String getBookname() { return bookname; }
        public int getLevel() { return level; }
        // setter

        public void setBookname(String bookname) { this.bookname = bookname; }
        public void setlevel(int level) { this.level = level; }
}
