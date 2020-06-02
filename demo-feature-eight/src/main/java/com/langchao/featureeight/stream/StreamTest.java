package com.langchao.featureeight.stream;

import com.langchao.featureeight.pojo.Product;
import com.langchao.featureeight.pojo.Student;
import com.langchao.featureeight.pojo.User;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * stream 相关操作
 * @author Administrator
 */
public class StreamTest {

    public static void main(String[] args) {

        int[] nums = {1, 2, 3};
        Integer count = IntStream.of(nums).sum();
        System.out.println(count);
        int sum = IntStream.of(nums).map(i -> {
            System.out.println("1111");
            return i + 2;
        }).sum();
        List<String> list1 = new ArrayList<>();


        list1.add("a2312");
        list1.add("b");
        list1.add("cccc");

        list1.add("cccc");
        List<Integer> list2 = new ArrayList<>();


        list2.add(1);
        list2.add(2);
        list2.add(3);

        list1.forEach(System.out::println);
        Arrays.stream(new int[]{1, 2, 3}).forEach(System.out::println);

        /**
         * limit 对一个Stream进行截断操作，获取其前N个元素，如果原Stream中包含的元素个数小于N，那就获取其所有的元素；
         */
        new Random().ints().limit(10).forEach(System.out::println);

        /**
         * skip 返回一个丢弃原Stream的前N个元素后剩下元素组成的新Stream，如果原Stream中包含的元素个数小于N，那么返回空Stream；
         */
        list1.stream().skip(2).forEach(System.out::println);

        /*	来一个字符串数组 将所有的字符 变成大写输出*/

        /**
         * map 方法用于映射每个元素到对应的结果
         */
        list1.stream().map(String::toUpperCase).forEach(System.out::println);

        /**
         * filter 方法用于通过设置的条件过滤出元素
         */
        list2.stream().filter(a -> a % 2 == 0).forEach(System.out::println);

        /**
         * sorted 方法用于对流进行排序
         */
        list1.stream().sorted((s1, s2) -> s2.length() - s1.length()).forEach(System.out::println);

        /**
         * distinct去重
         */
        list1.stream().distinct().forEach(System.out::println);


        String str = "ffda fd f xx ffff ccc";
        Stream.of(str.split(" ")).min(Comparator.comparingInt(String::length)).get();
        System.out.println(Stream.of(str.split(" ")).allMatch(i -> {
            System.out.println(i);
            return i.contains("x");
        }));

        List<String> integers = Arrays.asList("1", "2", "3", "4", "5", "6", "7");
        IntSummaryStatistics stats = integers.stream().mapToInt(Integer::parseInt).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        System.out.println("个数: " + stats.getCount());


        List<Student> sts = new ArrayList<>();

        sts.add(new Student("zhangsan", 10, "nv", 1));

        sts.add(new Student("zhangsan1", 11, "nan", 2));
        sts.add(new Student("zhangsan3", 12, "nan", 2));
        sts.add(new Student("zhangsan1", 25, "nv", 4));

        // 二次分组
        Map<String, IntSummaryStatistics> map = sts.stream().collect(Collectors
                .groupingBy(Student::getGender, Collectors.summarizingInt(Student::getAge)));
        System.out.println(map.get("nv").getSum() + "-----------");

        Map<Object, List<Student>> map2 = sts.stream().collect(Collectors.groupingBy(e -> e.getGrade() + e.getGender()));
        System.out.println(map2);


        List<String> list5 = new ArrayList<>();


        list5.add("a2312");
        list5.add("b");
        list5.add("cccc");

        list5.add("cccc");

        List<String> list6 = new ArrayList<>();

        list6.add("ddd");
        list6.add("ddddd");

        list6.add("cccc");

        // 并集,不去重
        list5.addAll(list6);

        list5.stream().distinct().forEach(System.out::println);
        Stream.of(list5, list6).forEach(System.out::println);


        // 交集
        System.out.println(list5.stream().filter(list6::contains).collect(toList()).get(0));


        /*map处理*/
        Map<String, Integer> map5 = new HashMap<>(16);

        map5.put("1", 123);
        map5.put("2", 123);
        map5.put("3", 123);


        map5.entrySet().forEach(e -> map5.put(e.getKey(), e.getValue() + 2));


        System.out.println(map5);


        /**
         * flatMap()：对每个元素执行mapper指定的操作，并用所有mapper返回的Stream中的元素组成一个新的Stream作为最终返回结果，通俗易懂就是将原来的stream中的所有元素都展开组成一个新的stream
         */
        //创建一个 装有两个泛型为integer的集合
        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(4, 5));
        // 将两个合为一个
        Stream<Integer> integerStream = stream.flatMap(
                (Function<List<Integer>, Stream<Integer>>) i -> i.stream());
        // 为新的集合
        List<Integer> collect = integerStream.collect(toList());
        System.out.println("新stream大小:" + collect.size());
        System.out.println("-----合并后-----");
        collect.forEach(System.out::println);


        List<Product> productArrayList = new ArrayList<>();
        productArrayList.add(new Product(1, 3));
        productArrayList.add(new Product(2, 1));
        productArrayList.add(new Product(3, 4));

        /**
         * findFirst() ：使用该方法获取第一个元素
         */
        Product product = productArrayList.stream().findFirst().get();

        List<User> list = Arrays.asList(
                // name，age
                new User("张三", 11),
                new User("王五", 20),
                new User("王五", 91),
                new User("张三", 8),
                new User("李四", 44),
                new User("李四", 44),
                new User("李四", 44)
        );

        Optional<User> reduceMaxAge = list.stream().reduce((s1, s2) -> s1.getAge() > s2.getAge() ? s1 : s2);
        User user = reduceMaxAge.get();
        System.out.println(user);

        // 求年龄之和
        // 0 该参数为初始值
        Integer reduceSumAge = list.stream().reduce(0,
                // integer, i 该参数为累加器，新元素如何累加
                (integer, i) -> integer + i.getAge(),
                // 多个部分如何合并
                Integer::sum);
        System.out.println(reduceSumAge);

        // 使用Collectors.joining()拼接字符串
        Stream<String> stringStream = Stream.of("张三", "李四", "王五", "赵六");

        // 张三李四王五赵六
        String string1 = stringStream.collect(Collectors.joining());

        // 张三-李四-王五-赵六
        String string2 = stringStream.collect(Collectors.joining("-"));

        // (张三-李四-王五-赵六)
        String string3 = stringStream.collect(Collectors.joining("-", "(", ")"));

        System.out.println(string1);
        System.out.println(string2);
        System.out.println(string3);



        List<Integer> integerList = new ArrayList<>();

        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);


        // 流的短路

        /**
         * anyMatch 是否有一个元素能匹配给定的谓词
         */
        boolean b = integerList.stream().anyMatch(i -> i == 2);

        /**
         * 是否匹配所有元素
         */
        boolean c = integerList.stream().allMatch(i -> i == 3);

        /**
         * 没有任何元素与给定的谓词匹配。
         */
        boolean  d = integerList.stream().noneMatch(i -> i == 0);

        /**
         * 返回当前流中的任意元素
         */
        Optional<Integer> optional = integerList.stream().findAny();


        /**
         * peek 操作 一般用于不想改变流中元素本身的类型或者只想元素的内部状态时；而 map 则用于改变流中元素本身类型，即从元素中派生出另一种类型的操作。这是他们之间的最大区别。
         * 比如对 Collection<T> 中的 T 的某些属性进行批处理的时候用 peek 操作就比较合适。如果我们要从 Collection<T> 中获取 T 的某个属性的集合时用 map 也就最好不过了。
         */
        List<Integer> list3 = integerList.stream().peek(System.out::println).collect(toList());

        /**
         * 并行流
         */
        Stream<Integer> parallel = integerList.stream().parallel();

        /**
         * 串行流
         */
        Stream<Integer> sequential = integerList.stream().sequential();

        /**
         * 消除流中必须保持的有序约束，因此允许之后的操作使用 不必考虑有序的优化。
         */
        integerList.stream().unordered().forEach(System.out::println);

    }


}