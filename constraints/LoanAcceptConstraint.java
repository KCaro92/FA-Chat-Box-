package constraints;

import app.FeatureExtractor;
import app.Features;

public class LoanAcceptConstraint implements Constraint {
    @Override
    public boolean isSatisfiedBy(Features f) {
        String q = f.originalText.toLowerCase();

        if (!f.mentionsLoans)
            return false;

        boolean acceptMatch = q.contains("accept") ||
                q.contains("accepting") ||
                FeatureExtractor.fuzzyWord(q, "accept") ||
                FeatureExtractor.fuzzyWord(q, "accepting") ||
                FeatureExtractor.fuzzyPhrase(q, "accept a loan") ||
                FeatureExtractor.fuzzyPhrase(q, "accepting a loan");

        return acceptMatch;
    }
}
