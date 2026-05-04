package constraints;

import app.FeatureExtractor;
import app.Features;

public class LoanCancelConstraint implements Constraint {
    @Override
    public boolean isSatisfiedBy(Features f) {
        String q = f.originalText.toLowerCase();

        if (!f.mentionsLoans)
            return false;

        boolean cancelMatch = q.contains("cancel") ||
                q.contains("decline") ||
                FeatureExtractor.fuzzyWord(q, "cancel") ||
                FeatureExtractor.fuzzyWord(q, "decline") ||
                FeatureExtractor.fuzzyPhrase(q, "cancel a loan") ||
                FeatureExtractor.fuzzyPhrase(q, "decline a loan");

        return cancelMatch;
    }
}
