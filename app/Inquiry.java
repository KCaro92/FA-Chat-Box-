package app;

public class Inquiry {

    private String question;
    private Category category;

    public Inquiry(String question, Category category) {
        this.question = question;
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public Category getCategory() {
        return category;
    }

    @Override

    public String toString() {
        return "[" + category + "]" + question;
    }
}
