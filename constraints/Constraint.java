package constraints;
//checks for constraints in CSP 

import app.Features;

public interface Constraint {
    boolean isSatisfiedBy(Features f);
}
