package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        signatureString = removeCharAt(signatureString, signatureString.length() - 1);
        String[] str = signatureString.split("\\(");
        String[] atm = str[0].split(" ");
        List<MethodSignature.Argument> args = new ArrayList<>();

        if (str.length > 1) {
            String[] aa = str[1].split(",");
            for (String arg : aa) {
                String[] aT = arg.trim().split(" ");
                args.add(new MethodSignature.Argument(aT[0], aT[1]));
            }
        }
        MethodSignature res;
        if (atm.length > 2) {
            res = new MethodSignature(atm[2], args);
            res.setAccessModifier(atm[0]);
            res.setReturnType(atm[1]);
            return res;
        } else {
            res = new MethodSignature(atm[1], args);
            res.setReturnType(atm[0]);
            return res;
        }
    }

    public static String removeCharAt(String str, int index) {
        return str.substring(0, index) + str.substring(index + 1);
    }

}
