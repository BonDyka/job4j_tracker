package ru.job4j.tracker.models;

/**
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 08.01.2018.
 */
public class Comment {

    private String author;

    private String text;

    public Comment() { }

    public Comment(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
