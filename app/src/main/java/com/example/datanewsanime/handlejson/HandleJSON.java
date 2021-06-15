package com.example.datanewsanime.handlejson;

import com.example.datanewsanime.models.MalInfoAnime;
import com.example.datanewsanime.models.MalNews;

import org.json.JSONArray;
import org.json.JSONObject;

public class HandleJSON {
    String searchAnimeTitleId = "https://api.jikan.moe/v3/anime/";
    String searchMangaTitleId = "https://api.jikan.moe/v3/manga/";
    String searchAnimeTitle = "https://api.jikan.moe/v3/search/anime?q=";
    String searchMangaTitle = "https://api.jikan.moe/v3/search/manga?q=";



    public static MalInfoAnime getMalInfoAnime(String content){
        try {
            JSONArray ja;
            JSONObject jo;
            MalInfoAnime iam = new MalInfoAnime();

            jo = new JSONObject(content);
            ja = jo.getJSONArray("results");
            jo = ja.getJSONObject(0);

            iam.setMalId(jo.getString("mal_id"));
            iam.setImageUrl(jo.getString("image_url"));
            iam.setTitle(jo.getString("title"));
            iam.setAiring(Boolean.parseBoolean(jo.getString("airing")));
            iam.setType(jo.getString("type"));
            iam.setSynopsis(jo.getString("synopsis"));
            iam.setEpisodes(jo.getString("episodes"));
            iam.setScore(jo.getString("score"));
            iam.setStartDate(jo.getString("start_date"));
            iam.setEndDate(jo.getString("end_date"));
            return iam;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static MalNews getMalINews(String content){
        try {
            JSONArray ja;
            JSONObject jo;
            MalNews mn = new MalNews();

            jo = new JSONObject(content);
            ja = jo.getJSONArray("articles");
            jo = ja.getJSONObject(0);

            mn.setTitle(jo.getString("title"));
            mn.setDate(jo.getString("date"));
            mn.setAuthorUrl(jo.getString("author_url"));
            mn.setImageUrl(jo.getString("image_url"));
            mn.setIntro(jo.getString("intro"));


            return mn;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
