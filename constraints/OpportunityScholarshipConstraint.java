package constraints;

import app.Features;

public class OpportunityScholarshipConstraint implements Constraint {
    @Override
    public boolean isSatisfiedBy(Features f) {
        return f.mentionsOpportunityScholarship;
    }
}
