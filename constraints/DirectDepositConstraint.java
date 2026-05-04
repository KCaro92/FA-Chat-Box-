package constraints;

import app.Features;

public class DirectDepositConstraint implements Constraint {
    @Override
    public boolean isSatisfiedBy(Features f) {
        return f.mentionsDirectDeposit;
    }
}
