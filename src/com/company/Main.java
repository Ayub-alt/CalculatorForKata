
/*
This program consists of 7 methods besides the 'Main' method.
The methods are:
        convertInputIntoArabic() - This method takes the INPUT of a user and converts it to arabic. If the conversion is
                                   completed successfully, it sends the numbers and the type of operation to
                                   'calculateValues()' method.
        convertInputIntoRoman() - This method takes the INPUT of a user and converts it to roman. If the conversion is
                                   completed successfully, it tries to convert roman numbers to arabic using
                                   'convertFromRomanIntoArabic()' and then sends the numbers and the type of operation to
                                   'calculateValues()' method.
        convertFromRomanIntoArabic - this method converts roman numbers to arabic numbers.
        convertFromArabicIntoRoman - this method, in order to view the result converts numbers from arabic to roman
                                     numbers using 'switchMethodForRomanNums()' method.
        switchMethodForRomanNums - this method converts arabic numbers to roman numbers, by splitting the value into
                                    two separate values(if arabic is 24, it split it into '2' and '4' |  if arabic is 65,
                                    it splits it into '6' and '5' and so on) and converting each values into roman, and
                                    then joins them.
        calculateValues() - in this method all calculations happen and the result returns.
        outputResult() - is used for printing the result in console.
 */


package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input:");
        String input = scanner.nextLine();

        if(input.length() < 3)
        {
            outputResult("throws Exception!");
        }
        else if(convertInputIntoArabic(input) == false)
        {
            convertInputIntoRoman(input);
        }

    }

    //Method to convert input into arabic.
    static boolean convertInputIntoArabic(String input)
    {
        int a = 0, b = 0;
        char operation = '0';
        try
        {

            if(input.length() == 3)
            {
                a = Character.getNumericValue(input.charAt(0));
                b = Character.getNumericValue(input.charAt(2));
                operation = (char) input.charAt(1);

            }
            else if(input.length() == 4 && input.contains("10"))
            {
                if(input.startsWith("10"))
                {
                    a=10;
                    b= Character.getNumericValue (input.charAt(input.length()-1));
                    operation= (char) input.charAt(input.length()-2);
                }
                else
                {
                    a = Character.getNumericValue(input.charAt(0));
                    b =10;
                    operation = (char) input.charAt(1);
                }
            }
            else if(input.startsWith("10") && input.endsWith("10"))
            {
                a = b = 10;
                operation = (char) input.charAt(2);
            }
            if((a > 0 && a <= 10) || (b > 0 && b <= 10))
            {
                outputResult(String.valueOf(calculateValues(a, b, operation)));
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }
    }


    //Method to convert input to roman numbers.
    static void convertInputIntoRoman(String  input)
    {
        String[] arrayOfNumbers = null;
        int a =0, b = 0;
        char operation = '.';

        if(input.contains("+"))
        {
            arrayOfNumbers = input.split("\\+");
            operation = '+';
            a = convertFromRomanIntoArabic(arrayOfNumbers[0]);
            b = convertFromRomanIntoArabic(arrayOfNumbers[1]);
        }
        else if(input.contains("-"))
        {
            arrayOfNumbers = input.split("-");
            operation = '-';
            a = convertFromRomanIntoArabic(arrayOfNumbers[0]);
            b = convertFromRomanIntoArabic(arrayOfNumbers[1]);
        }
        else if(input.contains("*"))
        {
            arrayOfNumbers = input.split("\\*");
            operation = '*';
            a = convertFromRomanIntoArabic(arrayOfNumbers[0]);
            b = convertFromRomanIntoArabic(arrayOfNumbers[1]);
        }
        else if(input.contains("/"))
        {
            arrayOfNumbers = input.split("/");
            operation = '/';
            a = convertFromRomanIntoArabic(arrayOfNumbers[0]);
            b = convertFromRomanIntoArabic(arrayOfNumbers[1]);
        }
        else
        {
            outputResult("throws Exception!");
            System.exit(0);
        }

        outputResult(String.valueOf(convertFromArabicIntoRoman(calculateValues(a, b, operation))));
    }

    //Method where the conversion from roman to arabic happens
    static int convertFromRomanIntoArabic(String input)
    {
        switch (input)
        {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                outputResult("throws Exception!");
                System.exit(0);
        }
        return 0;
    }

    //Method to convert arabic to roman numbers.
    static String convertFromArabicIntoRoman(int arabicNum)
    {
        String romanNum = null;
        if(arabicNum < 1 || arabicNum > 100)
        {
            outputResult("throws Exception!");
            System.exit(0);
        }
        else if(arabicNum <= 10)
        {
            switch (arabicNum)
            {
                case 1:
                    romanNum = "I";
                    break;
                case 2:
                    romanNum = "II";
                    break;
                case 3:
                    romanNum = "III";
                    break;
                case 4:
                    romanNum = "IV";
                    break;
                case 5:
                    romanNum = "V";
                    break;
                case 6:
                    romanNum = "VI";
                    break;
                case 7:
                    romanNum = "VII";
                    break;
                case 8:
                    romanNum = "VIII";
                    break;
                case 9:
                    romanNum = "IX";
                    break;
                case 10:
                    romanNum = "X";
                    break;
            }
        }
        else if (arabicNum == 100)
        {
            romanNum="C";
        }
        else
        {
            char numTenth, numOnes;
            numTenth = String.valueOf(arabicNum).charAt(0);
            numOnes = String.valueOf(arabicNum).charAt(1);
            romanNum = switchMethodForRomanNums(numTenth, numOnes);
        }
        return romanNum;
    }

    //Switch method to convert arabic number to roman.
    static String switchMethodForRomanNums(char tenth, char ones)
    {
        String result = null;

        switch (tenth)
        {
            case '1':
                result = "X";
                break;
            case '2':
                result = "XX";
                break;
            case '3':
                result = "XXX";
                break;
            case '4':
                result = "XL";
                break;
            case '5':
                result = "L";
                break;
            case '6':
                result = "LX";
                break;
            case '7':
                result = "LXX";
                break;
            case '8':
                result = "LXXX";
                break;
            case '9':
                result = "XC";
                break;
        }

        switch (ones)
        {
            case '1':
                result += "I";
                break;
            case '2':
                result += "II";
                break;
            case '3':
                result += "III";
                break;
            case '4':
                result += "IV";
                break;
            case '5':
                result += "V";
                break;
            case '6':
                result += "VI";
                break;
            case '7':
                result += "VII";
                break;
            case '8':
                result += "VIII";
                break;
            case '9':
                result += "IX";
                break;
        }
        return result;
    }


    //Method to calculate.
    static int calculateValues(int a, int b, char operation)
    {
        int result = 0;
        if((a<1 || a>10) || (b<1 || b>10))
        {
            outputResult("throws Exception!");
            System.exit(0);
        }
        else
        {
            switch (operation)
            {
                case '+':
                    result = a + b;
                    break;
                case '-':
                    result = a - b;
                    break;
                case '*':
                    result = a * b;
                    break;
                case '/':
                    result = a / b;
                    break;
                default:
                    outputResult("throws Exception!");
                    System.exit(0);
            }
        }
        return result;
    }

    //Method to output the result
    static void outputResult(String output)
    {
        System.out.print("\n\nOutput:\n" + output);
    }
}
