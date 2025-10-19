package leetcode.contest.contest_472;

/**
 *
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    public String lexGreaterPermutation(String s, String target) {
        char[] sCount = new char[26];
        for (char c : s.toCharArray()) {
            sCount[c - 'a']++;
        }

        StringBuilder sbRes = new StringBuilder();
        for (char c : target.toCharArray()) {
            if (sCount[c - 'a'] > 0) {
                sbRes.append(c);
                sCount[c - 'a']--;

            } else {
                boolean found1 = false;
                for (int i = c - 'a' + 1; i < 26; i++) {
                    if (sCount[i] > 0) {
                        sbRes.append((char) (i + 'a'));
                        sCount[i]--;
                        found1 = true;
                        break;
                    }
                }

                if (found1) {
                    doMinCharToRES(sCount, sbRes);

                    return sbRes.toString();

                } else {
                    for (int i = sbRes.length() - 1; i >= 0; i--) {
                        sCount[sbRes.charAt(i) - 'a']++;

                        boolean found2 = false;
                        for (int j = sbRes.charAt(i) - 'a' + 1; j < 26; j++) {
                            if (sCount[j] > 0) {
                                sbRes = new StringBuilder(sbRes.substring(0, i));
                                sbRes.append((char) (j + 'a'));
                                sCount[j]--;
                                found2 = true;

                                break;
                            }
                        }

                        if (found2) {
                            doMinCharToRES(sCount, sbRes);
                            return sbRes.toString();
                        }

                    }

                    return "";
                }

            }
        }

        for (int i = sbRes.length() - 1; i >= 0; i--) {
            sCount[sbRes.charAt(i) - 'a']++;

            boolean found3 = false;
            for (int j = sbRes.charAt(i) - 'a' + 1; j < 26; j++) {
                if (sCount[j] > 0) {
                    sbRes = new StringBuilder(sbRes.substring(0, i));
                    sbRes.append((char) (j + 'a'));
                    sCount[j]--;
                    found3 = true;

                    break;
                }
            }

            if (found3) {
                doMinCharToRES(sCount, sbRes);
                return sbRes.toString();
            }

        }

        return "";
    }

    private void doMinCharToRES(char[] sCount, StringBuilder sbRes) {
        for (int i = 0; i < 26; i++) {
            while (sCount[i] > 0) {
                sbRes.append((char) (i + 'a'));
                sCount[i]--;
            }
        }
    }


}
