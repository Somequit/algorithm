package interview;


import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Map;


/**
 * @author gusixue
 * @description
 * @date 2023/4/23
 */
public class StringInterpolationTest {

    @Test
    public void interpolation() {
        StringInterpolator stringInterpolation = new StringInterpolator();

        assert "My name is Bill and I am forever 21.".equals(stringInterpolation.interpolation(
                "My name is {{name }} and I am forever {{ age}}.",
                JSON.parseObject("{ \"name\": \"Bill\", \"age\": 21 }", Map.class)));

        assert "Say hello to Bill. He is 21.".equals(stringInterpolation.interpolation(
                "Say hello to {{ name }}. He is {{  age  }}.",
                JSON.parseObject("{ \"name\": \"Bill\", \"age\": 21, \"male\": true}", Map.class)));

        assert "My name is Bill. He is 30.".equals(stringInterpolation.interpolation(
                "My name is {{ name\t}}. He is 30.",
                JSON.parseObject("{ \"name\": \"Bill\" } ", Map.class)));

        assert "My name is Sam.".equals(stringInterpolation.interpolation("My name is Sam.",
                JSON.parseObject("{ \"name\": \"Bill\" } ", Map.class)));

        assert "".equals(stringInterpolation.interpolation("",
                JSON.parseObject("{ \"name\": \"Bill\" } ", Map.class)));

        assert null == stringInterpolation.interpolation(null,
                JSON.parseObject("{ \"name\": \"Bill\" } ", Map.class));

        assert "My name is Bill.".equals(stringInterpolation.interpolation("My name is Bill.", null));

        assert "Say hello to {{ name. He is {{  age  .".equals(stringInterpolation.interpolation(
                "Say hello to {{ name. He is {{  age  .",
                JSON.parseObject("{ \"name\": \"Bill\", \"age\": 21, \"male\": true}", Map.class)));

        assert "Say hello to Bill. He is 21.".equals(stringInterpolation.interpolation(
                "Say hello to {{ namenamenamenamenamenamename }}. He is {{  age  }}.",
                JSON.parseObject("{ \"namenamenamenamenamenamename\": \"Bill\", \"age\": 21, \"male\": true}", Map.class)));

        try {
            "My name is Sam.".equals(stringInterpolation.interpolation(
                    "My name is {{ {{ name }} oh }}.",
                    JSON.parseObject("{ \"name\": \"Bill\", \"Bill oh\": \"Sam\" } ", Map.class)));
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException) {
                System.out.println("OK1");
            } else {
                throw  e;
            }
        }

        try {
            "".equals(stringInterpolation.interpolation(
                    "Tommy is a good friend of {{ name }}. He lives in {{ city }}.",
                    JSON.parseObject("{ \"name\": \"Bill\" } ", Map.class)));
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException) {
                System.out.println("OK2");
            } else {
                throw  e;
            }
        }

    }


    @Test
    public void interpolationNested() {

        StringInterpolator stringInterpolation = new StringInterpolator();

        assert "My name is Bill and I am forever 21.".equals(stringInterpolation.interpolationNested(
                "My name is {{name }} and I am forever {{ age}}.",
                JSON.parseObject("{ \"name\": \"Bill\", \"age\": 21 }", Map.class)));

        assert "Say hello to Bill. He is 21.".equals(stringInterpolation.interpolationNested(
                "Say hello to {{ name }}. He is {{  age  }}.",
                JSON.parseObject("{ \"name\": \"Bill\", \"age\": 21, \"male\": true}", Map.class)));

        assert "My name is Bill. He is 30.".equals(stringInterpolation.interpolationNested(
                "My name is {{ name\t}}. He is 30.",
                JSON.parseObject("{ \"name\": \"Bill\" } ", Map.class)));

        assert "My name is Sam.".equals(stringInterpolation.interpolationNested("My name is Sam.",
                JSON.parseObject("{ \"name\": \"Bill\" } ", Map.class)));

        assert "".equals(stringInterpolation.interpolationNested("",
                JSON.parseObject("{ \"name\": \"Bill\" } ", Map.class)));

        assert null == stringInterpolation.interpolationNested(null,
                JSON.parseObject("{ \"name\": \"Bill\" } ", Map.class));

        assert "My name is Bill.".equals(stringInterpolation.interpolationNested("My name is Bill.", null));

        assert "Say hello to {{ name. He is {{  age  .".equals(stringInterpolation.interpolationNested(
                "Say hello to {{ name. He is {{  age  .",
                JSON.parseObject("{ \"name\": \"Bill\", \"age\": 21, \"male\": true}", Map.class)));

        assert "Say hello to Bill. He is 21.".equals(stringInterpolation.interpolationNested(
                "Say hello to {{ namenamenamenamenamenamename }}. He is {{  age  }}.",
                JSON.parseObject("{ \"namenamenamenamenamenamename\": \"Bill\", \"age\": 21, \"male\": true}", Map.class)));

        assert "My name is Sam.".equals(stringInterpolation.interpolationNested(
                "My name is {{ {{ name }} oh }}.",
                JSON.parseObject("{ \"name\": \"Bill\", \"Bill oh\": \"Sam\" } ", Map.class)));

        assert "My name is {{ Sam.".equals(stringInterpolation.interpolationNested(
                "My name is {{ {{ name oh }}.",
                JSON.parseObject("{ \"name\": \"Bill\", \"name oh\": \"Sam\" } ", Map.class)));


        assert "My name is Bill oh }}.".equals(stringInterpolation.interpolationNested(
                "My name is {{ name }} oh }}.",
                JSON.parseObject("{ \"name\": \"Bill\", \"name oh\": \"Sam\" } ", Map.class)));



        try {
            "".equals(stringInterpolation.interpolationNested(
                    "Tommy is a good friend of {{ name }}. He lives in {{ city }}.",
                    JSON.parseObject("{ \"name\": \"Bill\" } ", Map.class)));
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException) {
                System.out.println("OK2");
            } else {
                throw  e;
            }
        }
    }
}
