package Calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAddCalculator {

    public static final int ERROR_PARSE_INT = 1;
    public static final int ERROR_NEGATIVE_NUM = 2;


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
            return sumSplitTokens(tokens);
        }
        String[] tokens= text.split(",|:");
        return sumSplitTokens(tokens);
    }

    public static int sumSplitTokens(String[] tokens)
    {
        int result = 0;
        for(int i=0;i<tokens.length;i++) {
            result += isValidateToken(tokens[i]);
        }
        return result;
    }

    public static int isValidateToken(String token) throws RuntimeException
    {
        Integer result =0 ;
        try {
            result  = Integer.parseInt(token);
        }
        catch(Exception e) {
            throw ErrorHandler(ERROR_PARSE_INT);
        }
        if(result <0) {
            throw ErrorHandler(ERROR_NEGATIVE_NUM);
        }
        return result;
    }

    public static RuntimeException ErrorHandler(int error) throws RuntimeException{
        if(error == ERROR_PARSE_INT){
            RuntimeException parseIntError = new RuntimeException("ParseInt(token) Failed");
            throw parseIntError;
        }
        if(error == ERROR_NEGATIVE_NUM){
            RuntimeException negativeNumError = new RuntimeException("Negative Number Found");
            throw negativeNumError;
        }
        RuntimeException unknownError = new RuntimeException("Unknown Error");
        throw unknownError;
    }

}
