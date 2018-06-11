package com.example.android.newsapp1;

public class News {


    /**
     * Date of publish
     */
    private String Date;

    /**
     * Title of the article
     */
    private String Title;

    /**
     * Section name (topic of the article)
     */
    private String SectionName;

    /**
     * Author of the article
     */
    private String Author;

    /**
     * Url
     */
    private String URL;

    /** Constant value that represents no author was provided for this article */
    private static final String NO_AUTHOR_PROVIDED = null;

    /**
     * Create a new Earthquake object.
     *  @param sectionName  is the section of the article topic
     * @param title is the title of the article
     * @param date is the date of publication
     * @param author is the author of the publication
     * @param url is the website to which you can go
     */
    public News(String sectionName, String title, String date, String author, String url) {
        Title = title;
        SectionName = sectionName;
        Author = author;
        Date = date;
        URL = url;

    }

    public News(String sectionName, String title, String date, String url) {
        Title = title;
        SectionName = sectionName;
        Date = date;
        URL = url;

    }

    public String getTitle() {
        return Title;
    }

    public String getSectionName() {
        return SectionName;
    }

    public String getAuthor() {
        return Author;
    }

    public String getDate() {
        return Date;
    }

    public String getUrl() {
        return URL;
    }

    public boolean hasAuthor() {
        return Author != NO_AUTHOR_PROVIDED;
    }


}


