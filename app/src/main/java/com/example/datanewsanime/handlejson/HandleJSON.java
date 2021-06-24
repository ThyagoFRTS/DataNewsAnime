package com.example.datanewsanime.handlejson;

import com.example.datanewsanime.models.MalInfoAnime;
import com.example.datanewsanime.models.MalNews;
import com.example.datanewsanime.models.MalNextSeason;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HandleJSON {





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
            System.out.println("-----IN HANDLE-----");
            System.out.println(content);

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

}
