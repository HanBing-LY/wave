package com.langchao.featureeight.optional;

import java.util.Optional;

/**
 * @author liyuan
 * @create 2020-06-02-20:48
 */
public class OptionalTest {

    public static void main(String[] args) {

        String keyPresent = "abcd";

        String keyNull = null;


        Optional<String> keyPresentOptional = Optional.ofNullable(keyPresent);
        Optional<String> keyNullOptional = Optional.ofNullable(keyNull);

        /**
         * isPresent()
         * 如果值存在则方法会返回true，否则返回 false。
         */
        System.out.println(keyPresentOptional.isPresent());
        System.out.println(keyNullOptional.isPresent());

        /**
         * 判断前面是否存在,存在即返回,否则返回other
         * 返回6666
         */
        Optional.ofNullable(keyPresent).orElse("66666");
        Optional.ofNullable(keyNull).orElse("66666");


        /**
         * 判断前面是否存在,存在即返回,否则返回orElseGet方法的返回值
         */
        Optional.ofNullable(keyNull).orElseGet(() ->new String());

        Optional.ofNullable(keyNull).orElseThrow(RuntimeException::new);


        /**
         * 如果满足部位空,则调用filter继续判断,是否满足,满足则返回,不满足返回空
         * 相当于过滤
         */
        Optional.ofNullable(keyPresent).filter("1"::equals);
    }
}
