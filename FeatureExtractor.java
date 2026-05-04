package app;

public class FeatureExtractor {

        // exact keyword match
        public static boolean containsAny(String text, String... words) {
                for (String w : words) {
                        if (text.contains(w)) {
                                return true;
                        }
                }
                return false;
        }

        // checks for typos
        public static int typo(String a, String b) {
                int[][] dp = new int[a.length() + 1][b.length() + 1];

                for (int i = 0; i <= a.length(); i++)
                        dp[i][0] = i;
                for (int j = 0; j <= b.length(); j++)
                        dp[0][j] = j;

                for (int i = 1; i <= a.length(); i++) {
                        for (int j = 1; j <= b.length(); j++) {
                                int cost = (a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1;
                                dp[i][j] = Math.min(
                                                Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                                                dp[i - 1][j - 1] + cost);
                        }
                }
                return dp[a.length()][b.length()];
        }

        // fuzzy match for single words
        public static boolean fuzzyWord(String text, String word) {
                for (String token : text.split(" ")) {
                        if (typo(token, word) <= 2) {
                                return true;
                        }
                }
                return false;
        }

        // fuzzy match for multi-word phrases
        public static boolean fuzzyPhrase(String text, String phrase) {
                if (text.contains(phrase))
                        return true;

                String[] tWords = text.split(" ");
                String[] pWords = phrase.split(" ");

                if (pWords.length == 1)
                        return fuzzyWord(text, phrase);

                for (int i = 0; i <= tWords.length - pWords.length; i++) {
                        int totalDistance = 0;

                        for (int j = 0; j < pWords.length; j++) {
                                totalDistance += typo(tWords[i + j], pWords[j]);
                        }

                        if (totalDistance <= 3)
                                return true;
                }

                return false;
        }

        // fuzzy or exact match for any word
        public static boolean containsAnyFuzzy(String text, String... words) {
                for (String w : words) {
                        if (text.contains(w) || fuzzyWord(text, w) || fuzzyPhrase(text, w)) {
                                return true;
                        }
                }
                return false;
        }

        // feature extractor
        public static Features extract(String question) {
                Features f = new Features();
                f.originalText = question;

                String q = question.toLowerCase();
                q = q.replaceAll("[^a-z0-9 ]", " ");
                q = q.replaceAll("\\s+", " ").trim();

                // vauge question detection
                f.isVague = containsAny(q,
                                "help",
                                "i need help",
                                "idk",
                                "i dont know",
                                "what do i do",
                                "im confused",
                                "confused",
                                "can you help me",
                                "i need assistance",
                                "i have a question",
                                "question",
                                "lost",
                                "unsure",
                                "what now",
                                "what next");

                // documentation questions
                f.mentionsDocuments = containsAny(q, "upload", "document", "documents", "tax", "turn in", "submit",
                                "paperwork")
                                || fuzzyWord(q, "document");

                // deadlines
                f.containsDeadline = containsAnyFuzzy(q, "deadline", "due", "cutoff", "last day");

                // complex verifications questions
                f.requiresReview = containsAny(q, "verification", "override", "professional judgment", "sap")
                                || fuzzyWord(q, "verification");

                // cehck status also complex
                f.studentStatusCheck = containsAnyFuzzy(q, "status", "processed", "processing", "check", "update");

                // check vs direct deposit
                f.refundMethod = ((q.contains("check") || q.contains("paper check"))
                                && (q.contains("direct deposit") || fuzzyPhrase(q, "direct deposit")))
                                || containsAnyFuzzy(q,
                                                "check or direct deposit",
                                                "check or deposit",
                                                "does it go to my bank",
                                                "do i get a check",
                                                "how do i get my money",
                                                "will it go to my account",
                                                "will it be a check or",
                                                "does it go to my account",
                                                "will i get a check or");

                f.isUseOfAid = containsAnyFuzzy(q,
                                "use my financial aid",
                                "use financial aid",
                                "use my aid",
                                "what can i use my aid for",
                                "what can i use aid for",
                                "what does aid cover",
                                "what does financial aid cover",
                                "personal finances",
                                "personal expenses",
                                "can i use aid for personal",
                                "can i use aid for bills",
                                "can i use aid for rent",
                                "can i use aid for living expenses",
                                "is aid only for school",
                                "school related only",
                                "aid for personal",
                                "aid for school",
                                "aid usage",
                                "aid allowed for",
                                "aid restrictions");

                // account questions also complex
                f.isAccountSpecific = containsAny(q,
                                "my account has a problem",
                                "my account is wrong",
                                "my account issue",
                                "my account problem",
                                "i got an email",
                                "i received an email",
                                "email from financial aid",
                                "why did i not get",
                                "why didn't i get",
                                "why was my aid",
                                "why is my aid",
                                "why is my refund",
                                "why is my money",
                                "why is my scholarship",
                                "why was my scholarship",
                                "why was my refund",
                                "why was my aid reduced",
                                "do i have verification",
                                "am i selected for verification",
                                "what documents am i missing",
                                "what am i missing");

                // appeal questions
                f.isAppealQuestion = containsAnyFuzzy(q,
                                "appeal", "how do i appeal", "do an appeal", "appeal packet");

                // direct deposit questions
                f.mentionsDirectDeposit = containsAnyFuzzy(q,
                                "set up direct deposit",
                                "setup direct deposit",
                                "how do i set up direct deposit",
                                "how do i set up my direct deposit",
                                "direct deposit signup",
                                "sign up for direct deposit",
                                "enter my bank info",
                                "routing number",
                                "account number",
                                "bank information",
                                "bank info",
                                "set up my bank account",
                                "set up my bank info",
                                "direct deposit form");

                f.isEditDirectDeposit = containsAnyFuzzy(q,
                                "change my direct deposit",
                                "edit my direct deposit",
                                "update my direct deposit",
                                "make changes to my direct deposit",
                                "change direct deposit",
                                "update direct deposit",
                                "edit direct deposit",
                                "switch bank",
                                "switched banks",
                                "change bank account",
                                "update bank account",
                                "new bank account",
                                "change routing number",
                                "change account number",
                                "edit bank info",
                                "update bank info",
                                "change direct deposit info",
                                "change direct deposit information");

                // loans
                f.mentionsLoans = containsAny(q,
                                "loan",
                                "loans",
                                "student loan",
                                "subsidized",
                                "unsubsidized");

                // fafsa
                f.mentionsFAFSA = containsAnyFuzzy(q,
                                "fafsa", "fasa", "faffa", "student aid", "fsa id");

                // renew fafsa
                f.isRepeatFAFSA = containsAnyFuzzy(q, "fafsa again", "redo fafsa", "renew fafsa")
                                || (q.contains("renew") && q.contains("fafsa"))
                                || (q.contains("again") && q.contains("fafsa"));

                // fafsa submit
                f.fafsaSubmission = containsAnyFuzzy(q,
                                "submit fafsa", "how do i submit fafsa", "where do i submit fafsa",
                                "fill out fafsa", "complete fafsa", "turn in fafsa",
                                "file fafsa", "start fafsa");

                // fafsa renewal
                f.fafsaRenewal = containsAnyFuzzy(q,
                                "renew fafsa", "fafsa renewal", "redo fafsa", "repeat fafsa",
                                "fafsa every year", "each year fafsa", "yearly fafsa");

                // fafsa school received questiosn
                f.fafsaStatusSchool = containsAnyFuzzy(q,
                                "did the school receive my fafsa",
                                "did nmsu receive my fafsa",
                                "fafsa accepted on mynmsu",
                                "fafsa posted on mynmsu",
                                "fafsa on file at the school");

                // fafsa federal received fafsa
                f.fafsaStatusFederal = containsAnyFuzzy(q,
                                "is my fafsa processed",
                                "has my fafsa been processed",
                                "fafsa in progress",
                                "check fafsa on studentaid");

                // genearl fafsa status
                f.fafsaStatus = containsAnyFuzzy(q,
                                "fafsa status",
                                "was my fafsa received",
                                "did nmsu receive my fafsa",
                                "did the school receive my fafsa",
                                "did the school get my fafsa",
                                "did they get my fafsa",
                                "did they process my fafsa",
                                "was my fafsa processed");

                // fafsa requirements
                f.fafsaRequirements = containsAnyFuzzy(q,
                                "fafsa requirements", "verification", "documents", "missing documents");

                // if needing parent info questions
                f.fafsaParentInfo = containsAnyFuzzy(q,
                                "parent info", "parent information", "parents sign", "parent signature");

                // independent vs dependent status
                f.fafsaDependency = containsAnyFuzzy(q,
                                "dependent", "independent", "dependency", "do i need my parents");

                // correct fafsa mistakes
                f.fafsaCorrection = containsAnyFuzzy(q,
                                "fix my fafsa",
                                "correct my fafsa",
                                "fafsa correction",
                                "i made a mistake on my fafsa",
                                "i messed up my fafsa",
                                "i need to change my fafsa",
                                "edit my fafsa",
                                "change my fafsa");

                // if forget fafsaID
                f.fafsaFSAID = containsAnyFuzzy(q,
                                "fsa id", "cant log in", "forgot fsa id", "parent fsa id");

                // drop vs withdraw
                f.mentionsDropWithdraw = containsAnyFuzzy(q,
                                "drop a class",
                                "drop my class",
                                "withdraw from a class",
                                "withdraw from the class",
                                "withdrawal deadline",
                                "drop deadline",
                                "withdrawing",
                                "dropped a class",
                                "withdrew from a class",
                                "w on my transcript",
                                "w grade");

                // opportunity questions
                f.mentionsOpportunityScholarship = containsAnyFuzzy(q,
                                "opportunity scholarship", "opportunity", "lottery scholarship");

                // refund deadline
                f.mentionsRefundDeadline = containsAnyFuzzy(q,
                                "100% refund", "refund deadline", "drop deadline");

                // pickup check questions
                f.mentionsPickupCheck = containsAnyFuzzy(q,
                                "pick up check", "where do i pick up", "pickup my check");

                // how much will i get? questions
                f.mentionsHowMuch = containsAnyFuzzy(q,
                                "how much will i get", "how much aid", "how much money");

                // disbursement
                boolean disbursementKeywords = containsAnyFuzzy(q,
                                "financial aid", "refund", "my money", "get my money",
                                "disburse", "disbursement", "release funds", "refund check");

                boolean waitingKeywords = containsAnyFuzzy(q,
                                "when", "missing", "late", "not received", "haven't", "have not")
                                || (q.contains("where") && containsAnyFuzzy(q, "refund", "money"));

                // short disbursement questions
                boolean shortImplicitDisbursement = containsAnyFuzzy(q,
                                "my refund",
                                "my money");

                f.isDisbursementQuestion = (disbursementKeywords && waitingKeywords)
                                || shortImplicitDisbursement;

                return f;

        }
}
