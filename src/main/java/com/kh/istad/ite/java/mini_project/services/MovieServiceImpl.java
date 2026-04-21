package com.kh.istad.ite.java.mini_project.services;

import com.kh.istad.ite.java.mini_project.model.MovieDetail;
import com.kh.istad.ite.java.mini_project.model.Movies;

import com.kh.istad.ite.java.mini_project.utils.ApiClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.List;

public class MovieServiceImpl implements MovieService {

    private static final String API_KEY = "195f5afe14eecda45b8d501bc81e96c9";

    private static final String BASE_URL = "https://api.themoviedb.org/3";


    @Override
    public List<Movies> search(String query, int page) {

        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);

        List<Movies> list = new ArrayList<>();

        // String url = BASE_URL + "/search/movie?query=" + query + "&page=" + page;
        String url = BASE_URL + "/search/movie?query="
                + encodedQuery + "&page=" + page;

        String res = ApiClient.get(url);
        JSONObject json = new JSONObject(res);

        JSONArray results = json.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {

            JSONObject obj = results.getJSONObject(i);

            Movies m = new Movies();
            m.setId(obj.getInt("id"));
            m.setTitle(obj.getString("title"));
            m.setReleaseDate(obj.optString("release_date", "N/A"));
            m.setRating(obj.getDouble("vote_average"));

            // ✅ trailer
            m.setTrailerUrl(getTrailer(m.getId()));

            list.add(m);
        }

        return list;
    }

    // 🎬 Trailer (ONLY YouTube + Trailer type)
    @Override
    public String getTrailer(int movieId) {

        String url = BASE_URL + "/movie/" + movieId + "/videos";

        String res = ApiClient.get(url);
        JSONObject json = new JSONObject(res);

        JSONArray results = json.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {

            JSONObject v = results.getJSONObject(i);

            if ("Trailer".equals(v.getString("type")) &&
                    "YouTube".equals(v.getString("site"))) {

                String key = v.getString("key");
                return "https://www.youtube.com/watch?v=" + key;
            }
        }

        return "N/A";
    }

    // 📄 Movie Detail
    @Override
    public MovieDetail getDetail(int id) {

        String url = BASE_URL + "/movie/" + id;

        String res = ApiClient.get(url);
        JSONObject obj = new JSONObject(res);

        MovieDetail d = new MovieDetail();

        d.setTitle(obj.getString("title"));
        d.setReleaseDate(obj.optString("release_date", "N/A"));
        d.setRating(obj.getDouble("vote_average"));
        d.setRuntime(obj.optInt("runtime", 0));
        d.setBudget(obj.optLong("budget", 0));

        // genres
        JSONArray genres = obj.getJSONArray("genres");
        StringBuilder g = new StringBuilder();

        for (int i = 0; i < genres.length(); i++) {
            g.append(genres.getJSONObject(i).getString("name"));
            if (i < genres.length() - 1) g.append(", ");
        }

        d.setGenres(g.toString());

        // origin
        JSONArray origin = obj.getJSONArray("origin_country");
        d.setOrigin(origin.toString());

        return d;
    }
}