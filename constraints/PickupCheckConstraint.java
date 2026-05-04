package constraints;

import app.Features;

public class PickupCheckConstraint implements Constraint {
    @Override
    public boolean isSatisfiedBy(Features f) {
        return f.mentionsPickupCheck;
    }
}
