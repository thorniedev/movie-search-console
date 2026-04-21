package com.kh.istad.ite.java.mini_project;

import com.kh.istad.ite.java.mini_project.model.MovieDetail;
import com.kh.istad.ite.java.mini_project.model.Movies;
import com.kh.istad.ite.java.mini_project.services.MovieService;
import com.kh.istad.ite.java.mini_project.services.MovieServiceImpl;
import com.kh.istad.ite.java.mini_project.utils.Ascii;
import com.kh.istad.ite.java.mini_project.utils.TableRender;

import java.util.List;
import java.util.Scanner;

public class Application {

    // inject movie service
    private static final MovieService service = new MovieServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);
    private static String mode = "search";

    private static int pageNumber = 1;
    private static String query = "";

    private static void updatePageNumber(int pageNum) {
        if (pageNum < 1) {
            pageNumber = 1;
        } else {
            pageNumber = pageNum;
        }
    }

    private static void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private static boolean handleSpecialCommand(String op) {

        switch (op) {

            case "md" -> {
                showMovieDetail();
                return true;
            }

            case "s" -> {
                inputSearch();
                mode = "search";
                pageNumber = 1;
                return true;
            }

            // ⭐ NEW SERVICES
            case "tr" -> {
                mode = "tr";
                pageNumber = 1;
                return true;
            }

            case "pop" -> {
                mode = "pop";
                pageNumber = 1;
                return true;
            }

            case "top" -> {
                mode = "top";
                pageNumber = 1;
                return true;
            }

            case "now" -> {
                mode = "now";
                pageNumber = 1;
                return true;
            }

            case "sim" -> {
                System.out.print("[!] Enter movie ID: ");
                int id = Integer.parseInt(scanner.nextLine());

                List<Movies> list = service.getSimilar(id);
                TableRender.displayMovies(list);

                pause();
                return true;
            }

            case "c" -> {
                System.out.print("[!] Enter movie ID: ");
                int id = Integer.parseInt(scanner.nextLine());

                List<String> cast = service.getCast(id);

                System.out.println("\n🎭 Cast:");
                for (String name : cast) {
                    System.out.println("- " + name);
                }

                pause();
                return true;
            }

            case "e" -> System.exit(0);
        }

        return false;
    }

    static void main(String[] args) {

        showWelcome();     // ASCII only once
        inputSearch();     // enter movie title
        mainLoop();        // start app loop

        /*
        System.out.print("🔍 Enter movie title: ");
        query = scanner.nextLine();

        while (true) {

            // show list
            List<Movies> movies = service.search(query, pageNumber);
            TableRender.displayMovies(movies);

            System.out.printf("\nPage %d\n", pageNumber);

            System.out.println("""
            [n] Next Page
            [p] Previous Page
            [g] Go to Page
            [md] Movie Detail
            [e] Exit
            """);

            System.out.print("Choose: ");
            String op = scanner.nextLine();

            if (op.equalsIgnoreCase("md")) {

                System.out.print("[!] Enter movie ID: ");
                int id = Integer.parseInt(scanner.nextLine());

                MovieDetail detail = service.getDetail(id);
                TableRender.displayMovieDetail(detail);

                // STOP here -> don't refresh list immediately
                continue;
            }

            handleCommand(op);
        }


        while (true) {

            List<Movies> movies = service.search(query, pageNumber);

            TableRender.displayMovies(movies);

            System.out.printf("\nPage %d\n", pageNumber);

            System.out.println("""
                    [n] Next Page
                    [p] Previous Page
                    [g] Go to Page (example: g 3)
                    [md] Movie Detail
                    [e] Exit
                    """);

            System.out.print("Choose: ");
            String op = scanner.nextLine();

            try {
                handleCommand(op);
            } catch (Exception e) {
                System.out.println("[!] Error: " + e.getMessage());
            }
        }
        */

    }

    // ASCII (ONLY ONCE)
    private static void showWelcome() {
        Ascii.showAscii();
        //System.out.println("🎬 Movie Search Application\n");
    }

    // 🔍 INPUT SEARCH
    private static void inputSearch() {
        System.out.print("🔍 Enter movie title: ");
        query = scanner.nextLine();
    }

    // MAIN LOOP
    private static void mainLoop() {
        while (true) {

            showMovieList();   // show table

            String op = showOptions(); // get option

            if (handleSpecialCommand(op)) {
                continue; // stop loop refresh when needed
            }
            handlePagination(op);
        }
    }

    // SHOW MOVIE LIST
    private static void showMovieList() {

        List<Movies> movies;

        switch (mode) {
            case "tr" -> movies = service.getTrending();
            case "pop" -> movies = service.getPopular(pageNumber);
            case "top" -> movies = service.getTopRated(pageNumber);
            case "now" -> movies = service.getNowPlaying(pageNumber);
            default -> movies = service.search(query, pageNumber);
        }

        TableRender.displayMovies(movies);

        System.out.printf("\nPage %d | Mode: %s\n", pageNumber, mode.toUpperCase());
    }
    /*
    private static void showMovieList() {
        List<Movies> movies = service.search(query, pageNumber);
        TableRender.displayMovies(movies);

        System.out.printf("\nPage %d\n", pageNumber);
    } */


    // SHOW OPTIONS
    private static String showOptions() {
        System.out.println("""
                [n] Next Page
                [p] Previous Page
                [g] Go to Page
                [tr] Trending
                [pop] Popular
                [top] Top Rated
                [now] Now Playing
                [sim] Similar Movies
                [c] Cast
                [md] Movie Detail
                [s] New Search
                [e] Exit
                """);

        System.out.print("Choose: ");
        return scanner.nextLine().trim().toLowerCase();
    }

    // MOVIE DETAIL
    private static void showMovieDetail() {

        System.out.print("[!] Enter movie ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        MovieDetail detail = service.getDetail(id);
        TableRender.displayMovieDetail(detail);

        System.out.println("\nPress Enter to go back...");
        scanner.nextLine();
    }

    // PAGINATION
    private static void handlePagination(String op) {

        switch (op) {
            case "n" -> pageNumber++;
            case "p" -> pageNumber = Math.max(1, pageNumber - 1);

            default -> {
                if (op.startsWith("g")) {
                    String pageText = op.substring(1).trim();
                    pageNumber = Integer.parseInt(pageText);
                }
            }
        }
    }

}
