package REGEX;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicRegX {
    public static void checkStringAgainstRe(String re, String str) {
        Pattern pattern = Pattern.compile(re);
        Matcher matcher = pattern.matcher(str);
        boolean matches = matcher.matches();
        System.out.println(matches);
    }

    public static void main(String[] args) {
        while (true) {
            System.out.print("Enter Regular Expression:");
            Scanner sc = new Scanner(System.in);
            String re = sc.nextLine();
            System.out.print("Enter your String: ");
            String str = sc.nextLine();
            checkStringAgainstRe(re, str);
            System.out.println("Do you want to exit: y/n");
            String choice = sc.nextLine();
            if (choice == "y") {
                System.out.println("Thanks for using.");
                break;
            } else {
                continue;
            }
        }
        //Regx to print alphanumeric characters [a-zA-Z0-9]+ .
        // Regx to check for 10 digit number [0-9]+{10} here 10 inside curly braces means not more than 10 characters.
        //Regx to match a email address:^[a-zA-Z0-9_.$-]+@[a-zA-Z0-9]+\.[a-zA-Z]{2,}$ 
    }
}
