package app;

public class AnswerBank {

    public static String getAnswer(Category c) {

        switch (c) {

            // STATUS / DISBURSEMENT
            case STATUS_DISBURSEMENT:
                return "Aid disburses the first week of classes. Refunds are issued 2 to 4 business days after disbursement if direct deposit is set up.";

            case USE_OF_AID:
                return "Financial aid is intended to help cover educational expenses such as tuition, fees, books, supplies, and housing. Some aid can also help with basic living costs like food, transportation, and rent while you are enrolled";

            case STATUS_DIRECT_DEPOSIT_OR_CHECK:
                return "If you have direct deposit set up, your refund goes to your bank account. Otherwise, you will receive a check.";

            case EDIT_DIRECT_DEPOSIT:
                return "To update or change your direct deposit information, Please follow these instructions:\n"
                        + "1) In your NMSU dashboard go to the My Account tab and click on the General Self-Service card\n"
                        + "2) Click on the Direct Deposit option, it will lead you to your current bank account\n"
                        + "3) In there you can update the account information, delete the account, or put portions of your amount to different accounts";

            case STATUS_PICKUP_CHECK:
                return "if you are an NMSU student you can pick it up in the UAR office located at 1780 East Univeristy ave in the Educational Service Room 701\n\n"
                        + "If you are a DACC student, you can pick it up at the East Mesa Building at 2800 N. Sonoma Ranch Blvd inside the Student Resources Building at the cashiers office\n\n"
                        + "Please keep in mind that you are required to bring a valid picture ID such as: your licesnse, state issued ID or your NMSU ID to pick up your check.";

            case STATUS_HOW_MUCH_WILL_I_GET:
                return "If you want to see what you are potentially receiving in financial aid you can: \n"
                        + "1) Log into your nmsu account\n" + "2) Go into the Financial Aid card\n"
                        + "3) Click on the Offer tab\n\n"
                        + "In the offer tab there is a box that says Cost of Attendance, please keep in mind that this is NOT what you owe but what your budget allows\n\n"
                        + "If you scroll down to the Grants and Scholarships to Pay for College box that would be the estimate of what you are potentially receiving\n"
                        + "Please also keep in mind that the aid you see if for full time enrollment, your elligibility is dependent on your enrollment.";

            // FAFSA
            case PROCESS_SUBMIT_FAFSA:
                return "The fafsa application can be submitted through the studentaid.gov website, you will need to create an FSA ID in order to complete the applicaiton.\n"
                        + "If you are dependent student your parents would also have to make an FSA ID to complete their portion of your application. \n"
                        + "To submit your application to NMSU the federal school code needs to be added, the NMSU federal school code is 002657. \n"
                        + "To submit your application to DACC the federal school code is E00876.";

            case FAFSA_RENEWAL:
                return "FAFSA must be renewed every aid year, the application typically opens at the end of the academic year.";

            case FAFSA_REQUIREMENTS:
                return "You may receive FAFSA requirements if additional information is needed to verify your application. Common requirements include tax documents, household information, identity verification, or confirmation of citizenship status. You can view your specific requirements in your myNMSU account under the Student Requirements section.";

            case FAFSA_PARENT_INFO:
                return "If you are a dependent student, you need to provide your parent information.";

            case FAFSA_DEPENDENCY:
                return "Dependency status for FAFSA is based on federal guidelines. You are considered independent if you meet certain criteria such as being 24 or older, married, a veteran, supporting dependents, or experiencing special circumstances. Otherwise, you are considered dependent and must include parent information. You can review the full dependency criteria at studentaid.gov.";
            case FAFSA_CORRECTION:
                return "If you made a mistake on your FAFSA, you can correct it by logging in to your account at studentaid.gov and selecting 'Make FAFSA Corrections.' You can update most information directly on the site. If you are unable to make the correction yourself, the Financial Aid Office can guide you on what steps to take.";

            case FAFSA_FSAID_ISSUES:
                return "If you forgot your FSA ID username or password, you can recover it at studentaid.gov by selecting 'Forgot Username' or 'Forgot Password.' You may need access to your email or phone number to verify your identity. If you no longer have access to those, you can answer your security questions or contact Federal Student Aid for help.";

            case FAFSA_STATUS_SCHOOL:
                return "You can check if NMSU or DACC received your FAFSA by logging into your mynmsu account. Under the 'My Financial Aid' card, select your aid year and review your Student Requirements. If you see a box labeled 'Free Application for Federal Student Aid' and it shows as Accepted, that means the financial aid office received your FAFSA. Student requirements vary by student, so if you see any outstanding items you are unsure of, it is recommended to speak with a financial aid advisor.";

            case FAFSA_STATUS_FEDERAL:
                return "To check the status of your FAFSA application, sign in to your account at studentaid.gov. On your dashboard, you will see your current FAFSA applications and their status. In Progress means the FAFSA has not been submitted yet. In Review means the FAFSA was submitted but has not been processed or sent to schools yet. Processed means the FAFSA has been processed and the schools listed on your application should have received it. You can also click on your FAFSA form to see the schools you added.";

            // LOANS
            case PROCESS_ACCEPT_LOANS:
                return "You can accept loans online on your NMSU account, here are the instructions on how to get there: \n"
                        + "1) Log into your nmsu account \n" + "2) Go to the Financial Aid card \n"
                        + "3) go to the Offer tab and click on accept\n"
                        + "If you declined the loan initially and change your mind you can request a loan by contacting the financial aid office";

            case PROCESS_GET_LOAN:
                return "If your budget allows, you can accept a loan in myNMSU under the Loans section.";

            case LOAN_APPLY:
                return "If you declined a loan and wish to apply for one, you can follow these instructions:\n"
                        + "1) Go to fa.nmsu.edu\n" + "2) Go to the Resources tab and click on Forms\n"
                        + "3) Click on the Direct Loan Acceptance link\n"
                        + "4) Fill out the top part of the form, scan it and send it to financialaid@nmsu.edu and a financial aid advisor will send you a loan offer";

            case LOAN_CANCEL:
                return "If you received a loan and wish to cancel it and return the funds, you can follow these instructions:\n"
                        + "1) Go to fa.nmsu.edu\n" + "2) Go to the Resources tab and click on Forms\n"
                        + "3) Click on the Request to Return Loan Funds link\n"
                        + "4) Fill out the top part of the form, scan it and send it to financialaid@nmsu.edu, a Financial aid adsivor will update you once the form has been received.";

            case VERIFICATION_LOAN_AMOUNT:
                return "Loan limits depend on grade level and dependency status. Speak with a Financial Aid advisor for exact amounts.";

            case VERIFICATION_LOAN_REPAYMENT:
                return "Loans must be repaid. Repayment begins 6 months after graduation, stopping attendance, or dropping below part time.";

            // DIRECT DEPOSIT
            case PROCESS_DIRECT_DEPOSIT:
                return "To set up direct deposit please follow these instructions: \n"
                        + "1) Log in to your mynmsu account \n" + "2) Click on the View All Cards tab\n"
                        + "3) Scroll down to the Student Self Service card and click on the Open Student Dashboard box\n"
                        + "4) Scroll down to the Student Account and Payment Information section and click on the link that states Direct Deposit Signup, there you will have the ability to enter your bank information such as your routing number and account number.";

            // APPEALS
            case AWARD_APPEAL:
                return "A complete appeal includes the appeal form, a personal statement, and supporting documentation. Timeframe appeals also require an academic memo.";

            // DROP / WITHDRAW
            case VERIFICATION_DROP_WITHDRAW:
                return "Dropping or withdrawing classes may affect your aid and could result in a bill back depending on timing and participation.";

            case PROCESS_DROP_WITHDRAW:
                return "Dropping removes the class from your record; withdrawing keeps it on your transcript with a W.";

            case PROCESS_REFUND_DEADLINE:
                return "The 100% refund deadline depends on the academic calendar. Check the NMSU schedule for exact dates.";

            // OPPORTUNITY SCHOLARSHIP
            case PROCESS_OPPORTUNITY_SCHOLARSHIP:
                return "Opportunity Scholarship eligibility depends on residency, enrollment, and academic progress. Check the NMSU scholarship page for details.";

            case ACCOUNT_SPECIFIC:
                return "This question requires access to your student account. Please contact the Financial Aid Office so they can review your information.";

            // FOllow up
            case FOLLOW_UP:
                return "I want to help you, can you tell me a little more about what you need?\n"
                        + "Are you asking about FAFSA? loans? disbursement or something else?";

            // follow up loans
            case LOAN_GENERAL:
                return "Certainly! Do you need help accepting a loan? applying for a loan? cancelling a loan, or something else? ";
            // FALLBACK
            case ALL_OTHER:
            default:
                return "I'm not fully sure how to answer that yet. Please contact Financial Aid for more details.";
        }
    }
}
