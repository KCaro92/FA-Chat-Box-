package constraints;

import app.Features;

public class AppealConstraint implements Constraint {
    @Override
    public boolean isSatisfiedBy(Features f) {
        return f.isAppealQuestion;
    }
}
