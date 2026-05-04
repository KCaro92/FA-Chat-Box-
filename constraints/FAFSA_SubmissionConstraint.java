package constraints;

import app.Features;

public class FAFSA_SubmissionConstraint implements Constraint {
    @Override
    public boolean isSatisfiedBy(Features f) {
        return f.fafsaSubmission;
    }
}
