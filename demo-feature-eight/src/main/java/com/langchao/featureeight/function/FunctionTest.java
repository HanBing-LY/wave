package com.langchao.featureeight.function;


import com.langchao.featureeight.function.api.MathTowNumber;
import com.langchao.featureeight.function.api.MyMath;
import com.langchao.featureeight.function.api.MyPrint;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionTest {
    public static void main(String[] args) {

        MathTowNumber mathTowNumber = new MyMath();

        System.out.println(mathTowNumber.op(1, 2));

        MathTowNumber mathTowNumber2 = new MathTowNumber() {

            @Override
            public int op(int a, int b) {
                return a * b;
            }
        };

        System.out.println(mathTowNumber2.op(1, 2));

        MathTowNumber mathTowNumber3 = (a, b) -> a * b;

        // 两者之差 之和 之乘 取余
        // 接受一个参数 输出
        MyPrint<String> my = System.out::println;
        System.out.println(mathTowNumber3.op(1, 2));
        my.op("11");
        //一个数组 判断出当中的

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        FunctionTest.getString1("aaa", a -> System.out.println(a.toUpperCase()));


        FunctionTest.getString2("aaa", a -> System.out.println(a.toUpperCase()));

        Supplier<String> sup = "hello"::toUpperCase;

        System.out.println(sup.get());


        Function<String, Integer> fun = Integer::parseInt;
        System.out.println(fun.apply("1"));

    }


    public static void getNumbers(List<Integer> list, Predicate<Integer> predicate) {
		list.stream().filter(predicate).forEach(System.out::println);
    }


    public static void getString1(String string, CommonInterFace<String> commonInterFace) {
        commonInterFace.getResult(string);
    }


    public static void getString2(String string, Consumer<String> consumer) {
        consumer.accept(string);
    }

}
