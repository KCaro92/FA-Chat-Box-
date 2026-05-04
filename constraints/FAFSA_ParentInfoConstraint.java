package constraints;

import app.Features;

public class FAFSA_ParentInfoConstraint implements Constraint {
    @Override
    public boolean isSatisfiedBy(Features f) {
        return f.fafsaParentInfo;
    }
}
