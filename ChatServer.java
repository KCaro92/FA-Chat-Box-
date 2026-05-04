package app;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ChatServer {

    private static final String PUBLIC_DIR = "C:/Users/kleo9/OneDrive/Desktop/AI/FinalProject/public/";

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.setExecutor(java.util.concurrent.Executors.newCachedThreadPool());

        // Serve index.html
        server.createContext("/", exchange -> serveFile(exchange, PUBLIC_DIR + "index.html", "text/html"));

        // Serve CSS
        server.createContext("/style.css", exchange -> serveFile(exchange, PUBLIC_DIR + "style.css", "text/css"));

        // Serve JS
        server.createContext("/script.js",
                exchange -> serveFile(exchange, PUBLIC_DIR + "script.js", "application/javascript"));

        // Serve logo
        server.createContext("/nmsu-logo.png",
                exchange -> serveFile(exchange, PUBLIC_DIR + "nmsu-logo.png", "image/png"));

        // Chat endpoint
        server.createContext("/chat", exchange -> {
            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                handleChatRequest(exchange);
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        });

        System.out.println("Server running at http://localhost:8080");
        server.start();
    }

    private static void handleChatRequest(HttpExchange exchange) throws IOException {
        try {
            // Read request body
            InputStream is = exchange.getRequestBody();
            String body = new String(is.readAllBytes()).trim();

            // Extract question safely
            String question = extractQuestion(body);
            if (question == null)
                question = "";
            question = question.trim();

            System.out.println("QUESTION RECEIVED: " + question);

            // Classify
            Category category = Classifier.classify(question);

            // Safety net
            if (category == null)
                category = Category.ALL_OTHER;

            // ⭐ UPDATED ANSWER SELECTION ⭐
            String answer;

            if (category == Category.FOLLOW_UP_SMART) {
                answer = FollowUpMemory.lastFollowUp; // <-- NEW BEHAVIOR
            } else {
                answer = AnswerBank.getAnswer(category);
            }

            // Safety net
            if (answer == null) {
                answer = "I'm not fully sure how to answer that yet. Please contact Financial Aid for more details.";
            }

            // ⭐ Escape JSON-breaking characters ⭐
            String safeAnswer = answer
                    .replace("\\", "\\\\")
                    .replace("\"", "'")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r");

            String jsonResponse = "{ \"answer\": \"" + safeAnswer + "\" }";

            // Send response
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(jsonResponse.getBytes());
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
            String error = "{ \"answer\": \"Error processing request.\" }";
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(500, error.getBytes().length);
            exchange.getResponseBody().write(error.getBytes());
            exchange.close();
        }
    }

    // ⭐ Robust JSON extractor ⭐
    private static String extractQuestion(String body) {
        if (body == null)
            return "";

        body = body.trim();

        int keyIndex = body.indexOf("question");
        if (keyIndex == -1)
            return "";

        int colon = body.indexOf(":", keyIndex);
        if (colon == -1)
            return "";

        int firstQuote = body.indexOf("\"", colon + 1);
        if (firstQuote == -1)
            return "";

        int secondQuote = body.indexOf("\"", firstQuote + 1);
        if (secondQuote == -1)
            return "";

        return body.substring(firstQuote + 1, secondQuote).trim();
    }

    private static void serveFile(HttpExchange exchange, String filePath, String contentType) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            String error = "File not found: " + filePath;
            exchange.sendResponseHeaders(404, error.length());
            exchange.getResponseBody().write(error.getBytes());
            exchange.close();
            return;
        }

        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        exchange.getResponseHeaders().set("Content-Type", contentType);
        exchange.sendResponseHeaders(200, bytes.length);
        exchange.getResponseBody().write(bytes);
        exchange.close();
    }
}
