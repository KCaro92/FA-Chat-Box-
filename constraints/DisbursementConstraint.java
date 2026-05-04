package constraints;

import app.Features;

public class DisbursementConstraint implements Constraint {
    @Override
    public boolean isSatisfiedBy(Features f) {
        return f.isDisbursementQuestion;
    }
}
