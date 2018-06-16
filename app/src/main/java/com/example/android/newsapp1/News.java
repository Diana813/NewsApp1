package com.example.android.newsapp1;

public class News {


    /**
     * Date of publish
     */
    private String mDate;

    /**
     * Title of the article
     */
    private String mTitle;

    /**
     * Section name (topic of the article)
     */
    private String mSectionName;

    /**
     * Author of the article
     */
    private String mAuthor;

    /**
     * Url
     */
    private String mURL;

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
        mTitle = title;
        mSectionName = sectionName;
        mAuthor = author;
        mDate = date;
        mURL = url;

    }

    public News(String sectionName, String title, String date, String url) {
        mTitle = title;
        mSectionName = sectionName;
        mDate = date;
        mURL = url;

    }

    public String getTitle() {
        return mTitle;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getDate() {
        return mDate;
    }

    public String getUrl() {
        return mURL;
    }

    public boolean hasAuthor() {
        return mAuthor != NO_AUTHOR_PROVIDED;
    }

}


