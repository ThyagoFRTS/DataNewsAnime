package com.example.datanewsanime.handlejson;

import com.example.datanewsanime.models.MalFullData;
import com.example.datanewsanime.models.MalInfoAnime;
import com.example.datanewsanime.models.MalNews;
import com.example.datanewsanime.models.MalNextSeason;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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



    public static MalNews getMalNews(String content){
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

    public static List<MalNextSeason> getMalNextSeason(String content){
        try {
            JSONArray ja;
            JSONArray jaNames;
            JSONObject jo;
            JSONObject joNames;

            List<MalNextSeason> animes = new ArrayList<>();
            List<String> names;
            MalNextSeason mns;

            jo = new JSONObject(content);
            ja = jo.getJSONArray("anime");

            for (int i = 0; i < ja.length(); i++){
                jo = ja.getJSONObject(i);
                mns = new MalNextSeason();

                mns.setMalId(jo.getString("mal_id"));
                mns.setTitle(jo.getString("title"));
                mns.setImageUrl(jo.getString("image_url"));
                mns.setSynopsis(jo.getString("synopsis"));
                mns.setType(jo.getString("type"));
                mns.setMembers(jo.getString("members"));
                mns.setSource(jo.getString("source"));

                jaNames = jo.getJSONArray("genres");
                names = new ArrayList<>();
                for (int j = 0; j < jaNames.length(); j++){
                    joNames = jaNames.getJSONObject(j);
                    names.add(joNames.getString("name"));
                }
                mns.setGenres(names);

                jaNames = jo.getJSONArray("producers");
                names = new ArrayList<>();
                for (int j = 0; j < jaNames.length(); j++){
                    joNames = jaNames.getJSONObject(j);
                    names.add(joNames.getString("name"));
                }
                mns.setProducers(names);

                animes.add(mns);
            }

            return animes;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static MalFullData getMalFullData(String content){
        try {
            JSONArray ja;
            JSONArray jaNames;
            JSONObject jo;
            JSONObject joNames;
            MalFullData mfd = new MalFullData();
            List<String> names;

            jo = new JSONObject(content);


            mfd.setMalId(jo.getString("mal_id"));
            mfd.setImageUrl(jo.getString("image_url"));
            mfd.setTitle(jo.getString("title"));
            mfd.setAiring(Boolean.parseBoolean(jo.getString("airing")));
            mfd.setType(jo.getString("type"));
            mfd.setSynopsis(jo.getString("synopsis"));
            mfd.setEpisodes(jo.getString("episodes"));
            mfd.setScore(jo.getString("score"));
            mfd.setStartDate(jo.getString("start_date"));
            mfd.setEndDate(jo.getString("end_date"));

            mfd.setMembers(jo.getString("members"));
            mfd.setSource(jo.getString("source"));

            jaNames = jo.getJSONArray("genres");
            names = new ArrayList<>();
            for (int j = 0; j < jaNames.length(); j++){
                joNames = jaNames.getJSONObject(j);
                names.add(joNames.getString("name"));
            }
            mfd.setGenres(names);

            jaNames = jo.getJSONArray("producers");
            names = new ArrayList<>();
            for (int j = 0; j < jaNames.length(); j++){
                joNames = jaNames.getJSONObject(j);
                names.add(joNames.getString("name"));
            }
            mfd.setProducers(names);

            jaNames = jo.getJSONArray("opening_themes");
            names = new ArrayList<>();
            for (int j = 0; j < jaNames.length(); j++){
                joNames = jaNames.getJSONObject(j);
                names.add(joNames.getString(Integer.toString(j)));
            }
            mfd.setOpening_themes(names);

            jaNames = jo.getJSONArray("endning_themes");
            names = new ArrayList<>();
            for (int j = 0; j < jaNames.length(); j++){
                joNames = jaNames.getJSONObject(j);
                names.add(joNames.getString(Integer.toString(j)));
            }
            mfd.setEnding_themes(names);

            jaNames = jo.getJSONArray("studios");
            names = new ArrayList<>();
            for (int j = 0; j < jaNames.length(); j++){
                joNames = jaNames.getJSONObject(j);
                names.add(joNames.getString("name"));
            }
            mfd.setEnding_themes(names);

            return mfd;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String getMalIdFromTitle(String content){
        try {
            JSONArray ja;
            JSONObject jo;
            MalInfoAnime iam = new MalInfoAnime();

            jo = new JSONObject(content);
            ja = jo.getJSONArray("results");
            jo = ja.getJSONObject(0);



            return jo.getString("mal_id");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
