package constraints;

import app.Features;

public class RepeatFAFSAConstraint implements Constraint {

    public boolean isSatisfiedBy(Features f) {

        // if user has questions about redoing the FAFSA
        return f.isRepeatFAFSA;
    }

}
