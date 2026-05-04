package constraints;

import app.Features;

public class VagueConstraint implements Constraint {
    @Override
    public boolean isSatisfiedBy(Features f) {
        return f.isVague;
    }
}
