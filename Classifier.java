package app;

import constraints.FinancialAidCSP;
import java.util.*;

public class Classifier {

    private static FinancialAidCSP csp = new FinancialAidCSP();

    public static Category classify(String question) {

        Features f = FeatureExtractor.extract(question);

        // If vague, trigger smart follow-up immediately
        if (f.isVague) {
            Map<Category, Integer> scores = csp.getScores(f);
            List<Category> topTwo = getTopTwoCategories(scores);
            String followUp = generateSmartFollowUp(topTwo);

            FollowUpMemory.lastFollowUp = followUp;
            return Category.FOLLOW_UP_SMART;
        }

        // if complex
        if (f.isAccountSpecific) {
            return Category.ACCOUNT_SPECIFIC;
        }

        // Otherwise normal CSP classification
        return csp.solve(f);
    }

    // Gets the top 2 scoring categories
    private static List<Category> getTopTwoCategories(Map<Category, Integer> scores) {
        return scores.entrySet()
                .stream()
                .filter(e -> e.getKey() != Category.VAGUE_QUESTION) // exclude vague
                .filter(e -> e.getValue() > 0) // ignore zero-score categories
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(2)
                .map(Map.Entry::getKey)
                .toList();
    }

    // Generats a nature follow up question
    private static String generateSmartFollowUp(List<Category> topTwo) {
        if (topTwo.size() < 2) {
            return "I want to help! Can you tell me a little more about what you need?";
        }

        String c1 = topTwo.get(0).getFriendlyName();
        String c2 = topTwo.get(1).getFriendlyName();

        return "I want to help! Are you asking about " + c1 + " or " + c2 + "?";
    }
}
