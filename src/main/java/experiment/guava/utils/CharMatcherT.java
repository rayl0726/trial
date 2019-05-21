package experiment.guava.utils;

import com.google.common.base.CharMatcher;

/**
 * @author : liulei
 **/
public class CharMatcherT {
    public static void main(String[] args) {
        String s = CharMatcher.breakingWhitespace().trimAndCollapseFrom("    String     with    spaces       and      tabs     ", ' ');
        System.out.println(s);

        String digit = CharMatcher.javaDigit().retainFrom("foo123123");
        System.out.println(digit);
    }
}
