package app;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {

    private static final String LOG_FILE = "assistant_log.txt";

    public static void log(String question, String category, boolean wasVague) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true)) {

            fw.write("[" + LocalDateTime.now() + "]\n");
            fw.write("Question: " + question + "\n");
            fw.write("Category: " + category + "\n");
            fw.write("Vague: " + wasVague + "\n");
            fw.write("----------------------------------------\n");

        } catch (IOException e) {
            System.out.println("Error writing to log: " + e.getMessage());
        }
    }
}
