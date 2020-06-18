package com.liyuan.wave.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author liyuan
 * @version jdk1.8
 * @description 字符串工具类
 * @create 2020-01-24-10:27
 */
public class StringUtils {

    /**
     * 空字符串
     */
    private static final String EMPTY_STR = "";

    /**
     * 逗号
     */
    public static final String COMMA_STR = ",";

    /**
     * @param value        要判断的value
     * @param defaultValue 默认值
     * @return T 返回值
     * @description 对象非空判断, 取默认值
     */
    public static <T> T comma(T value, T defaultValue) {
        return Optional.ofNullable(value).orElse(defaultValue);
    }

    /**
     * @param coll 要判断的Collection
     * @return true：为空 false：非空
     * @description 判断一个Collection是否为空
     */
    public static boolean isEmpty(Collection<?> coll) {
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * @param coll 要判断的Collection
     * @return true：非空 false：空
     * @description 判断一个Collection容器是否非空
     */
    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * @param objects 要判断的对象数组
     * @return true：为空 false：非空
     * @description 判断一个对象数组是否为空
     */
    public static boolean isEmpty(Object[] objects) {
        return isNull(objects) || (objects.length == 0);
    }

    /**
     * @param objects 要判断的对象数组
     * @return true：非空 false：空
     * @description 判断一个对象数组是否非空
     */
    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }


    /**
     * @param str 判断对象
     * @return true：为空 false：非空
     * @description 判断一个字符串是否为空串(方法内部前后尾去空)
     */
    public static boolean isEmpty(String str) {
        return isNull(str) || EMPTY_STR.equals(str.trim());
    }


    /**
     * @param str String
     * @return true：非空串 false：空串
     * @description 判断一个字符串是否为非空串
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * @param object Object
     * @return true：为空 false：非空
     * @description 判断一个对象是否为空
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * @param object Object
     * @return true：非空 false：空
     * @description 判断一个对象是否非空
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * @param object 对象
     * @return true：是数组 false：不是数组
     * @description 判断一个对象是否是数组类型（Java基本型别的数组）
     */
    public static boolean isArray(Object object) {
        return isNotNull(object) && object.getClass().isArray();
    }

    /**
     * @param str 需要去除的对象
     * @return
     * @description 去除字符串前后端空格
     */
    public static String trim(String str) {
        return str == null ? "" : str.trim();
    }

    /**
     * @param str "1,2,3"
     * @return
     * @description 逗号字符串转Integer集合
     */
    public static List<Integer> stringToIntegerList(String str) {
        if (isEmpty(str)) {
            return new ArrayList<>();
        }
        String[] strings = str.trim().split(COMMA_STR);
        return Arrays.stream(strings).filter(i -> isNotEmpty(i) && i.matches("^[0-9]*$")).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList());
    }

    /**
     * @param str "1,2,3"
     * @return
     * @description 逗号字符串转Long集合
     */
    public static List<Long> stringToLongList(String str) {
        if (isEmpty(str)) {
            return new ArrayList<>();
        }
        String[] strings = str.trim().split(COMMA_STR);
        return Arrays.stream(strings).filter(i -> isNotEmpty(i) && i.matches("^[0-9]*$")).mapToLong(Long::valueOf).boxed().collect(Collectors.toList());
    }

    /**
     * @param length
     * @return
     * @description 生成指定长度的随机数字
     */
    public static String randomCode(int length) {
        String[] num = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        //创建Random类的对象rand
        Random rand = new Random();
        int index = 0;
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            index = rand.nextInt(num.length - 1);
            sbf.append(num[index]);
        }
        return sbf + "";
    }

    /**
     * @param date
     * @param pattern 需要转换成的格式 yyyy/MM/dd hh:MM:ss    yyyy-MM-dd hh:MM:ss   yyyy-MM-dd
     * @return 返回的字符串
     * @description 返回日期字符串  yyyy-MM-dd
     */
    public static String dateToString(Date date, String pattern) {
        date = Optional.ofNullable(date).orElse(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * @param object
     * @return
     * @description 检查某个对象的各个属性是否为空
     */
    public static boolean checkFieldAllNull(Object object) {
        if (object == null) {
            return true;
        }
        try {
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (Modifier.isFinal(f.getModifiers()) && Modifier.isStatic(f.getModifiers())) {
                    continue;
                }
                if (!isEmpty(f.get(object))) {
                    return false;
                }
                f.setAccessible(false);
            }
            //父类public属性
            for (Field f : object.getClass().getFields()) {
                f.setAccessible(true);
                if (Modifier.isFinal(f.getModifiers()) && Modifier.isStatic(f.getModifiers())) {
                    continue;
                }
                if (!isEmpty(f.get(object))) {
                    return false;
                }
                f.setAccessible(false);
            }
        } catch (IllegalAccessException e) {
            return true;
        }
        return true;
    }

    /**
     * @param object
     * @return
     * @description 检查某个对象的是否为空(对象的属性无法判断)
     */
    private static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String && (EMPTY_STR.equals(object.toString().trim()))) {
            return true;
        }
        if (object instanceof Collection && ((Collection) object).isEmpty()) {
            return true;
        }
        if (object instanceof Map && ((Map) object).isEmpty()) {
            return true;
        }
        if (object instanceof Object[] && ((Object[]) object).length == 0) {
            return true;
        }
        return false;
    }

    /**
     * @param string
     * @return
     * @description 字符串正则清空特殊字符串
     */
    public static String clearSpecialChar(String string) {
        if (isEmpty(string)) {
            return EMPTY_STR;
        }
        String regEx = "[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(string);
        return m.replaceAll(EMPTY_STR).trim();
    }

    /**
     * @param string
     * @return
     * @description 字符串正则手机号
     */
    public static boolean regexMobilePhone(String string) {
        if (isEmpty(string)) {
            return false;
        }
        String regex = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[\\d]{8}$";
        return Pattern.compile(regex).matcher(string).matches();
    }

    /**
     * @param str     验证字符串
     * @param strings 字符串组
     * @return 包含返回true
     * @description 是否包含字符串
     */
    public static boolean inStringIgnoreCase(String str, String... strings) {
        if (str != null && strings != null) {
            for (String s : strings) {
                if (str.equalsIgnoreCase(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * @param text 判断的对象
     * @return 包含返回true
     * @description 对象有效
     */
    public static boolean effective(Long text) {
        return text != null && text > -1L;
    }


    /**
     * @param text 判断的对象
     * @return 包含返回true
     * @description 对象无效
     */
    public static boolean invalid(Long text) {
        return !effective(text);
    }
}
