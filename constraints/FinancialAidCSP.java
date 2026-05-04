package constraints;

import java.util.*;
import app.Features;
import app.Category;

public class FinancialAidCSP {

        private Map<Category, List<Constraint>> constraintMap = new HashMap<>();

        // constraint map
        public FinancialAidCSP() {

                constraintMap.put(Category.VAGUE_QUESTION, List.of(new VagueConstraint()));

                constraintMap.put(Category.PROCESS_SUBMIT_FAFSA, List.of(new FAFSA_SubmissionConstraint()));

                constraintMap.put(Category.FAFSA_RENEWAL, List.of(new FAFSA_RenewalConstraint()));
                constraintMap.put(Category.FAFSA_STATUS, List.of(new FAFSA_StatusConstraint()));
                constraintMap.put(Category.FAFSA_REQUIREMENTS, List.of(new FAFSA_RequirementsConstraint()));
                constraintMap.put(Category.FAFSA_PARENT_INFO, List.of(new FAFSA_ParentInfoConstraint()));
                constraintMap.put(Category.FAFSA_DEPENDENCY, List.of(new FAFSA_DependencyConstraint()));
                constraintMap.put(Category.FAFSA_CORRECTION, List.of(new FAFSA_CorrectionConstraint()));
                constraintMap.put(Category.FAFSA_FSAID_ISSUES, List.of(new FAFSA_FSAIDConstraint()));

                constraintMap.put(
                                Category.STATUS_DISBURSEMENT,
                                List.of(new DisbursementConstraint()));

                constraintMap.put(
                                Category.PROCESS_DIRECT_DEPOSIT,
                                List.of(new DirectDepositConstraint()));

                constraintMap.put(Category.EDIT_DIRECT_DEPOSIT, List.of(new EditDirectDepositConstraint()));

                constraintMap.put(Category.LOAN_GENERAL, List.of(new LoanGeneralConstraint()));

                constraintMap.put(Category.USE_OF_AID, List.of(new UseOfAidConstraint()));

                constraintMap.put(
                                Category.PROCESS_ACCEPT_LOANS,
                                List.of(new LoanAcceptConstraint()));

                constraintMap.put(Category.LOAN_APPLY,
                                List.of(new LoanApplyConstraint()));

                constraintMap.put(Category.LOAN_CANCEL,
                                List.of(new LoanCancelConstraint()));

                constraintMap.put(
                                Category.AWARD_APPEAL,
                                List.of(new AppealConstraint()));

                constraintMap.put(
                                Category.PROCESS_DROP_WITHDRAW,
                                List.of(new DropWithdrawConstraint()));

                constraintMap.put(
                                Category.PROCESS_OPPORTUNITY_SCHOLARSHIP,
                                List.of(new OpportunityScholarshipConstraint()));

                constraintMap.put(
                                Category.PROCESS_REFUND_DEADLINE,
                                List.of(new RefundDeadlineConstraint()));

                constraintMap.put(
                                Category.STATUS_PICKUP_CHECK,
                                List.of(new PickupCheckConstraint()));

                constraintMap.put(
                                Category.STATUS_HOW_MUCH_WILL_I_GET,
                                List.of(new HowMuchAidConstraint()));

                constraintMap.put(Category.STATUS_DIRECT_DEPOSIT_OR_CHECK,
                                List.of(new DirectDepositOrCheckConstraint()));

                constraintMap.put(Category.ACCOUNT_SPECIFIC,
                                List.of(new AccountSpecificConstraint()));

                constraintMap.put(Category.ALL_OTHER, List.of());

        }

        public Map<Category, Integer> getScores(Features f) {
                Map<Category, Integer> scoreMap = new HashMap<>();

                for (Category c : constraintMap.keySet()) {
                        int score = 0;

                        for (Constraint constraint : constraintMap.get(c)) {
                                if (constraint.isSatisfiedBy(f)) {
                                        score++;
                                }
                        }

                        scoreMap.put(c, score);
                }

                return scoreMap;
        }

        public Category solve(Features f) {

                // FAFSA status - school received FAFSA
                if (f.fafsaStatusSchool) {
                        System.out.println("Selected category: FAFSA_STATUS_SCHOOL");
                        return Category.FAFSA_STATUS_SCHOOL;
                }

                // FAFSA status - federal processing
                if (f.fafsaStatusFederal) {
                        System.out.println("Selected category: FAFSA_STATUS_FEDERAL");
                        return Category.FAFSA_STATUS_FEDERAL;
                }

                // actual CSP
                Category bestCategory = Category.ALL_OTHER;
                int bestScore = 0;

                for (Category c : constraintMap.keySet()) {
                        int score = 0;

                        System.out.println("Checking Category: " + c);

                        for (Constraint constraint : constraintMap.get(c)) {
                                boolean satisfied = constraint.isSatisfiedBy(f);
                                System.out.println(" Constraint " + constraint.getClass().getSimpleName() + " = "
                                                + satisfied);

                                if (satisfied) {
                                        score++;
                                }
                        }

                        if (score > bestScore) {
                                bestScore = score;
                                bestCategory = c;
                        }
                }

                System.out.println("Selected category (raw CSP): " + bestCategory);
                return bestCategory;
        }
}