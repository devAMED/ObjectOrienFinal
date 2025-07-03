package oop.finalexam.t3;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.nio.file.*;
import java.time.Duration;
import java.util.Properties;
import java.util.Scanner;

/**
 * Console chat-bot that talks to the REST blog server.
 *
 * Configuration file (project root) – bot.conf
 * --------------------------------------------
 * url  = https://max.ge/final/t3/93746182/index.php
 * name = Amd BOT
 *
 * Supported commands
 * ------------------
 * 1) POST  /?api=blogs    – create blog post
 * 2) GET   /?api=blogs    – list all posts
 * 3) GET   /?api=stats    – site statistics
 * 0) Exit
 *
 * If bot.conf is missing, default URL and name are used.
 */
public class ChatBot {

    /* ---------- load config ---------- */
    private static final String CONF_PATH = "bot.conf";
    private static String BOT_NAME = "Bot";
    private static String BASE_URL = "https://max.ge/final/t3/93746182/index.php";

    static {
        Properties p = new Properties();
        try {
            p.load(Files.newBufferedReader(Paths.get(CONF_PATH)));
            BOT_NAME = p.getProperty("name", BOT_NAME).trim();
            BASE_URL = p.getProperty("url",  BASE_URL).trim();
        } catch (IOException e) {
            System.out.println("⚠️  Could not read " + CONF_PATH +
                    " – using defaults.");
        }
    }

    /* ---------- main ---------- */
    public static void main(String[] args) {
        new ChatBot().run();
    }

    /* ---------- instance state ---------- */
    private final HttpClient http = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    private final Scanner in = new Scanner(System.in);

    private void run() {
        System.out.printf("%n%s connected to %s%n%n", BOT_NAME, BASE_URL);

        while (true) {
            System.out.println("""
                    1) Create blog post
                    2) View all posts
                    3) View site stats
                    0) Exit""");
            System.out.print(BOT_NAME + "> ");
            String choice = in.nextLine().trim();

            switch (choice) {
                case "1" -> createPost();
                case "2" -> viewPosts();
                case "3" -> viewStats();
                case "0" -> {
                    System.out.println("Good-bye!");
                    return;
                }
                default -> System.out.println("Invalid option.\n");
            }
        }
    }

    /* ---------- actions ---------- */
    private void createPost() {
        System.out.print("Title   : "); String title   = in.nextLine().trim();
        System.out.print("Author  : "); String author  = in.nextLine().trim();
        System.out.print("Content : "); String content = in.nextLine().trim();

        String json = String.format(
                "{\"title\":\"%s\",\"content\":\"%s\",\"author\":\"%s\"}",
                esc(title), esc(content), esc(author.isBlank() ? "Anonymous" : author));

        send("POST", "?api=blogs", json);
    }

    private void viewPosts() { send("GET", "?api=blogs", null); }
    private void viewStats() { send("GET", "?api=stats", null); }

    /* ---------- HTTP helper ---------- */
    private void send(String method, String endpoint, String body) {
        try {
            HttpRequest.Builder b = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + endpoint))
                    .timeout(Duration.ofSeconds(15));

            if ("POST".equals(method)) {
                b.POST(HttpRequest.BodyPublishers.ofString(body))
                        .header("Content-Type", "application/json");
            } else {
                b.GET();
            }

            HttpResponse<String> r = http.send(b.build(),
                    HttpResponse.BodyHandlers.ofString());

            System.out.println("\nStatus : " + r.statusCode());
            System.out.println(r.body() + "\n");

        } catch (IOException | InterruptedException ex) {
            System.out.println("❌  Network error: " + ex.getMessage() + "\n");
        }
    }

    private static String esc(String s) {
        return s.replace("\"", "\\\"");
    }
}
