# Regular Expressions in Java
## Introduction
- **Regular Expression (Regex)** in Java is a **pattern** used to **match, search, and manipulate text**.  
- Package: `java.util.regex`
- Composed of **special symbols**, **metacharacters**, and **quantifiers** that define matching rules.

### Common Use Cases
- Input **validation** (email, phone, password)
- **Text searching**, **filtering**, and **extraction**
- **Data cleaning**, **log analysis**, and **pattern detection**

---

## Core Classes

| Class | Description |
|--------|-------------|
| `Pattern` | Compiles a regex into a pattern (immutable). |
| `Matcher` | Applies a pattern to text for matching operations. |
| `PatternSyntaxException` | Thrown when regex pattern has invalid syntax. |

### Example
```java
import java.util.regex.*;

public class RegexExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher("Hello123");

        if (matcher.find()) {
            System.out.println("Match found: " + matcher.group());
        } else {
            System.out.println("No match found");
        }
    }
}
```
## Metacharacters
| Symbol | Meaning                       | Example  | Matches       |
| ------ | ----------------------------- | -------- | ------------- |
| `.`    | Any character except newline  | `a.b`    | `acb`, `a#b`  |
| `^`    | Start of string               | `^Hello` | `Hello World` |
| `$`    | End of string                 | `end$`   | `the end`     |
| `\d`   | Any digit `[0-9]`             | `\d{3}`  | `123`         |
| `\D`   | Non-digit                     | `\D+`    | `abc`         |
| `\w`   | Word character `[a-zA-Z0-9_]` | `\w+`    | `word_123`    |
| `\W`   | Non-word character            | `\W`     | `#`, `@`      |
| `\s`   | Whitespace                    | `\s+`    | space, tab    |
| `\S`   | Non-whitespace                | `\S+`    | `text`        |

## Quantifiers

| Quantifier | Description           | Example  | Matches             |
| ---------- | --------------------- | -------- | ------------------- |
| `*`        | 0 or more times       | `a*`     | `""`, `a`, `aaa`    |
| `+`        | 1 or more times       | `a+`     | `a`, `aaaa`         |
| `?`        | 0 or 1 time           | `a?`     | `""`, `a`           |
| `{n}`      | Exactly n times       | `\d{3}`  | `123`               |
| `{n,}`     | At least n times      | `\d{2,}` | `12`, `1234`        |
| `{n,m}`    | Between n and m times | `a{2,4}` | `aa`, `aaa`, `aaaa` |

## Character Classes

| Expression    | Meaning                  | Example       |
| ------------- | ------------------------ | ------------- |
| `[abc]`       | Matches `a`, `b`, or `c` | `bat`, `cat`  |
| `[^abc]`      | Not `a`, `b`, or `c`     | `dog`         |
| `[a-z]`       | Any lowercase letter     | `x`, `f`      |
| `[A-Z]`       | Any uppercase letter     | `Z`, `B`      |
| `[0-9]`       | Any digit                | `5`, `9`      |
| `[a-zA-Z0-9]` | Any alphanumeric         | `K9`, `test2` |

## Common Regex Examples

| Purpose           | Regex                                        | Description                                             |
| ----------------- | -------------------------------------------- | ------------------------------------------------------- |
| Email Validation  | `^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,6}$`  | Matches valid email format                              |
| Phone Number      | `\\d{10}`                                    | Matches 10-digit number                                 |
| Password (Strong) | `^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$` | At least 8 chars, one uppercase, one digit, one special |
| Only Alphabets    | `^[A-Za-z]+$`                                | Only letters                                            |
| Postal Code       | `^[1-9][0-9]{5}$`                            | 6-digit PIN starting with 1–9                           |

## Matcher Methods

| Method              | Description                                        |
| ------------------- | -------------------------------------------------- |
| `find()`            | Attempts to find the next subsequence that matches |
| `matches()`         | Checks if entire string matches the pattern        |
| `lookingAt()`       | Matches from start of string                       |
| `group()`           | Returns matched substring                          |
| `start()` / `end()` | Returns start and end index of match               |
| `replaceAll()`      | Replaces all matches                               |
| `split()`           | Splits text based on regex delimiter               |

### Example:
```
Pattern p = Pattern.compile("\\d+");
Matcher m = p.matcher("ID123 Code456");
while (m.find()) {
    System.out.println("Found: " + m.group());
}
```

## Pattern Flags

| Flag                       | Description                             | Usage                                               |
| -------------------------- | --------------------------------------- | --------------------------------------------------- |
| `Pattern.CASE_INSENSITIVE` | Ignores case                            | `Pattern.compile("java", Pattern.CASE_INSENSITIVE)` |
| `Pattern.MULTILINE`        | Enables multi-line mode for `^` and `$` |                                                     |
| `Pattern.DOTALL`           | Makes `.` match newline characters      |                                                     |
| `Pattern.UNICODE_CASE`     | Enables Unicode case folding            |                                                     |

## Few Examples:

// RegexValidations.java
```
import java.util.regex.*;
import java.util.*;

public class RegexValidations {

    // Helper: generic regex match
    private static boolean matches(String regex, String input) {
        return Pattern.compile(regex).matcher(input).matches();
    }

    // 1) Indian Mobile Number (10 digits, starts with 6-9)
    // Examples: 9876543210
    public static boolean isValidIndianMobile(String mobile) {
        String regex = "^[6-9]\\d{9}$";
        return matches(regex, mobile);
    }

    // 2) International phone (E.164-like basic support)
    // Examples: +919876543210, +1-202-555-0187, 001-202-555-0187 (loose)
    public static boolean isValidInternationalPhone(String phone) {
        // Accepts optional + or 00, digits, spaces, hyphens, parentheses
        String regex = "^(?:\\+|00)?[0-9]{1,3}[-.\\s]?\\(?\\d+\\)?(?:[-.\\s]?\\d+)*$";
        return matches(regex, phone);
    }

    // 3) Email (practical, not perfect RFC)
    // Examples: user.name+tag@example.co.in
    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return matches(regex, email);
    }

    // 4) Strong Password
    // At least 8 chars, at least one upper, one lower, one digit, one special char
    public static boolean isStrongPassword(String pwd) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?#&^()_+\\-=\\[\\]{};:'\",.<>\\/\\\\|]).{8,}$";
        return matches(regex, pwd);
    }

    // 5) Aadhaar (India) - 12 digits (note: real Aadhaar uses Verhoeff checksum; here we validate format)
    public static boolean isValidAadhaar(String aadhaar) {
        String regex = "^\\d{12}$";
        return matches(regex, aadhaar);
    }

    // 6) PAN (India) - 5 letters, 4 digits, 1 letter. Example: ABCDE1234F
    public static boolean isValidPAN(String pan) {
        String regex = "^[A-Z]{5}[0-9]{4}[A-Z]$";
        return matches(regex, pan);
    }

    // 7) GSTIN (India) - 15 chars: 2 digits | 5 letters | 4 digits | 1 letter | 1 alnum | 'Z' | 1 alnum
    // Example: 22AAAAA0000A1Z5
    public static boolean isValidGSTIN(String gstin) {
        String regex = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z][1-9A-Z]Z[0-9A-Z]$";
        return matches(regex, gstin);
    }

    // 8) Indian PIN Code (6 digits, not starting with 0)
    public static boolean isValidPIN(String pin) {
        String regex = "^[1-9][0-9]{5}$";
        return matches(regex, pin);
    }

    // 9) IPv4 address
    public static boolean isValidIPv4(String ip) {
        String num = "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)";
        String regex = "^(" + num + "\\.){3}" + num + "$";
        return matches(regex, ip);
    }

    // 10) URL (basic)
    public static boolean isValidURL(String url) {
        String regex = "^(https?://)?" +                     // optional http/https
                       "([\\w\\-]+\\.)+[A-Za-z]{2,}" +       // domain
                       "(:\\d{1,5})?" +                      // optional port
                       "(/[^\\s]*)?$";                       // optional path
        return matches(regex, url);
    }

    // 11) Username: letters, numbers, underscores; 3-20 chars; cannot start with digit
    public static boolean isValidUsername(String username) {
        String regex = "^[A-Za-z_][A-Za-z0-9_]{2,19}$";
        return matches(regex, username);
    }

    // 12) Date (YYYY-MM-DD) basic validation (does not check month/day range to perfection)
    public static boolean isValidDateISO(String date) {
        String regex = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-([0-2][0-9]|3[01])$";
        return matches(regex, date);
    }

    // 13) Credit / Debit Card validation
    // A) Regex that matches most card formats (Visa, MasterCard, Amex, Discover)
    // B) Luhn algorithm to ensure checksum validity
    public static boolean isValidCardNumberRegex(String cardNumber) {
        // remove spaces/hyphens
        String digits = cardNumber.replaceAll("[\\s-]", "");
        // Common card patterns:
        // Visa: 4[0-9]{12}(?:[0-9]{3})?
        // MasterCard: 5[1-5][0-9]{14} or 2221-2720
        // AmEx: 3[47][0-9]{13}
        // Discover: 6011|65|64[4-9]
        String regex = "^(?:4[0-9]{12}(?:[0-9]{3})?" +             // Visa 13 or 16
                       "|5[1-5][0-9]{14}" +                       // MasterCard 16
                       "|2(?:2[2-9][0-9]{12}|[3-6][0-9]{13}|7[01][0-9]{12}|720[0-9]{12})" + // MasterCard 16 (new range)
                       "|3[47][0-9]{13}" +                        // American Express 15
                       "|6(?:011|5[0-9]{2}|4[4-9][0-9])?[0-9]{12}" + // Discover-ish
                       ")$";
        return matches(regex, digits);
    }

    // Luhn checksum
    public static boolean passesLuhn(String cardNumber) {
        String digits = cardNumber.replaceAll("[\\s-]", "");
        int sum = 0;
        boolean alternate = false;
        for (int i = digits.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(digits.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1; // same as n-9
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    // Combined card validator: regex + Luhn
    public static boolean isValidCard(String cardNumber) {
        return isValidCardNumberRegex(cardNumber) && passesLuhn(cardNumber);
    }

    // 14) Debit card number: usually same format as credit card for validation purposes
    public static boolean isValidDebitCard(String debitCardNumber) {
        return isValidCard(debitCardNumber); // same checks: pattern + Luhn
    }

    // 15) Extract numbers from a text (example of text extraction)
    public static List<String> extractNumbers(String text) {
        List<String> result = new ArrayList<>();
        Matcher m = Pattern.compile("\\d+").matcher(text);
        while (m.find()) {
            result.add(m.group());
        }
        return result;
    }

    // Demonstration main
    public static void main(String[] args) {
        // Sample test cases
        System.out.println("Indian mobile 9876543210: " + isValidIndianMobile("9876543210"));
        System.out.println("International +1-202-555-0187: " + isValidInternationalPhone("+1-202-555-0187"));
        System.out.println("Email test.user+tag@example.co.in: " + isValidEmail("test.user+tag@example.co.in"));
        System.out.println("Strong password Abc@1234: " + isStrongPassword("Abc@1234"));
        System.out.println("Aadhaar 123412341234: " + isValidAadhaar("123412341234"));
        System.out.println("PAN ABCDE1234F: " + isValidPAN("ABCDE1234F"));
        System.out.println("GSTIN 22AAAAA0000A1Z5: " + isValidGSTIN("22AAAAA0000A1Z5"));
        System.out.println("PIN 560001: " + isValidPIN("560001"));
        System.out.println("IPv4 192.168.1.1: " + isValidIPv4("192.168.1.1"));
        System.out.println("URL https://example.com: " + isValidURL("https://example.com"));
        System.out.println("Username _user99: " + isValidUsername("_user99"));
        System.out.println("Date 2023-09-28: " + isValidDateISO("2023-09-28"));

        // Card samples (Visa test number passes Luhn)
        String visaTest = "4111 1111 1111 1111";
        System.out.println("Visa (regex) : " + isValidCardNumberRegex(visaTest));
        System.out.println("Visa (Luhn)  : " + passesLuhn(visaTest));
        System.out.println("Visa (combined): " + isValidCard(visaTest));

        // Extract numbers example
        System.out.println("Numbers in 'Order 123, Item 45' => " + extractNumbers("Order 123, Item 45"));
    }
}
```
## Useful tips:

- In Java strings, backslashes must be escaped (e.g. \\d to represent \d).

- Regex in code is often combined with Pattern.compile() when you need Matcher features like .find() or capturing groups. For simple whole-string checks you can use Pattern.matches(regex, input) or the helper matches() above.

- Card validation: format regex is necessary but not sufficient — always include Luhn checksum to catch common mistypes.

- Aadhaar: real Aadhaar numbers use a Verhoeff checksum. Regex \d{12} only checks format — implement Verhoeff for stronger validation if needed.

- Email: full RFC 5322 compliance is extremely complex. Use a practical regex for most cases and additional checks (MX lookup) for high-assurance validation.

- URLs: many valid URLs exist that are hard to cover fully with regex; consider using java.net.URL for parsing along with basic regex checks.

- When validating user input for security-critical flows (payments, identity), combine regex checks with server-side verification (bank APIs, checksum algorithms) and never rely solely on front-end regex.

## Summary — Regular Expressions in Java

- **Regex** is a powerful tool for **pattern matching, validation, and text manipulation**.  
- In Java, regex is handled using the `java.util.regex` package (`Pattern`, `Matcher`).
- Always **escape backslashes** in Java strings — use `"\\d"` instead of `"\d"`.
- Use:
  - `Pattern.compile()` → for reusable and complex regex operations.
  - `Matcher` → for searching, extracting, or finding multiple matches.
  - `Pattern.matches()` → for simple one-time validations.
- **Validation Use Cases:**
  - Mobile numbers, emails, passwords, Aadhaar, PAN, GSTIN, credit/debit cards, etc.
- **Checksum Algorithms:**
  - Use **Luhn algorithm** for validating card numbers.
  - Use **Verhoeff algorithm** for Aadhaar number verification.
- **Emails & URLs:**
  - Regex checks format, but additional checks (like MX record or `java.net.URL`) are needed for real validity.
- **Security Practices:**
  - Always perform **server-side validation** in addition to regex checks.
  - Combine regex with **API verification** and **input sanitization**.
- **Remember:** Regex ensures correct **format**, not **authenticity** — use it wisely for reliability and security.

---

