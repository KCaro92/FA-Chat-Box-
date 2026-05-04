package constraints;

import app.FeatureExtractor;
import app.Features;

public class LoanApplyConstraint implements Constraint {
    @Override
    public boolean isSatisfiedBy(Features f) {
        String q = f.originalText.toLowerCase();

        if (!f.mentionsLoans)
            return false;

        boolean applyMatch = q.contains("apply") ||
                q.contains("application") ||
                FeatureExtractor.fuzzyWord(q, "apply") ||
                FeatureExtractor.fuzzyWord(q, "application") ||
                FeatureExtractor.fuzzyPhrase(q, "apply for") ||
                FeatureExtractor.fuzzyPhrase(q, "apply for a loan");

        return applyMatch;
    }
}
