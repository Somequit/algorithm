package leetcode.contest.contest_355;

import org.springframework.util.CollectionUtils;
import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
            List<String> words = CollectionUtils.arrayToList(AlgorithmUtils.systemInArrayString());
            char separator = AlgorithmUtils.systemInChar();

            List<String> res = contest.solution(words, separator);
            System.out.println(res);
        }

    }

    public List<String> solution(List<String> words, char separator) {
        List<String> res = new ArrayList<>();

        for (int i = 0; i < words.size(); i++) {

            StringBuilder temp = new StringBuilder();
            for (int j = 0; j < words.get(i).length(); j++) {
                if (words.get(i).charAt(j) == separator) {
                    if (temp.length() > 0) {
                        res.add(temp.toString());
                    }
                    temp = new StringBuilder();
                } else {
                    temp.append(words.get(i).charAt(j));
                }
            }
            if (temp.length() > 0) {
                res.add(temp.toString());
            }
        }

        return res;
    }


}
