package com.sahil.capstonestage2.models;

public class DataBaseModel {
    private String mUrl, mImg, mTitle, mDate, mSource, mAuthor;
    private String id;


    public DataBaseModel() {
    }

    public DataBaseModel(String id,String Url, String Img, String Title, String Date, String Source, String Author){
        this.id=id;
        this.mAuthor = Author;
        this.mDate = Date;
        this.mImg = Img;
        this.mSource = Source;
        this.mTitle = Title;
        this.mUrl = Url;
        }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmImg() {
        return mImg;
    }

    public void setmImg(String mImg) {
        this.mImg = mImg;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmSource() {
        return mSource;
    }

    public void setmSource(String mSource) {
        this.mSource = mSource;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
