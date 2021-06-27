package com.example.datanewsanime.models;

import java.util.List;

public class MalFullData extends MalInfoAnime{
    List<String> genres;
    List<String> producers;
    List<String> opening_themes;
    List<String> ending_themes;
    List<String> studios;
    String members;
    String source;

    @Override
    public String toString() {
        return "MalFullData{" +
                "genres=" + genres +
                ", producers=" + producers +
                ", opening_themes=" + opening_themes +
                ", ending_themes=" + ending_themes +
                ", studios=" + studios +
                ", members='" + members + '\'' +
                ", source='" + source + '\'' +
                ", malId='" + malId + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", title='" + title + '\'' +
                ", airing=" + airing +
                ", type='" + type + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", episodes='" + episodes + '\'' +
                ", score='" + score + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    public List<String> getStudios() {
        return studios;
    }

    public void setStudios(List<String> studios) {
        this.studios = studios;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getProducers() {
        return producers;
    }

    public void setProducers(List<String> producers) {
        this.producers = producers;
    }

    public List<String> getOpening_themes() {
        return opening_themes;
    }

    public void setOpening_themes(List<String> opening_themes) {
        this.opening_themes = opening_themes;
    }

    public List<String> getEnding_themes() {
        return ending_themes;
    }

    public void setEnding_themes(List<String> ending_themes) {
        this.ending_themes = ending_themes;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
