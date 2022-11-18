package Calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAddCalculator {

    public static int splitAndSum(String text) {
        if (text == null || text == "") {
            return 0;
        }
        Integer result = stringSplitValidator(text);
        if(result == 0) {
            result = Integer.parseInt(text);
        }
        return result;
    }

    public static Integer stringSplitValidator(String text)
    {
        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);
        if (m.find()) {
            String customDelimiter = m.group(1);
            String[] tokens= m.group(2).split(customDelimiter);
            return 0;
        }
        String[] tokens= text.split(",|:");
        return 0;
    }
}
