package demo.problems;

import java.math.BigDecimal;

/**
 * @author liulei
 */
public class BigNumAdd {

    private static String bigNumAdd1(String num1, String num2) {
        //翻转两个字符串
        String chars1 = new StringBuilder(num1).reverse().toString();
        String chars2 = new StringBuilder(num2).reverse().toString();
        //获取最大的字符串长度
        int maxlength = Math.max(chars1.length(), chars2.length());
        StringBuilder result = new StringBuilder(maxlength + 1);
        int carry = 0;
        for(int i = 0; i < maxlength; i++) {
            int tempNum1 = chars1.length() - 1 < i ? 0 :  (chars1.charAt(i) - '0');
            int tempNum2 = chars2.length() - 1 < i ? 0 :  (chars2.charAt(i) - '0');
            int tempNum = tempNum1 + tempNum2 + carry;
            int currentNum = tempNum % 10;
            carry = tempNum / 10;
            result.append(currentNum);
        }

        if(carry > 0) {
            result.append(carry);
        }

        return result.reverse().toString();
    }


    private static String bigNumAdd2(String num1, String num2) {
        BigDecimal decimal1 = new BigDecimal(num1);
        BigDecimal decimal2 = new BigDecimal(num2);

        BigDecimal result = decimal1.add(decimal2);
        return result.toString();
    }

    private static String bigIntegerAdd(String num1, String num2, int floatCarry) {
        //翻转两个字符串
        String chars1 = new StringBuilder(num1).reverse().toString();
        String chars2 = new StringBuilder(num2).reverse().toString();
        //获取最大的字符串长度
        int maxlength = Math.max(chars1.length(), chars2.length());
        StringBuilder result = new StringBuilder(maxlength + 1);
        int carry = 0;
        for(int i = 0; i < maxlength; i++) {
            int tempNum1 = chars1.length() - 1 < i ? 0 :  (chars1.charAt(i) - '0');
            int tempNum2 = chars2.length() - 1 < i ? 0 :  (chars2.charAt(i) - '0');
            int tempNum = 0;
            if(i == 0 && floatCarry > 0) {
                tempNum = tempNum1 + tempNum2 + carry + floatCarry;
            } else {
                tempNum = tempNum1 + tempNum2 + carry;
            }
            int currentNum = tempNum % 10;
            carry = tempNum / 10;
            result.append(currentNum);
        }

        if(carry > 0) {
            result.append(carry);
        }

        return result.reverse().toString();
    }

    private static String bigDecimalAdd(String num1, String num2) throws Exception {
        String[] split1 = num1.split("\\.");
        String[] split2 = num2.split("\\.");

        if(split1.length > 2 || split2.length > 2) {
            throw new Exception("params wrong");
        }

        //计算小数部分
        String float1 = null;
        String float2 = null;
        if(split1.length > 1) {
            float1 = split1[1];
        }
        if(split2.length > 1) {
            float2 = split2[1];
        }
        //获取最大的字符串长度
        int maxlength = 0;
        if(float1 == null && float2 == null) {
        } else if(float1 == null) {
            maxlength = float2.length();
            float1 = "";
        } else if(float2 == null) {
            maxlength = float1.length();
            float2 = "";
        } else {
            maxlength = Math.max(float1.length(), float2.length());
        }
        StringBuilder floatResult = new StringBuilder(maxlength + 1);
        int floatCarry = 0;
        if(maxlength > 0) {
            for(int i = 0; i < maxlength; i++) {
                int tempNum1 = float1.length() - 1 < i ? 0 :  (float1.charAt(i) - '0');
                int tempNum2 = float2.length() - 1 < i ? 0 :  (float2.charAt(i) - '0');
                int tempNum = tempNum1 + tempNum2 + floatCarry;
                int currentNum = tempNum % 10;
                floatCarry = tempNum / 10;
                floatResult.append(currentNum);
            }
        }
        String decimalResult = floatResult.toString();
        String integerResult = bigIntegerAdd(split1[0], split2[0], floatCarry);
        return decimalResult.length() > 0 ? integerResult + "." + decimalResult : integerResult;
    }


    public static void main(String[] args) throws Exception {
        String a = "921.42342";
        String b = "1.12324123";
        System.out.println(bigDecimalAdd(a, b));
    }
}
