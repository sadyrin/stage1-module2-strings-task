package com.epam.mjc;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.epam.mjc.MethodSignature.Argument;

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
        String[] parts = signatureString.split("\\(");
        String firstPart = parts[0].trim();
        String secondPart = parts[1].trim();

        if(!secondPart.equals(")")) secondPart = secondPart.split("\\)")[0];
        else secondPart = null;

        String[] firstParts = null;
        firstParts = firstPart.split(" ");
        String[] secondParts = null;
        if(secondPart != null) secondParts = secondPart.split(",");
        
        String methodName = "";
        methodName = firstParts[firstParts.length - 1];
        String returnType = "";
        returnType = firstParts[firstParts.length - 2];
        boolean isStatic = false;
        String modifier = null;

        if(firstParts.length > 2) {
            if(firstParts[firstParts.length - 3].equals("static")) isStatic = true;
            else modifier = firstParts[firstParts.length - 3];
        } 
        
        if(firstParts.length > 3) modifier = firstParts.length == 3 ? firstParts[firstParts.length - 3] : firstParts[firstParts.length - 4];

        List<Argument> arguments = new ArrayList<>();

        if(secondParts != null){
            for(String arg : secondParts){
                arg = arg.trim();
                String[] argParts = arg.split(" ");
                String argType = null;
                String argName = null;
                if(argParts.length > 1){
                    argType = argParts[0];
                    argName = argParts[1];

                    Argument argument = new Argument(argType, argName);
        
                    arguments.add(argument);
                }
            }
        }

        MethodSignature out = new MethodSignature(methodName, arguments);
        out.setAccessModifier(modifier);
        out.setReturnType(returnType);

        return out;
    }
}
