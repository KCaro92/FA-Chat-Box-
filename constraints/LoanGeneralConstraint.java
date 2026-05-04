package constraints;

import app.Features;

public class LoanGeneralConstraint implements Constraint {
    @Override
    public boolean isSatisfiedBy(Features f) {
        if (!f.mentionsLoans) {
            return false;
        }

        String q = f.originalText.toLowerCase();

        // If it's clearly apply/accept/cancel, let the specific constraints handle it
        if (q.contains("apply") || q.contains("application") || q.contains("take out")
                || q.contains("accept") || q.contains("accepting")
                || q.contains("cancel") || q.contains("decline")) {
            return false;
        }

        // Otherwise it's a general loan question
        return true;
    }
}
