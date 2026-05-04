package constraints;

import app.Features;

public class UseOfAidConstraint implements Constraint {

    @Override
    public boolean isSatisfiedBy(Features f) {
        return f.isUseOfAid;
    }
}
