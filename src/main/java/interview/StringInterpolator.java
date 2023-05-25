package interview;

import org.springframework.util.StringUtils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gusixue
 * @description String Interpolation
 * @date 2023/4/23
 */
public class StringInterpolator {

    /**
     * Ignore nested strings "{{" and "}}", example："My name is {{ {{ name }} and }} I am forever {{ age }}."
     * I'm not sure if you need it, but if you do, my idea is:
     * 1. loop through and search for {{ or }}
     * 2. When {{ is found, push its index onto the stack
     * 3. When }} is found, pop the corresponding {{ index from the stack:
     * If the stack is empty continue , else string interpolation
     *
     * String interpolation, Ignore the key-value pair object keys that are not present in the variable in the string
     * Throw exception/error if one of the variables in the string is missing from the key-value pair object
     *
     * @param content A string that the interpolation will be applied to. This parameter is required.
     * @param values  A key-value pair object for Map
     * @return Return the string after interpolation
     * @throws IllegalArgumentException Throw exception if one of the variables in the string is missing from
     *          the key-value pair object
     */
    public static String interpolation(String content, Map<String, Object> values) {
        // empty content or values
        if (StringUtils.isEmpty(content)) {
            return content;
        }
        if (values == null) {
            values = new HashMap<>();
        }

        // increase efficiency
        StringBuffer contentBuffer = new StringBuffer(content);

        // Find the first "{{" and the first "}}" after it
        int indexLeft = contentBuffer.indexOf("{{", 0);
        int indexRight = contentBuffer.indexOf("}}", indexLeft);

        // double curly braces interpolation: "{{" and "}}"
        while (indexLeft != -1 && indexRight != -1) {
            // String Interpolation
            interpolationBracket(contentBuffer, indexLeft, indexRight, values);

            indexLeft = contentBuffer.indexOf("{{", 0);
            indexRight = contentBuffer.indexOf("}}", indexLeft);
        }

        return contentBuffer.toString();
    }


    /**
     * Nested strings "{{" and "}}", example："My name is {{ {{ name }} and }} I am forever {{ age }}."
     * 1. loop through and search for {{ or }}
     * 2. When {{ is found, push its index onto the stack
     * 3. When }} is found, pop the corresponding {{ index from the stack:
     * If the stack is empty continue , else string interpolation
     *
     * String interpolation, Ignore the key-value pair object keys that are not present in the variable in the string
     * Throw exception/error if one of the variables in the string is missing from the key-value pair object
     *
     * @param content A string that the interpolation will be applied to. This parameter is required.
     * @param values  A key-value pair object for Map
     * @return Return the string after interpolation
     * @throws IllegalArgumentException Throw exception if one of the variables in the string is missing from
     *          the key-value pair object
     */
    public static String interpolationNested(String content, Map<String, Object> values) {
        // empty content or values
        if (StringUtils.isEmpty(content)) {
            return content;
        }
        if (values == null) {
            values = new HashMap<>();
        }

        // increase efficiency
        StringBuffer contentBuffer = new StringBuffer(content);

        // Find the first "{{" and the first "}}" after it
        int indexLeft = contentBuffer.indexOf("{{", 0);
        int indexRight = contentBuffer.indexOf("}}", indexLeft);

        // double curly braces interpolation: "{{" and "}}" use stack
        Deque<Integer> leftIndexStack = new ArrayDeque<>(content.length() >> 1);
        while (indexLeft != -1 || indexRight != -1) {

            // If the "}}" is in front, pop the top of the stack and look for the next "}}"
            if (indexLeft == -1 || (indexRight != -1 && indexRight < indexLeft)) {
                // If the stack is empty, take the "}}" after the "{{"
                if (leftIndexStack.isEmpty()) {
                    indexRight = contentBuffer.indexOf("}}", indexRight + 2);

                // If the stack is not empty, pop it, replace the result, and update both "}}" and "{{"
                } else {
                    int indexLeftTemp = leftIndexStack.pollFirst();
                    interpolationBracket(contentBuffer, indexLeftTemp, indexRight, values);
                    indexLeft = contentBuffer.indexOf("{{", indexLeftTemp);
                    indexRight = contentBuffer.indexOf("}}", indexLeftTemp);
                }

            // If the "{{" is in front, push it onto the stack and look for the next "{{"
            } else {
                leftIndexStack.offerFirst(indexLeft);
                indexLeft = contentBuffer.indexOf("{{", indexLeft + 2);
            }
        }

        return contentBuffer.toString();
    }

    /**
     * String Interpolation
     * @param contentBuffer A string that the interpolation will be applied to. This parameter is required.
     * @param indexLeft interpolation left index
     * @param indexRight interpolation right index
     * @param values A key-value pair object for Map
     */
    private static void interpolationBracket(StringBuffer contentBuffer, int indexLeft, int indexRight,
                                             Map<String, Object> values) {

        // delete Spaces/tabs
        String contentKey = contentBuffer.substring(indexLeft + 2, indexRight).trim();
        Object contentValue = values.get(contentKey);

        if (contentValue == null) {
            throw new IllegalArgumentException("Variable '" + contentKey + "' not found in values map");
        } else {
            contentBuffer.replace(indexLeft, indexRight + 2, contentValue.toString());
        }
    }
}
