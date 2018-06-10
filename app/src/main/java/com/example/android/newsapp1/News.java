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
     * Url
     */
    private String URL;

    /**
     * Create a new Earthquake object.
     *  @param sectionName          is the section of the article topic
     * @param title          is the title of the article
     * @param date is the date of publication
     * @param url                is the website to which you can go
     */
    public News(String sectionName, String title, String date, String url) {
        Title = title;
        SectionName = sectionName;
        Date = date;
        URL = url;

    }

    /**
     * Get the title.
     */
    public String getTitle() {
        return Title;
    }


    /**
     * Get the magazine.
     */
    public String getSectionName() {
        return SectionName;
    }


    /**
     * Get the date of publish.
     */
    public String getDate() {
        return Date;
    }

    /**
     * Get the url
     */
    public String getUrl() {
        return URL;
    }

}


