package app;

import java.util.Scanner;

public class TestClassifier {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Category lastCategory = null;

        System.out.println("Financial Aid Assistant\n");
        System.out.println("Ask me anything about financial aid!\n");

        while (true) {

            System.out.print("You: ");
            String studentQuestion = scanner.nextLine();

            if (studentQuestion.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            String q = studentQuestion.toLowerCase();

            if (lastCategory == Category.FOLLOW_UP_LOAN) {

                // User clearly chooses an option
                if (q.contains("accept")) {
                    lastCategory = Category.PROCESS_ACCEPT_LOANS;
                    System.out.println("Assistant: " + AnswerBank.getAnswer(Category.PROCESS_ACCEPT_LOANS) + "\n");
                    continue;
                }

                if (q.contains("apply") || q.contains("application") || q.contains("take out")) {
                    lastCategory = Category.LOAN_APPLY;
                    System.out.println("Assistant: " + AnswerBank.getAnswer(Category.LOAN_APPLY) + "\n");
                    continue;
                }

                if (q.contains("cancel") || q.contains("decline")) {
                    lastCategory = Category.LOAN_CANCEL;
                    System.out.println("Assistant: " + AnswerBank.getAnswer(Category.LOAN_CANCEL) + "\n");
                    continue;
                }

                // If user says something unrelated → exit follow-up mode
                if (!q.contains("loan")) {
                    lastCategory = null;
                    // fall through to classifier
                } else {
                    // Still vague → ask again
                    System.out.println("Assistant: Got it! Do you want to apply for, accept, or cancel a loan?\n");
                    lastCategory = Category.FOLLOW_UP_LOAN;
                    continue;
                }
            }

            // classifier handles everything else
            Category category = Classifier.classify(studentQuestion);

            // If classifier says FOLLOW_UP_SMART → use the stored follow-up
            if (category == Category.FOLLOW_UP_SMART) {
                System.out.println("Assistant: " + FollowUpMemory.lastFollowUp + "\n");

                // If the follow-up is about loans → enter loan follow-up mode
                if (FollowUpMemory.lastFollowUp.toLowerCase().contains("loan")) {
                    lastCategory = Category.FOLLOW_UP_LOAN;
                } else {
                    lastCategory = Category.FOLLOW_UP_SMART;
                }

                continue;
            }

            // Normal category = answer normally
            String answer = AnswerBank.getAnswer(category);
            System.out.println("Assistant: " + answer + "\n");

            lastCategory = category;
        }

        scanner.close();
    }
}
