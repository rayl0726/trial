package demo.problems;

import java.util.*;

/**
 * @author liulei
 */
public class StringFilter {

    private static String method1(String str) {
        if(str == null || str.equals("")) {
            return "";
        }
        char[] chars = str.toCharArray();
        int len = chars.length;
        Map<Object, Object> filter = new HashMap<>();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char temp = chars[i];
            if(null == filter.put(temp, 1)) {
                result.append(temp);
            }
        }
        return result.toString();
    }


    private static String method2(String str) {
        if(str == null || str.equals("")) {
            return "";
        }
        char[] chars = str.toCharArray();
        int len = chars.length;
        StringBuilder result = new StringBuilder();
        char[] filter = new char[len];
        int count = 0;
        for (int i = 0; i < len; i++) {
            String temp = chars[i] + "";
            if(!contains(temp, filter)) {
                result.append(temp);
                filter[count] = chars[i];
                count++;
            }
        }
        return result.toString();
    }


    private static boolean contains(String str, char[] filter) {
        for (char c : filter) {
            if ((c + "").equals(str)) {
                return true;
            }
        }
        return false;

    }


    public static  String convert(String sou) {
        if (sou == null || sou.trim().length() == 0) {
            return "";
        }
        int length = sou.length();
        LinkedHashMap<Character, Object> result = new LinkedHashMap<>();
        for (int i = 0; i < length; i++) {
            result.put(sou.charAt(i), i);
        }

        Set<Character> set = result.keySet();
        StringBuilder res = new StringBuilder();
        for (Character item : set) {
            res.append(item);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String str = convert("2341233dfmlg dfgsdfgkakewjhdsdsassaf");
        System.out.println(str);
    }
}
