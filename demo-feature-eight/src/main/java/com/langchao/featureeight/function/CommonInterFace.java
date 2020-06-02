package com.langchao.featureeight.function;

/**
 * jdk1.8 提供了 四大核心函数式接口
 * Predicate 接受一个输入参数，返回一个布尔值结果。
 * Function<Integer,String> 接受一个输入参数，返回一个结果。
 * Supplier 无参数，返回一个结果。
 * Consumer 接受一个参数 无结果返回
 * @author admin
 * @param <T>
 */
@FunctionalInterface
public interface CommonInterFace<T> {

    void getResult(T t);
}
