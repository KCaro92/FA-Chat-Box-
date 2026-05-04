package app;

public enum Category {

    // vague questions
    VAGUE_QUESTION("your question"),

    STATUS_DISBURSEMENT("when your aid will disburse"),
    STATUS_DIRECT_DEPOSIT_OR_CHECK("direct deposit or paper checks"),
    STATUS_PICKUP_CHECK("picking up your check"),
    STATUS_HOW_MUCH_WILL_I_GET("how much aid you will receive"),
    USE_OF_AID("Use of Financial Aid"),

    PROCESS_SUBMIT_FAFSA("submitting your FAFSA"),
    FAFSA_RENEWAL("renewing your FAFSA"),
    FAFSA_STATUS("your FAFSA status"),
    FAFSA_REQUIREMENTS("FAFSA requirements"),
    FAFSA_PARENT_INFO("parent information on the FAFSA"),
    FAFSA_DEPENDENCY("dependency status"),
    FAFSA_CORRECTION("correcting your FAFSA"),
    FAFSA_FSAID_ISSUES("FSA ID issues"),
    FAFSA_STATUS_SCHOOL("FAFSA status at the school"),
    FAFSA_STATUS_FEDERAL("FAFSA status with the federal processor"),

    LOAN_GENERAL("loans"),

    PROCESS_ACCEPT_LOANS("accepting your loans"),
    PROCESS_GET_LOAN("getting a loan"),
    VERIFICATION_LOAN_AMOUNT("loan amounts"),
    VERIFICATION_LOAN_REPAYMENT("loan repayment"),
    LOAN_ACCEPT("accepting a loan"),
    LOAN_APPLY("applying for a loan"),
    LOAN_CANCEL("canceling a loan"),
    FOLLOW_UP_LOAN("loan questions"),

    PROCESS_DIRECT_DEPOSIT("setting up direct deposit"),
    EDIT_DIRECT_DEPOSIT("Editing Direct Deposit"),

    AWARD_APPEAL("appealing your award"),

    VERIFICATION_DROP_WITHDRAW("drop/withdraw questions"),
    PROCESS_DROP_WITHDRAW("dropping or withdrawing"),
    PROCESS_REFUND_DEADLINE("refund deadlines"),

    PROCESS_OPPORTUNITY_SCHOLARSHIP("Opportunity Scholarship"),

    ACCOUNT_SPECIFIC("your student account"),

    FOLLOW_UP_SMART("follow-up"),
    FOLLOW_UP("follow-up"),
    ALL_OTHER("general questions");

    private final String friendlyName;

    Category(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
