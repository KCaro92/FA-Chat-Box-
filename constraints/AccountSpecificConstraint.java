package constraints;

import app.Features;

public class AccountSpecificConstraint implements Constraint {
    @Override
    public boolean isSatisfiedBy(Features f) {
        return f.isAccountSpecific;
    }
}
