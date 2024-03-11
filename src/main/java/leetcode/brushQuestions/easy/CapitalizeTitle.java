package leetcode.brushQuestions.easy;

import utils.AlgorithmUtils;

/**
 * @author gusixue
 * @description 2129. 将标题首字母大写
 * @date 2024/3/11
 */
public class CapitalizeTitle {

    public static void main(String[] args) {
        CapitalizeTitle capitalizeTitle = new CapitalizeTitle();
        while (true) {
            String title = AlgorithmUtils.systemInString();

            String res = capitalizeTitle.capitalizeTitle(title);
            System.out.println(res);

        }
    }

    public String capitalizeTitle(String title) {
        StringBuilder res = new StringBuilder();

        StringBuilder titleTemp = new StringBuilder();
        title = title.toLowerCase();
        for (int i = 0; i < title.length(); i++) {
            if (title.charAt(i) == ' ') {
                res.append(toTitleCase(titleTemp)).append(' ');

                titleTemp = new StringBuilder();

            } else {
                titleTemp.append(title.charAt(i));
            }
        }

        res.append(toTitleCase(titleTemp));

        return res.toString();
    }

    private StringBuilder toTitleCase(StringBuilder titleTemp) {
        if (titleTemp.length() > 2) {
            titleTemp.setCharAt(0, (char) (titleTemp.charAt(0) + ('A' - 'a')));
        }
        return titleTemp;
    }
}
