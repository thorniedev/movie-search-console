package com.kh.istad.ite.java.mini_project.services;

import com.kh.istad.ite.java.mini_project.model.MovieDetail;
import com.kh.istad.ite.java.mini_project.model.Movies;

import java.util.List;

public interface MovieService {

    List<Movies> search(String query, int page);

    String getTrailer(int movieId);

    MovieDetail getDetail(int id);

    // additional
    List<Movies> getTrending();

    List<Movies> getPopular(int page);

    List<Movies> getTopRated(int page);

    List<Movies> getNowPlaying(int page);

    List<Movies> getSimilar(int movieId);

    List<String> getCast(int movieId);


}
