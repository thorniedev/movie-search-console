package com.kh.istad.ite.java.mini_project.utils;

import com.kh.istad.ite.java.mini_project.model.Movies;
import com.kh.istad.ite.java.mini_project.model.MovieDetail;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class TableRender
{
    public static void displayMovies(List<Movies> movies) {

        Table table = new Table(
                5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL
        );

        String[] columns = {"ID", "Title", "Release", "Rating", "Trailer"};

        for (String column : columns) {
            table.addCell(column);
        }

        for (Movies m : movies) {
            table.addCell(String.valueOf(m.getId()));
            table.addCell(m.getTitle());
            table.addCell(m.getReleaseDate());
            table.addCell(String.valueOf(m.getRating()));
            table.addCell(m.getTrailerUrl());
        }

        System.out.println(table.render());
    }

    public static void displayMovieDetail(MovieDetail d) {

        Table table = new Table(
                2, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL
        );

        table.addCell("Title");
        table.addCell(d.getTitle());

        table.addCell("Release");
        table.addCell(d.getReleaseDate());

        table.addCell("Rating");
        table.addCell(d.getRating() + " / 10");

        table.addCell("Runtime");
        table.addCell(d.getRuntime() + " min");

        table.addCell("Budget");
        table.addCell("$" + d.getBudget());

        table.addCell("Genres");
        table.addCell(d.getGenres());

        table.addCell("Origin");
        table.addCell(d.getOrigin());

        System.out.println(table.render());
    }

}
