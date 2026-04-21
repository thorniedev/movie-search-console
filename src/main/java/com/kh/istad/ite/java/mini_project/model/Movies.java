package com.kh.istad.ite.java.mini_project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Movies
{
    public int id;
    public String title;
    public String releaseDate;
    public double rating;
    public String trailerUrl;
    public String Category;
    public Double Price;
    public Integer Stock;
}
