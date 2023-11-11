package leetcode.hot_100.hard;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author gusixue
 * @description
 * @date 2023/6/4
 */
public class RemoveInvalidParenthesesTest {
    @Test
    public void test() {
        RemoveInvalidParentheses removeInvalidParentheses = new RemoveInvalidParentheses();

        assert equalsResult(removeInvalidParentheses.solution("abc"), new String[]{"abc"});

        assert equalsResult(removeInvalidParentheses.solution("abc(def"), new String[]{"abcdef"});

        assert equalsResult(removeInvalidParentheses.solution("abc)def"), new String[]{"abcdef"});

        assert equalsResult(removeInvalidParentheses.solution("ab(c)de"), new String[]{"ab(c)de"});

        assert equalsResult(removeInvalidParentheses.solution("ab)c(def"), new String[]{"abcdef"});

        assert equalsResult(removeInvalidParentheses.solution("()abcdef"), new String[]{"()abcdef"});

        assert equalsResult(removeInvalidParentheses.solution("abcdef()"), new String[]{"abcdef()"});

        assert equalsResult(removeInvalidParentheses.solution("abc()def"), new String[]{"abc()def"});

        assert equalsResult(removeInvalidParentheses.solution("()())()"), new String[]{"(())()","()()()"});

        assert equalsResult(removeInvalidParentheses.solution("(a)())()"), new String[]{"(a())()","(a)()()"});

        assert equalsResult(removeInvalidParentheses.solution("((i)"), new String[]{"(i)"});
    }

    private boolean equalsResult(List<String> resultList, String[] resultStr) {
        if (resultList.size() != resultStr.length) {
            return false;
        }
        Set<String> resultSet = new HashSet<>((resultList.size() / 3) << 2);
        for (int i = 0; i < resultList.size(); i++) {
            resultSet.add(resultList.get(i));
        }

        for (int i = 0; i < resultStr.length; i++) {
            if (!resultSet.contains(resultStr[i])) {
                return false;
            }
        }
        return true;
    }

}
