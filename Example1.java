package REGEX;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Example1 {
    public static void main(String[] args) {
        // String regex = "a";
        Pattern pattern = Pattern.compile("[a-zA-z]");
        Matcher matcher = pattern.matcher("ab");
        System.out.println(matcher.matches());

    }
}
