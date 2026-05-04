package constraints;

import app.Features;

public class FAFSA_RequirementsConstraint implements Constraint {
    @Override
    public boolean isSatisfiedBy(Features f) {
        return f.fafsaRequirements;
    }
}
