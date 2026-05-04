package constraints;

import app.Features;

public class FAFSA_StatusConstraint implements Constraint {
    @Override
    public boolean isSatisfiedBy(Features f) {
        return f.fafsaStatusSchool || f.fafsaStatusFederal;
    }
}
