package constraints;

import app.Features;

public class FAFSA_CorrectionConstraint implements Constraint {
    @Override
    public boolean isSatisfiedBy(Features f) {
        return f.fafsaCorrection;
    }
}
