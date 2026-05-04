package constraints;

import app.Features;

public class FAFSA_RenewalConstraint implements Constraint {
    @Override
    public boolean isSatisfiedBy(Features f) {
        return f.fafsaRenewal;
    }
}
