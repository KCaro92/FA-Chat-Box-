package constraints;

import app.Features;

public class EditDirectDepositConstraint implements Constraint {

    @Override
    public boolean isSatisfiedBy(Features f) {
        return f.isEditDirectDeposit;
    }
}
