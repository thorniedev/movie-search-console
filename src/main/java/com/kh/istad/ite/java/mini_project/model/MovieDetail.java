package com.kh.istad.ite.java.mini_project.model;

import lombok.Data;

@Data
public class MovieDetail {
    public String title;
    public String releaseDate;
    public double rating;
    public int runtime;
    public long budget;
    public String genres;
    public String origin;
}
