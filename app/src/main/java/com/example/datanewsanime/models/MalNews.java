package com.example.datanewsanime.models;

public class MalNews {
    String title;
    String date;
    String authorUrl;
    String imageUrl;
    String intro;

    @Override
    public String toString() {
        return "MalNews{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", authorUrl='" + authorUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
