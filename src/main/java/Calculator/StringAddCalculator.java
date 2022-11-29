package Calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAddCalculator {
    private static final Pattern pattern = Pattern.compile("//(.)\n(.*)");
    private static final int customDelimiterPos = 1;
    private static final int textToSplitPos = 2;
    private static final String defaultValidator01 = ",";
    private static final String defaultValidator02 = ":";
    private static final String defaultValidatorString = "[" + defaultValidator01 + defaultValidator02 + "]";

    public static int splitAndSum(String text) {
        if (isBlankString(text))
            return 0;
        return stringSplitValidator(text);
    }

    private static boolean isBlankString(String text) {
        return text == null || text.equals("");
    }

    private static Integer stringSplitValidator(String text) {
        if (isStringInteger(text))
            return Integer.parseInt(text);
        Matcher m = pattern.matcher(text);
        if (m.find()) {
            String customDelimiter = m.group(customDelimiterPos);
            String[] tokens = m.group(textToSplitPos).split(customDelimiter);
            return sumSplitTokens(tokens);
        }
        isStringDefaultValidator(text);
        String[] tokens = text.split(defaultValidatorString);
        return sumSplitTokens(tokens);
    }

    private static int sumSplitTokens(String[] tokens) {
        int result = 0;
        for (String token : tokens) {
            if (!isStringInteger(token))
                throw new RuntimeException("ParseInt(token) Failed");
            result += Integer.parseInt(token);
        }
        return result;
    }

    private static void isStringDefaultValidator(String text) {
        if (text.equals(defaultValidator01)) {
            throw new RuntimeException("Parse Failed");
        }
        if (text.equals(defaultValidator02)) {
            throw new RuntimeException("Parse Failed");
        }
    }

    private static boolean isStringInteger(String token) {
        int result = 0;
        try {
            result = Integer.parseInt(token);
        } catch (Exception e) {
            return false;
        }
        if (result < 0) {
            return false;
        }
        return true;
    }

}
