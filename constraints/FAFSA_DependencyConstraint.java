package constraints;

import app.Features;

public class FAFSA_DependencyConstraint implements Constraint {
    @Override
    public boolean isSatisfiedBy(Features f) {
        return f.fafsaDependency;
    }
}
