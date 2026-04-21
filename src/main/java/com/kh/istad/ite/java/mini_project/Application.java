package com.kh.istad.ite.java.mini_project;

import com.kh.istad.ite.java.mini_project.model.Movies;
import com.kh.istad.ite.java.mini_project.model.MovieDetail;
import com.kh.istad.ite.java.mini_project.services.MovieService;
import com.kh.istad.ite.java.mini_project.services.MovieServiceImpl;
import com.kh.istad.ite.java.mini_project.utils.Ascii;
import com.kh.istad.ite.java.mini_project.utils.TableRender;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Application {

    // inject movie service
    private static final MovieService service = new MovieServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);

    private static int pageNumber = 1;
    private static String query = "";

    private static void updatePageNumber(int pageNum) {
        if (pageNum < 1) {
            pageNumber = 1;
        } else {
            pageNumber = pageNum;
        }
    }

    private static void handleCommand(String command) {

        if (command == null || command.isBlank()) return;

        String input = command.trim().toLowerCase();

        switch (input) {

            case "n" -> updatePageNumber(pageNumber + 1);

            case "p" -> updatePageNumber(pageNumber - 1);

            case "md" -> {
                System.out.print("[!] Enter movie ID: ");
                int id = Integer.parseInt(scanner.nextLine());

                MovieDetail detail = service.getDetail(id);
                TableRender.displayMovieDetail(detail);
            }

            case "e" -> System.exit(0);

            default -> {
                if (input.startsWith("g")) {
                    // g 3 → go page 3
                    String pageText = input.substring(1).trim();
                    updatePageNumber(Integer.parseInt(pageText));
                } else {
                    System.out.println("[!] Invalid command");
                }
            }
        }
    }

    public static void main(String[] args) {

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
        List<Movies> movies = service.search(query, pageNumber);
        TableRender.displayMovies(movies);

        System.out.printf("\nPage %d\n", pageNumber);
    }


    // SHOW OPTIONS
    private static String showOptions() {
        System.out.println("""
                [n] Next Page
                [p] Previous Page
                [g] Go to Page
                [md] Movie Detail
                [s] New Search
                [e] Exit
                """);

        System.out.print("Choose: ");
        return scanner.nextLine().trim().toLowerCase();
    }

    //  SPECIAL COMMANDS
    private static boolean handleSpecialCommand(String op) {

        switch (op) {

            case "md" -> {
                showMovieDetail(); // only detail
                return true; // STOP list refresh
            }

            case "s" -> {
                inputSearch(); // new search
                pageNumber = 1;
                return true;
            }

            case "e" -> System.exit(0);
        }

        return false;
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
