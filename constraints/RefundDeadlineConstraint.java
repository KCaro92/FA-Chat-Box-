package constraints;

import app.Features;

public class RefundDeadlineConstraint implements Constraint {
    @Override
    public boolean isSatisfiedBy(Features f) {
        return f.mentionsRefundDeadline;
    }
}
